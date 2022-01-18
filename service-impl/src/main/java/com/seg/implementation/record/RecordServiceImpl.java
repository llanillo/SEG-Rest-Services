package com.seg.implementation.record;

import java.util.List;

import com.seg.api.record.RecordService;
import com.seg.domain.record.dto.RecordResponse;
import com.seg.domain.record.entity.Record;
import com.seg.precaution.exception.controller.record.RecordNotFoundException;
import com.seg.repository.record.RecordRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService{

    private final RecordRepository recordRepository;

    @Override
    public Record findById(final Long id) {
        return recordRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException());
    }

    @Override
    public Record save(final Record record) {
        return recordRepository.save(record);
    }

    @Override
    public Record update(Long id, Record record) {
        findById(id);
        return save(record);
    }

    @Override
    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    @Override
    public Page<Record> findAll(final Pageable paginacion) {
        return recordRepository.findAll(paginacion);
    }
    
    @Override
    public long count() {
        return recordRepository.count();
    }
    
    @Override
    public void delete(final Long id) {
        findById(id);
        recordRepository.deleteById(id);
    }

    public RecordResponse convertToResponse(Record record) {
        // TODO Auto-generated method stub
        return null;
    }

    public Record convertToEntity(RecordResponse recordResponse) {
        // TODO Auto-generated method stub
        return null;
    }
}
