package com.seg.implementation.document;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.seg.api.commission.CommissionService;
import com.seg.api.document.DocumentService;
import com.seg.domain.commission.entity.Commission;
import com.seg.domain.document.dto.DocumentEdit;
import com.seg.domain.document.dto.DocumentProperties;
import com.seg.domain.document.dto.DocumentResponse;
import com.seg.domain.document.entity.Document;
import com.seg.domain.document.entity.DocumentBackup;
import com.seg.domain.document.projection.DocumentSummary;
import com.seg.domain.enumeration.Action;
import com.seg.domain.enumeration.Role;
import com.seg.domain.enumeration.Status;
import com.seg.precaution.exception.controller.document.DocumentNotFoundException;
import com.seg.repository.document.DocumentRepository;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService{

    private final DocumentRepository documentRepository;    
    private final CommissionService commissionService;
    private final ModelMapper modelMapper;

    @Override
    public DocumentResponse findById(final Long id) {
        return documentRepository.findById(id).map(this::convertToResponse)
            .orElseThrow(() -> new DocumentNotFoundException());
    }
    
    @Override
    public DocumentResponse save(final DocumentProperties documentProperties) {
        final Document document = convertToEntity(documentProperties);
        document.setStatus(Status.ACTIVE);
        document.setAction(Action.NONE);
        return convertToResponse(documentRepository.save(document));
    }

    @Override
    public DocumentResponse update(final Long id, final DocumentProperties documentProperties) {
        return documentRepository.findById(id).map((document) -> {
            document = modelMapper.map(documentProperties, Document.class);
            return convertToResponse(document);
        })
        .orElseGet(() -> {
            final Document document = convertToEntity(documentProperties);
            document.setId(id);            
            return convertToResponse(documentRepository.save(document));
        });        
    }

    @Override
    public Document findEntity(final Long id){
        return documentRepository.findById(id)
            .orElseThrow(() -> new DocumentNotFoundException());
    }
    
    @Override
    public DocumentResponse restoreBackup(final Long id) {
        final Document document = findEntity(id);
        final DocumentBackup backup = document.getBackup();
        restoreBackup(document, backup);
        documentRepository.save(document);
        return convertToResponse(document);
    }

    @Override
    public Set<DocumentSummary> findByRole(final Role role) {   
        final Commission commission = commissionService.findByRole(role);
        return documentRepository.findAllByCommissionAndStatusAndAction(commission, Status.ACTIVE, Action.NONE);                        
    }
    
    @Override
    public List<DocumentResponse> findAll() {
        return documentRepository.findAll()
                    .stream()
                    .map(this::convertToResponse)  
                    .collect(Collectors.toList());
    }
    
    @Override
    public Page<DocumentResponse> findAll(final Pageable pageable) {
        final List<DocumentResponse> list = documentRepository.findAll(pageable)
                                        .getContent()
                                        .stream()
                                        .map(this::convertToResponse)            
                                        .collect(Collectors.toList());
        return new PageImpl<>(list);
    }

    @Override
    public byte[] findData(final Long id) {
        return documentRepository.findData(id)
            .orElseThrow(() -> new DocumentNotFoundException());            
    }

    @Override
    public Status findStatus(final Long id) {
        return documentRepository.findStatus(id)
            .orElseThrow(() -> new DocumentNotFoundException());
    }    

    @Override
    public long count() {
        return documentRepository.count();
    }

    @Override
    public void delete(final Long id) {
        findById(id);
        documentRepository.deleteById(id);
    }

    public DocumentResponse convertToResponse(final Document document) {
        return modelMapper.map(document, DocumentResponse.class);
    }  
    
    public Document convertToEntity(final DocumentProperties documentProperties) {
        return modelMapper.map(documentProperties, Document.class);
    }

    public Document convertToEntity(DocumentEdit documentEdit) {
        // TODO Auto-generated method stub
        return null;
    }

    private void restoreBackup(final Document document, final DocumentBackup backup){
        document.setData(backup.getData());
        document.setTitle(backup.getTitle());
        document.setName(backup.getName());
        document.setSize(backup.getSize());
        document.setDescription(backup.getDescription());
    }
}
