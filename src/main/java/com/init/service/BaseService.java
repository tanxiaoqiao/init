package com.init.service;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

/**
 * service基类接口
 *
 */
public interface BaseService<T> {

    void save(T t);

    void delete(T t);

    void deleteById(Long id);

    Optional<T> findById(Long id);

    Page<T> findPage();

    Page<T> findPage(Specification<T> specification);

}
