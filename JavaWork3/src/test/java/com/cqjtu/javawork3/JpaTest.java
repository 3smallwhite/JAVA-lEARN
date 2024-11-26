package com.cqjtu.javawork3;

import com.cqjtu.javawork3.repository.MajorRepository;
import com.cqjtu.javawork3.utils.JwtUtils;
import com.cqjtu.javawork3.utils.Md5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class JpaTest {
    @Test
    public void test() {
        String pass = "123";
        System.out.println(Md5Utils.toMd5(pass));

        Date date = new Date("");
    }
}
