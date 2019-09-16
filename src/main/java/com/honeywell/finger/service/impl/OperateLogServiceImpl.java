package com.honeywell.finger.service.impl;

import com.honeywell.finger.constant.SymbolConstant;
import com.honeywell.finger.entity.OperateLog;
import com.honeywell.finger.repository.OperateLogRepository;
import com.honeywell.finger.util.JpaUtils;
import com.honeywell.finger.service.OperateLogService;
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
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    OperateLogRepository operateLogRep;

    @Override
    public void delete(OperateLog entity) {
        operateLogRep.delete(entity);
    }


    @Override
    public void deleteById(Long id) {
        operateLogRep.deleteById(id);
    }

    @Override
    public Optional<OperateLog> findById(Long id) {
        return operateLogRep.findById(id);
    }

    /**
     * 分页查询
     *
     * @return
     */
    @Override
    public Page<OperateLog> findPage() {
        Page<OperateLog> entityPage = operateLogRep.findAll(JpaUtils.getPageRequest());
        return entityPage;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @Override
    public Page<OperateLog> findPage(Specification<OperateLog> specification) {
        Page<OperateLog> entityPage = operateLogRep.findAll(specification, JpaUtils.getPageRequest());
        return entityPage;
    }

    @Override
    public void saveAttendance(String sn, String info) {
        String[] str = info.split(SymbolConstant.spanReturn);
        for (String s : str) {
            String[] st = s.split(SymbolConstant.tab);
            OperateLog op = new OperateLog();
            op.setCode(st[0]);
            op.setSerialNumber(sn);
            op.setTime(st[1]);
            op.setOpType(-1);
            operateLogRep.save(op);
        }
    }

    @Override
    public void saveOperateLog(String sn, String info) {
        String[] s = info.split(SymbolConstant.tab);
        OperateLog pl = new OperateLog();
        pl.setSerialNumber(sn);
        pl.setOpType(Integer.valueOf(s[0].split(" ")[1]));
        pl.setTime(s[2]);
        operateLogRep.save(pl);
    }

}
