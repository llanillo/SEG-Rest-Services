package com.seg.domain.document.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.seg.domain.commission.entity.Commission;
import com.seg.domain.enumeration.Action;
import com.seg.domain.enumeration.DocumentType;
import com.seg.domain.enumeration.Status;
import com.seg.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Document {
    
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include()
    private Long id;

    private Long size;
    
    private String name;
    private String title;    
    private String description;

    private LocalDateTime date;    
    
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "backup", referencedColumnName = "id")
    private DocumentBackup backup;    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commission_id")  
    private Commission commission;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")        
    private User author;    

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "doc_participated")
    private Set<User> coAuthor;
    
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Action action;
    
    @Lob
    @Basic (fetch = FetchType.LAZY)  
    private byte[] data;
}
