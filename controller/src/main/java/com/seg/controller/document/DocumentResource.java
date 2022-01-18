package com.seg.controller.document;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.seg.api.document.DocumentService;
import com.seg.domain.document.dto.DocumentProperties;
import com.seg.domain.document.dto.DocumentResponse;
import com.seg.domain.document.projection.DocumentSummary;
import com.seg.domain.enumeration.Role;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/document")
@PreAuthorize("isAuthenticated()")
public class DocumentResource {
    
    private final DocumentService documentService;

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping(value = "/id/{id}")
    public ResponseEntity<DocumentResponse> findById(@PathVariable("id") @Min(0) final Long id){
        return new ResponseEntity<DocumentResponse>(documentService.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('GUEST', 'USER', 'MANAGER', 'ADMIN')")
    @PostMapping(value = "/commission")
    public ResponseEntity<Set<DocumentSummary>> findByCommission(@RequestBody @NotNull final Role rol){
        return new ResponseEntity<Set<DocumentSummary>>(documentService.findByRole(rol), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    @PostMapping(value = "/upload")
    public ResponseEntity<DocumentResponse> upload(@RequestBody @Valid final DocumentProperties archivoPropiedades){
        return new ResponseEntity<DocumentResponse>(documentService.save(archivoPropiedades), HttpStatus.OK);
    }
}
