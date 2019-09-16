package com.honeywell.finger.repository;

import com.honeywell.finger.entity.FingerDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FingerDeviceRepository extends JpaRepository<FingerDevice, String>, JpaSpecificationExecutor<FingerDevice> {

}
