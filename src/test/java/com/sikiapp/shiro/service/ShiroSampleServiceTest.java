package com.sikiapp.shiro.service;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.security.MessageDigest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroSampleServiceTest {


    @Test
    public void md5() throws Exception {
        String str = "e10adc3949ba59abbe56e057f20f883e";
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            byte[] digest = md.digest();
            BigInteger bigInteger = new BigInteger(1, digest);
            String result = bigInteger.toString(16);
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密出现错误");
        }
    }

    @Test
    public void sha256() {
        String hashAlgorithName = Sha256Hash.ALGORITHM_NAME;
        String password = "123456";
        int hashIterations = 2;//加密次数
        ByteSource credentialsSalt = ByteSource.Util.bytes("wxKYXuTPST5SG0jMQzVPsg==");
        String salt = "wxKYXuTPST5SG0jMQzVPsg==";
        Object obj = new SimpleHash(hashAlgorithName, password, salt, hashIterations).toHex();
        System.out.println(obj);
    }




}