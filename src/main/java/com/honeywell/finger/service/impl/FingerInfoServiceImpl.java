package com.honeywell.finger.service.impl;

import com.honeywell.finger.constant.SymbolConstant;
import com.honeywell.finger.entity.FingerInfo;
import com.honeywell.finger.repository.FingerInfoRepository;
import com.honeywell.finger.service.BaseService;
import com.honeywell.finger.service.FingerInfoService;
import com.honeywell.finger.util.JpaUtils;
import org.apache.commons.lang3.StringUtils;
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
public class FingerInfoServiceImpl implements FingerInfoService, BaseService<FingerInfo> {

    @Autowired
    FingerInfoRepository fingerInfoRep;


    @Override
    public void delete(FingerInfo entity) {
        fingerInfoRep.delete(entity);
    }


    @Override
    public void deleteById(Long id) {
        fingerInfoRep.deleteById(id);
    }

    @Override
    public Optional<FingerInfo> findById(Long id) {
        return fingerInfoRep.findById(id);
    }

    /**
     * 分页查询
     *
     * @return
     */
    @Override
    public Page<FingerInfo> findPage() {
        Page<FingerInfo> entityPage = fingerInfoRep.findAll(JpaUtils.getPageRequest());
        return entityPage;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @Override
    public Page<FingerInfo> findPage(Specification<FingerInfo> specification) {
        Page<FingerInfo> entityPage = fingerInfoRep.findAll(specification, JpaUtils.getPageRequest());
        return entityPage;
    }

    @Override
    public void saveEntity(String info) {
        String[] line = info.split(SymbolConstant.FP);
        for (String str : line) {
            if (StringUtils.isEmpty(str)) {
                continue;
            }
            String[] split = info.split(SymbolConstant.tab);
            FingerInfo fi = new FingerInfo();
            //todo同一个指纹更新
            String code = split[0].split(SymbolConstant.equal)[1];
            String fid = split[1].split(SymbolConstant.equal)[1];
            FingerInfo fingerInfo = fingerInfoRep.findByPinAndFid(code, fid);
            if (fingerInfo != null) {
                fi.setId(fingerInfo.getId());
            }
            fi.setPin(split[0].split(SymbolConstant.equal)[1]);
            fi.setFid(Integer.valueOf(split[1].split(SymbolConstant.equal)[1]));
            fi.setSize(Integer.valueOf(split[2].split(SymbolConstant.equal)[1]));
            fi.setValid(Integer.valueOf(split[3].split(SymbolConstant.equal)[1]));
            fi.setTmp(split[4].split(SymbolConstant.equal)[1]);
            fingerInfoRep.save(fi);
        }
    }

    @Override
    public void save(String info) {
        String[] split = info.split(SymbolConstant.tab);
        FingerInfo fi = new FingerInfo();
        fi.setPin(split[0].split(SymbolConstant.equal)[1]);
        fi.setFid(Integer.valueOf(split[1].split(SymbolConstant.equal)[1]));
        fi.setIndex(Integer.valueOf(split[2].split(SymbolConstant.equal)[1]));
        fi.setSize(Integer.valueOf(split[3].split(SymbolConstant.equal)[1]));
        fi.setValid(Integer.valueOf(split[4].split(SymbolConstant.equal)[1]));
        fi.setTmp(split[5].split(SymbolConstant.equal)[1]);
        fingerInfoRep.save(fi);
    }
}
