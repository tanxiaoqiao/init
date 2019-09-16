package com.honeywell.finger.service.impl;

import com.honeywell.finger.entity.FingerDevice;
import com.honeywell.finger.repository.FingerDeviceRepository;
import com.honeywell.finger.service.FingerDeviceService;
import com.honeywell.finger.util.JpaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @Author: Kris
 * @Date: 2019-09-03  13:34
 */
@Service
@Transactional
public class FingerDeviceServiceImpl implements FingerDeviceService {

    @Autowired
    FingerDeviceRepository fingerRep;

    @Override
    public void delete(FingerDevice entity) {
        fingerRep.delete(entity);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteBySid(String id) {
        fingerRep.deleteById(id);
    }

    @Override
    public Optional<FingerDevice> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public void deleteBySN(String id) {
        fingerRep.deleteById(id);
    }

    @Override
    public Optional<FingerDevice> findBySN(String id) {
        return fingerRep.findById(id);
    }

    @Override
    public void save(String sn, String info) {
        String[] split = info.split(",");
        FingerDevice fd = new FingerDevice(sn, split[0], Integer.valueOf(split[1]), Integer.valueOf(split[2]), Long.valueOf(split[3]), split[4], split[5], Integer.valueOf(split[9]),null);
        fingerRep.save(fd);

    }

    @Override
    public void saveByWeb(FingerDevice fd) {
        fingerRep.save(fd);
    }

    /**
     * 分页查询
     *
     * @return
     */
    @Override
    public Page<FingerDevice> findPage() {
        Page<FingerDevice> entityPage = fingerRep.findAll(JpaUtils.getPageRequest());
        return entityPage;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @Override
    public Page<FingerDevice> findPage(Specification<FingerDevice> specification) {
        Page<FingerDevice> entityPage = fingerRep.findAll(specification, JpaUtils.getPageRequest());
        return entityPage;
    }

}
