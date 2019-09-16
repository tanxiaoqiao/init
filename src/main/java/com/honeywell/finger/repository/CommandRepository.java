package com.honeywell.finger.repository;

import com.honeywell.finger.entity.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long>, JpaSpecificationExecutor<Command> {


    @Modifying
    @Transactional
    void  deleteAllByIdIn(List<Long> ids);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Command cd SET cd.status = ?1 where cd.id = ?2")
    void  updateStatus(Integer status, Long id);

    List<Command> findCommandByStatusAndType(Integer status,String type);

    List<Command> findCommandByStatusAndTypeAndUserId(Integer status,String type,Long userId);
}
