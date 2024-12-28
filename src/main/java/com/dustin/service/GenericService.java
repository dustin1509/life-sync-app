package com.dustin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface GenericService<T, ID> {
    Page<T> findPaging(Specification<T> specification, Pageable pageable);

    Page<T> findPagingActive(Specification<T> specification, Pageable pageable);

    List<T> findAllActive(Specification<T> specification);



}
