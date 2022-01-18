package com.seg.domain.commission.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.seg.domain.document.entity.Document;
import com.seg.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedEntityGraph(
        name ="Commission.manager",
        attributeNodes = {
            @NamedAttributeNode(value = "manager"),
        }) 
public class Commission {
    
    @Id
    @EqualsAndHashCode.Include
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "commission")
    private List <Document> document;
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "commission")
    private Set<User> user;
        
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "commissioned")    
    private Set<User> manager;
    
    @NotNull    
    @EqualsAndHashCode.Include
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "function_id", referencedColumnName = "id")
    private Function function;

    @Override
    public String toString() {
        return function.toString();
    }    
}
