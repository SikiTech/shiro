/**
 * projectName: shiro
 * fileName: PasswordEncrypter.java
 * packageName: com.sikiapp.shiro.util
 * date: 2019-06-17 下午4:33
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.util;

import com.sikiapp.shiro.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @className: PasswordEncrypter
 * @packageName: com.sikiapp.shiro.util
 * @description: 自定义密码加密类
 * @author: Robert
 * @data: 2019-06-17 下午4:33
 * @version: V1.0
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class PasswordEncrypter<T extends User> {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void encryptPassword(T t) {
        // 随机生成盐
        t.setSalt(randomNumberGenerator.nextBytes().toHex());
        // 加密
        String encryptPwd = new SimpleHash(algorithmName, t.getPwd(), ByteSource.Util.bytes(t.getSalt()), hashIterations)
                .toHex();
        t.setPwd(encryptPwd);
    }




}