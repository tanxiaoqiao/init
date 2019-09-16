package com.honeywell.finger.repository;

import com.honeywell.finger.entity.FingerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FingerInfoRepository extends JpaRepository<FingerInfo, Long>, JpaSpecificationExecutor<FingerInfo> {

    @Query
    FingerInfo findByPinAndFid(String code,String fid);

    @Query
    List<FingerInfo> findByPin(String code);

}
