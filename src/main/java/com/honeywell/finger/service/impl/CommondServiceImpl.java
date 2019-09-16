package com.honeywell.finger.service.impl;

import com.honeywell.finger.constant.SymbolConstant;
import com.honeywell.finger.entity.Command;
import com.honeywell.finger.entity.FingerDevice;
import com.honeywell.finger.entity.FingerInfo;
import com.honeywell.finger.entity.User;
import com.honeywell.finger.repository.CommandRepository;
import com.honeywell.finger.repository.FingerDeviceRepository;
import com.honeywell.finger.repository.FingerInfoRepository;
import com.honeywell.finger.service.CommandService;
import com.honeywell.finger.service.UserService;
import com.honeywell.finger.util.JpaUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Kris
 * @Date: 2019-09-03  13:34
 */
@Service
@Transactional
@Log4j2
public class CommondServiceImpl implements CommandService {

    @Autowired
    CommandRepository commandRep;


    @Autowired
    FingerDeviceRepository fingerDeviceRep;
    @Autowired
    FingerInfoRepository fingerInfoRep;
    @Autowired
    UserService userService;


    private static final String Name = "Name";
    private static final String Pri = "Pri";
    private static final String Passwd = "Passwd";
    private static final String Card = "Card";
    private static final String Grp = "Grp";
    public static String CDATA = "C:";
    public static String DATA = "DATA";
    public static final String PIN = "PIN";
    public static final String FID = "FID";
    public static final String VALID = "Valid";
    public static final String TMP = "TMP";
    public static final String Size = "Size";

    //下发命令类型
    public static final String UPDATE_USER = "UPDATE_USER";
    //public static final String TRUNCATED = "[truncated]";

    //跟新用户
    /* C:24:DATA USER PIN=1\tName=\270\366\tPri=0\tPasswd=\tCard=\t\tGrp=1\t\n
     *  [truncated]C:35:DATA FP PIN=888\tFID=6\tValid=1\tSize=1592\tTMP= */
    @Override
    public void save(User u) {
        List<FingerDevice> all = fingerDeviceRep.findAll();

        all.forEach(s -> {
            Command commond = new Command();
            commond.setUserId(u.getId());
            commond.setSerialNumber(s.getSerialNumber());
            commond.setType(UPDATE_USER);
            Command c = commandRep.save(commond);
            StringBuffer sb = new StringBuffer();
            sb.append(CDATA);
            sb.append(c.getId()).append(SymbolConstant.colon)
                    .append(DATA).append(" ")
                    .append(SymbolConstant.USER).append(" ")
                    .append(PIN).append(SymbolConstant.equal).append(u.getPin()).append(SymbolConstant.tab)
                    .append(Name).append(SymbolConstant.equal).append(u.getName()).append(SymbolConstant.tab)
                    .append(Pri).append(SymbolConstant.equal).append(u.getPermission() == null ? "" : u.getPermission()).append(SymbolConstant.tab)
                    .append(Passwd).append(SymbolConstant.equal).append(u.getPassword() == null ? "" : u.getPassword()).append(SymbolConstant.tab)
                    .append(Card).append(SymbolConstant.equal).append(u.getCardNo() == null ? "" : u.getCardNo()).append(SymbolConstant.tab)
                    .append(Grp).append(SymbolConstant.equal).append(u.getGrp() == null ? "" : u.getGrp()).append(SymbolConstant.tab+SymbolConstant.enter);
            c.setDescription(sb.toString());
            commandRep.save(c);

            List<FingerInfo> list = fingerInfoRep.findByPin(u.getPin());
            list.forEach(l -> {
                StringBuffer fp = new StringBuffer();
                fp.append(CDATA);
                Command cd = new Command();
                cd.setSerialNumber(s.getSerialNumber());
                cd.setType(SymbolConstant.FP);
                cd.setUserId(u.getId());
                Command save = commandRep.save(cd);
                fp.append(save.getId()).append(SymbolConstant.colon)
                        .append(DATA).append(" ")
                        .append(SymbolConstant.FP).append(" ")
                        .append(PIN).append(SymbolConstant.equal).append(l.getPin()).append(SymbolConstant.tab)
                        .append(FID).append(SymbolConstant.equal).append(l.getFid() == null ? "" : l.getFid()).append(SymbolConstant.tab)
                        .append(VALID).append(SymbolConstant.equal).append(l.getValid() == null ? "" : l.getValid()).append(SymbolConstant.tab)
                        .append(Size).append(SymbolConstant.equal).append(l.getSize() == null ? "" : l.getSize()).append(SymbolConstant.tab)
                        .append(TMP).append(SymbolConstant.equal).append(l.getTmp() == null ? "" : l.getTmp());
                cd.setDescription(fp.toString());
                commandRep.save(save);

            });


        });

    }

    @Override
    public List<Command> findAll() {
        return commandRep.findAll();
    }

    @Override
    public List<Command> findNotExeByType(String type) {
        return commandRep.findCommandByStatusAndType(9, type);
    }

    @Override
    public List<Command> findNotExeByTypeAndUserId(String type, Long userId) {
        return commandRep.findCommandByStatusAndTypeAndUserId(9, type, userId);
    }

    @Override
    public Command findById(Long id) {
        return commandRep.findById(id).orElse(null);
    }

    @Override
    public void update(Integer statu, Long id) {
        commandRep.updateStatus(statu, id);
    }

    @Override
    public void deleteByIds(List<Long> id) {
        commandRep.deleteAllByIdIn(id);
    }


    /**
     * 分页查询
     *
     * @return
     */
    @Override
    public Page<Command> findPage() {
        Page<Command> entityPage = commandRep.findAll(JpaUtils.getPageRequest());
        return entityPage;
    }


    @Override
    public void updateUser() {
        List<User> all = userService.findAll();
        all.forEach(a -> {
            save(a);
        });
        log.info("用户全部更新");
    }

    @Override
    public String clearAll() {
        return "C:1:CLEAR DATA";
    }

    @Override
    public String clearLog() {
        return  "C:1:CLEAR LOG";
    }


}
