package com.bangdao.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.bangdao.common.utils.http.HttpUtils;
import com.bangdao.framework.config.ProjectPropertiesConfig;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取地址类
 */
@Slf4j
public class AddressUtils {

    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    public static String getRealAddressByIP(String ip) {
        String address = "XX XX";
        if (ProjectPropertiesConfig.isAddressEnabled()) {
            String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);
            if (StringUtils.isEmpty(rspStr)) {
                log.error("获取地理位置异常 " + ip);
                return address;
            }
            JSONObject obj = JSONObject.parseObject(rspStr);
            JSONObject data = obj.getObject("data", JSONObject.class);
            String region = data.getString("region");
            String city = data.getString("city");
            address = region + " " + city;
        }
        return address;
    }
}
