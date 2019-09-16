package com.honeywell.finger.service;

import com.honeywell.finger.entity.FingerDevice;

import java.util.Optional;


public interface FingerDeviceService extends BaseService<FingerDevice> {
    void deleteBySN(String sn);

    Optional<FingerDevice> findBySN(String id);

    void save(String sn,String info);
    void saveByWeb(FingerDevice fd);
     void deleteBySid(String id);

}
