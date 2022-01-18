package com.seg.controller.record;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.seg.api.record.RecordService;
import com.seg.domain.record.entity.Record;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class RecordResource {
    
    private final RecordService recordService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Record> findById(@PathVariable("id") @Min(0) final Long id){
        return new ResponseEntity<Record>(recordService.findById(id), HttpStatus.OK);
    }

    @GetMapping (value = "/all")
    public ResponseEntity<List<Record>> findAll(){
        return new ResponseEntity<List<Record>>(recordService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<Record>> findAll(@RequestBody @NotNull final Pageable pageable){
        return new ResponseEntity<Page<Record>>(recordService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping (value = "/save")
    public ResponseEntity<Record> create(@RequestBody @Valid final Record recordDto) {        
        return new ResponseEntity<Record>(recordService.save(recordDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Record> update(@PathVariable("id") @Min(0) final Long id, @RequestBody @Valid final Record recordDto){                
        return new ResponseEntity<Record>(recordService.update(id, recordDto), HttpStatus.OK);        
    }

    @GetMapping(value = "/amount")
    public Long count (){
        return recordService.count();
    }
    
    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") @Min(0) final Long id){
        recordService.delete(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
}
