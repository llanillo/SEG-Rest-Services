package com.seg.domain.user.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

import com.seg.domain.commission.entity.Commission;
import com.seg.domain.document.entity.Document;
import com.seg.domain.enumeration.Career;
import com.seg.domain.enumeration.Status;

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
        name = "Usuario.priviliges",
        attributeNodes = {
            @NamedAttributeNode(value = "privilege"),
            @NamedAttributeNode(value = "commission"),
            @NamedAttributeNode(value = "commissioned"),
            @NamedAttributeNode(value = "career")})
@NamedEntityGraph(
        name = "Usuario.commissions",
        attributeNodes = {
            @NamedAttributeNode(value = "commission"),
            @NamedAttributeNode(value = "commissioned"),
            @NamedAttributeNode(value = "career")
        }
)
public class User{
    
    @Id
    @EqualsAndHashCode.Include
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long dni;
    private Long cuil;
    
    private String lastname;
    private String name;
    private String password;        
    private String email;    
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Career.class, fetch = FetchType.LAZY)  
    private Set<Career> career;
    
    @ManyToMany(fetch = FetchType.LAZY)    
    private Set<Privilege> privilege;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List <Document> doc_author; 
        
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Commission> commission;
    
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Commission> commissioned;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Document> doc_participated;
}
