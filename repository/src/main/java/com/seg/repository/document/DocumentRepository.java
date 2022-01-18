package com.seg.repository.document;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.seg.domain.commission.entity.Commission;
import com.seg.domain.document.entity.Document;
import com.seg.domain.document.entity.DocumentBackup;
import com.seg.domain.document.projection.DocumentSummary;
import com.seg.domain.enumeration.Action;
import com.seg.domain.enumeration.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{
    
    List<Document> findAll();

    Set<DocumentSummary> findAllByCommissionAndStatusAndAction(Commission commission, Status status, Action action);
    
    @Query("select ar.data from Document ar where ar.id = :id")
    Optional<byte[]> findData(@Param("id")Long id);
   
    @Query ("select ar.backup from Document ar where ar.id = :id")
    Optional<DocumentBackup> findBackup(@Param("id")Long id);
    
    @Query ("select ar.status from Document ar where ar.id = :id")
    Optional<Status> findStatus(@Param("id")Long id);       
}
