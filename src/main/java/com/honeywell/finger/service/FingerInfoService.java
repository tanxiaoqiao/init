package com.honeywell.finger.service;

import com.honeywell.finger.entity.FingerInfo;


public interface FingerInfoService extends BaseService<FingerInfo> {

   /**
    * 存储指纹信息
    * @param info
    */
   void saveEntity(String info);

   /**
    * 存储指静脉模版
    * @param info
    */
   void save(String info);

}
