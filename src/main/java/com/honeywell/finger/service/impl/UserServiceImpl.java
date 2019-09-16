package com.honeywell.finger.service.impl;

import com.honeywell.finger.constant.SymbolConstant;
import com.honeywell.finger.entity.User;
import com.honeywell.finger.repository.UserRepository;
import com.honeywell.finger.service.CommandService;
import com.honeywell.finger.service.UserService;
import com.honeywell.finger.util.JpaUtils;
import com.init.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @Author: Kris
 * @Date: 2019-09-03  13:34
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRep;

    @Autowired
    CommandService commandService;

    @Autowired
    @Qualifier("fingerExecutors")
    private ExecutorService executor;

    @Override
    public void saveByDevice(String info) {
        String[] line = info.split(SymbolConstant.USER);
        for (String str : line) {
            if (StringUtils.isEmpty(str)) {
                continue;
            }
            String[] s = str.split(SymbolConstant.tab);
            User u = new User();
            u.setPin(s[0].split(SymbolConstant.equal)[1]);
            u.setName(s[1].split(SymbolConstant.equal)[1]);
            u.setPermission(Integer.valueOf(s[2].split(SymbolConstant.equal)[1]));
            u.setPassword(s[3].substring(s[3].indexOf(SymbolConstant.equal) + 1));
            u.setCardNo(s[4].substring(s[4].indexOf(SymbolConstant.equal) + 1));
            u.setGrp(Integer.valueOf(s[5].substring(s[5].indexOf(SymbolConstant.equal) + 1)));
            u.setVerify(Integer.valueOf(s[7].substring(s[7].indexOf(SymbolConstant.equal) + 1)));
            userRep.save(u);
        }
    }

    @Override
    public void saveByWeb(User user) {
        User u = userRep.save(user);
        //异步存储下发指令
        executor.submit(() -> {
            commandService.save(u);
        });
    }

    @Override
    public void deleteById(Long id) {
        userRep.deleteById(id);
    }

    @Override
    public List<User>findAll() {
        return userRep.findAll();
    }


    /**
     * 分页查询
     *
     * @return
     */
    @Override
    public Page<User> findPage() {
        Page<User> entityPage = userRep.findAll(JpaUtils.getPageRequest());
        return entityPage;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @Override
    public Page<User> findPage(Specification<User> specification) {
        Page<User> entityPage = userRep.findAll(specification, JpaUtils.getPageRequest());
        return entityPage;
    }

}
