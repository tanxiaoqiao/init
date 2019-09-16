package com.honeywell.finger.service;

import com.honeywell.finger.entity.Command;
import com.honeywell.finger.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CommandService {

    void save(User user);

    List<Command> findAll();

    List<Command> findNotExeByType(String type);
    List<Command> findNotExeByTypeAndUserId(String type ,Long userId);

    Command findById(Long id);

    void  update(Integer status,Long id);

    void deleteByIds(List<Long> id);

    Page<Command> findPage();

    /**
     * 同步人员信息
     * @return
     */
    void updateUser();

    /**
     * 清除数据
     */
    String clearAll();


    String clearLog();
}
