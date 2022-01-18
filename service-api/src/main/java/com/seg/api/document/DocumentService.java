package com.seg.api.document;

import java.util.Set;

import com.seg.api.blueprint.service.BasicService;
import com.seg.api.blueprint.service.EntityService;
import com.seg.domain.document.dto.DocumentProperties;
import com.seg.domain.document.dto.DocumentResponse;
import com.seg.domain.document.entity.Document;
import com.seg.domain.document.projection.DocumentSummary;
import com.seg.domain.enumeration.Role;
import com.seg.domain.enumeration.Status;


public interface DocumentService extends EntityService<Document>, BasicService<DocumentProperties, DocumentResponse>{

    DocumentResponse restoreBackup (final Long id);
    
    Set<DocumentSummary> findByRole (final Role role);
    
    byte[] findData (final Long id);

    Status findStatus (final Long id);
}
