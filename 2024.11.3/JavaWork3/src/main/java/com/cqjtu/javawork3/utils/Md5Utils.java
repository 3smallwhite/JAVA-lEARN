package com.cqjtu.javawork3.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class Md5Utils {
    public static String toMd5(String str) {
        return DigestUtils.md5Hex(str);
    }
}
