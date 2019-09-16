package com.honeywell.finger.service;


import com.honeywell.finger.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public interface UserService {

    void saveByDevice(String info);

    void saveByWeb(User user);

    void deleteById(Long id);
    List<User> findAll();

    Page<User> findPage();

    Page<User> findPage(Specification<User> specification);
}
