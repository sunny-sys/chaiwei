package com.bangdao.common.utils;

import org.junit.Test;

public class UUID {
    public static String getUUID()
    {
        String UUID = java.util.UUID.randomUUID().toString();
        return UUID.replaceAll("-","");
    }
}
