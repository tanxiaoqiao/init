package com.honeywell.finger.controller;

import com.honeywell.finger.constant.SymbolConstant;
import com.honeywell.finger.entity.Command;
import com.honeywell.finger.redis.RedisMap;
import com.honeywell.finger.redis.RedisMapFactory;
import com.honeywell.finger.service.*;
import com.init.service.*;
import com.honeywell.finger.service.impl.CommondServiceImpl;
import com.honeywell.finger.util.RedisConstant;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(tags = "Accept")
@Log4j2
public class AcceptController {

    @Autowired
    FingerDeviceService fingerDeviceService;

    @Autowired
    OperateLogService operateLogService;

    @Autowired
    UserService userService;

    @Autowired
    FingerInfoService fingerInfoService;

    @Autowired
    CommandService commandService;


    private static RedisMap heartbeat;
    private static RedisMapFactory redisFactory;

    @Autowired
    public void setRedisFactory(RedisMapFactory redisFactory) {
        AcceptController.redisFactory = redisFactory;
        AcceptController.heartbeat =
                RedisMapFactory.getRedisMap(RedisConstant.HASH_KEY_SECURITY);
    }


    /**
     * 首次交互
     *
     * @param sn
     * @param rep
     * @return
     */
    @GetMapping(value = "/iclock/cdata")
    public Object delete(
            @RequestParam("SN") String sn,
            HttpServletResponse rep) {
        this.setHeader(rep);
        String stssr = "GET OPTION FROM:" + sn + "\n" +
                "    Stamp=0\n" +
                "    OpStamp=632325096\n" +
                "    PhotoStamp=0\n" +
                "    ErrorDelay=60\n" +
                "    Delay=30\n" +
                "    TransTimes=00:00;14:05\n" +
                "    TransInterval=1\n" +
                "    TransFlag=TransData AttLog\\tOpLog\\tAttPhoto\\tEnrollFP\\tEnrollUser\\tFPImag\\tChgUser\\tChgFP\\tFACE\\tUserPic\\tFVEIN\\tBioPhoto\\n\n" +
                "    TimeZone=8\n" +
                "    Realtime=1\n" +
                "    SupportPing=1\n" +
                "    Encrypt=0\n";
        log.info(sn + "首次交互成功");
        return stssr;
    }


    /**
     * 上传考勤，操作
     *
     * @param rep
     * @return
     */
    @PostMapping(value = "/iclock/cdata")
    public Object post(
            @RequestParam("SN") String sn,
            @RequestParam(value = "table", required = false) String table,
            HttpServletResponse rep, HttpServletRequest req) throws IOException {
        this.setHeader(rep);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = req.getInputStream().read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        String info = result.toString("gb2312");
        if (SymbolConstant.OPERLOG.equals(table)) {
            //操作日志
            if (info.startsWith(SymbolConstant.OPERLOG)) {
                return "OK:1";
            }
            //用户信息
            if (info.startsWith(SymbolConstant.USER)) {
                userService.saveByDevice(info);
                return "OK:2";
            }
            //指纹信息
            if (info.startsWith(SymbolConstant.FP)) {
                fingerInfoService.saveEntity(info);
                return "OK:3";
            }
            //指纹静脉模版
            if (info.startsWith(SymbolConstant.FVEIN)) {
                fingerInfoService.save(info);
                return "OK:1";
            }

        }
        //进出记录
        if (SymbolConstant.ATTLOG.equals(table)) {
            operateLogService.saveAttendance(sn, info);
            return "OK:9";
        }

        return "OK";
    }

    /**
     * 跟新信息 心跳 下发指令
     *
     * @param sn
     * @param info
     * @return
     */
    @GetMapping(value = "/iclock/getrequest", produces = "text/plain;charset=gb2312")
    public Object update(
            @RequestParam("SN") String sn,
            @RequestParam(value = "INFO", required = false) String info,
            HttpServletResponse rep) {
        this.setHeader(rep);
        //心跳
        heartbeat.putSetTime(sn, sn);
        //同步人员到设备
        List<Command> notExe = commandService.findNotExeByType(CommondServiceImpl.UPDATE_USER);
        StringBuilder sb = new StringBuilder();
        notExe.forEach(l -> {
            List<Command> command = commandService.findNotExeByTypeAndUserId(SymbolConstant.FP, l.getUserId());
            sb.append(l.getDescription());
            command.forEach(c -> {
                sb.append(c.getDescription());
            });

        });
        return sb;

        //清除所有数据
      //  return commandService.clearAll();

        //清除考勤记录
        // return    commandService.clearLog();

        //删除用户指纹信息
        //return "C:1:DATA DELETE FINGERTMP PIN=3";

        //删除用户信息
        // return "C:1:DATA DELETE USERINFO PIN=3";

        //重启
       //return "C:107:REBOOT";


        //无任何下发信息返回值
//       if (info != null) {
//            fingerDeviceService.save(sn, info);
//        }
  //     return "OK";

    }

    /**
     * 返回下发结果
     *
     * @param sn
     * @return ID=${XXX}&Return=${XXX}&CMD=DATA
     */
    @PostMapping(value = "/iclock/devicecmd", consumes = "application/octet-stream;charset=gb2312")
    public Object commond(@RequestParam("SN") String sn, @RequestBody String data, HttpServletResponse rep) {
        //ID=1&Return=0&CMD=DATA
        String[] split = data.split(SymbolConstant.spanReturn);
        for (String s : split) {
            String[] str = s.split(SymbolConstant.and);
            String id = str[0].split(SymbolConstant.equal)[1];
            String status = str[1].split(SymbolConstant.equal)[1];
            commandService.update(Integer.valueOf(status), Long.valueOf(id));
        }
        this.setHeader(rep);
        return "OK";

    }

    @GetMapping("/heartbeat")
    public Object update(
            @RequestParam("SN") String sn) {
        return heartbeat.exist(sn);

    }


    private HttpServletResponse setHeader(HttpServletResponse rep) {
        rep.setHeader("Server", "nginx/1.6.0");
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE,dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区为GMT
        String str = sdf.format(cd.getTime());
        rep.setHeader("Date", str);
        rep.setContentType("text/plain");
        rep.setHeader("Pragma", "no-cache");
        rep.setHeader("Cache-Control", "no-store");
        return rep;
    }
}
