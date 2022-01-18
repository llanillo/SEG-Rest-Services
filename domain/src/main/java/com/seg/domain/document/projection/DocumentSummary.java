package com.seg.domain.document.projection;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.seg.domain.enumeration.DocumentType;
import com.seg.domain.user.projection.UserInformation;

public interface DocumentSummary extends Serializable {
    
    Long getId();    
    Long getSize();

    String getTitle();
    String getDescription();

    UserInformation getAuthor();
    LocalDateTime getDate();    
    DocumentType getDocumentType();
}
