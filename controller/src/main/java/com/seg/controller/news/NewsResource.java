package com.seg.controller.news;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.seg.api.news.NewsService;
import com.seg.domain.news.dto.NewsResponse;

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
@RequestMapping("/novedad")
@RequiredArgsConstructor
public class NewsResource {
    
    private final NewsService newService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<NewsResponse> findById(@PathVariable("id") @Min(0) final Long id){
        return new ResponseEntity<NewsResponse>(newService.findById(id), HttpStatus.OK);
    }

    @GetMapping (value = "/all")
    public ResponseEntity<List<NewsResponse>> findAll(){
        return new ResponseEntity<List<NewsResponse>>(newService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<NewsResponse>> findAll(@RequestBody @NotNull final Pageable pageable){
        return new ResponseEntity<Page<NewsResponse>>(newService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping (value = "/save")
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid final NewsResponse newsDto) {        
        return new ResponseEntity<NewsResponse>(newService.save(newsDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<NewsResponse> update(@PathVariable("id") @Min(0) final Long id, @RequestBody @Valid final NewsResponse newsDto){                
        return new ResponseEntity<NewsResponse>(newService.update(id, newsDto), HttpStatus.OK);        
    }

    @GetMapping(value = "/count")
    public Long count (){
        return newService.count();
    }
    
    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") @Min(0) final Long id){
        newService.delete(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
}
