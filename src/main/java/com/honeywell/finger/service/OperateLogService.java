package com.honeywell.finger.service;

import com.honeywell.finger.entity.OperateLog;


public interface OperateLogService extends BaseService<OperateLog> {

   void  saveAttendance(String sn,String info);
    void  saveOperateLog(String sn,String info);

}
