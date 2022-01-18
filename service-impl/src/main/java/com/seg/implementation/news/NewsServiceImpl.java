package com.seg.implementation.news;

import java.util.List;
import java.util.stream.Collectors;

import com.seg.api.news.NewsService;
import com.seg.domain.news.dto.NewsResponse;
import com.seg.domain.news.entity.News;
import com.seg.precaution.exception.controller.news.NewsNotFoundException;
import com.seg.repository.news.NewsRepository;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;

    @Override
    public NewsResponse findById(final Long id) {
        return newsRepository.findById(id).map(e -> convertToResponse(e))
            .orElseThrow(() -> new NewsNotFoundException());
    }

    @Override
    public NewsResponse save(final NewsResponse newsDto) {
        final News news = converToEntity(newsDto);
        newsRepository.save(news);
        return newsDto;
    }
    
    @Override
    public NewsResponse update(final Long id, final NewsResponse newsDto) {
        return null;
    }
    
    @Override
    public List<NewsResponse> findAll() {
        return newsRepository.findAll()
                    .stream()
                    .map(this::convertToResponse)
                    .collect(Collectors.toList());
    }

    @Override
    public Page<NewsResponse> findAll(final Pageable pageable) {
        final List<NewsResponse> list = newsRepository.findAll(pageable)
                                                .getContent()
                                                .stream()
                                                .map(this::convertToResponse)
                                                .collect(Collectors.toList());
        return new PageImpl<>(list);
    }

    @Override
    public long count() {
        return newsRepository.count();
    }
    

    @Override
    public void delete(final Long id) {
        findById(id);
        newsRepository.deleteById(id);
    }

    public NewsResponse convertToResponse(final News news) {
        return modelMapper.map(news, NewsResponse.class);
    }

    public News converToEntity(final NewsResponse newsResponse) {
        return modelMapper.map(newsResponse, News.class);
    }
}
