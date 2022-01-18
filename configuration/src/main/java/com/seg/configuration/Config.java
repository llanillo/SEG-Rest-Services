package com.seg.configuration;

import com.seg.domain.user.dto.UserProperties;
import com.seg.domain.user.entity.User;

import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@PropertySource("classpath:security-application.properties")
public class Config{	
    
    @Bean    
    @Scope("prototype")
    public ModelMapper modelMapper(){
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(new Condition<Object, Object>() {

            @Override
            public boolean applies(MappingContext<Object, Object> context) {
                return (!(context.getSource() instanceof PersistentCollection) || ((PersistentCollection)context.getSource()).wasInitialized());
            }
            
        });
        return modelMapper;
    }   

    @Bean(name = "userModelMapper")
    public ModelMapper userModelMapper(final ModelMapper modelMapper){
        modelMapper.typeMap(UserProperties.class, User.class).addMappings(mapper -> {            
            mapper.skip(User::setCommission);
            mapper.skip(User::setCommissioned);
        });        
        return modelMapper;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
