package com.dustin.service.impl;

import com.dustin.service.GenericRepository;
import com.dustin.service.GenericService;
import com.dustin.service.GenericSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public abstract class GenericServiceImpl<T, ID> implements GenericService<T, ID> {
    @Autowired
    protected GenericRepository<T, ID> repository;

    @Override
    public Page<T> findPaging(Specification<T> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Override
    public Page<T> findPagingActive(Specification<T> specification, Pageable pageable) {
        return repository.findAll(specification.and(GenericSpecification.isActive()), pageable);
    }

    @Override
    public List<T> findAllActive(Specification<T> specification) {
        return findPagingActive(specification, Pageable.unpaged()).getContent();
    }
}
