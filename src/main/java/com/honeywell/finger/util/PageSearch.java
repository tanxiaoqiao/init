package com.honeywell.finger.util;

import com.honeywell.finger.constant.OrderType;
import lombok.Data;

import java.util.Map;


@Data
public class PageSearch {
    private String keyword;                            //关键字
    private Integer pi = 0;                            //页码

    private Integer ps = 20;                           //一页显示的项目数

    private OrderType orderType = OrderType.DESC;
    private String orderColumn = "id";


    private Map<String, Object> pageParameter;
    private Map<String, Object[]> filterParameter;
    private Map<String, Object> rangeMaxParameter;
    private Map<String, Object> rangeMinParameter;
    private Map<String, Object> exportParameter;
}
