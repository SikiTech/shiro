/**
 * projectName: spring-boot-in-action
 * fileName: PasswordHelper.java
 * packageName: com.sikiapp.springbootinaction.config
 * date: 2019-06-14 上午10:05
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.config;

import com.sikiapp.springbootinaction.model.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @className: PasswordHelper
 * @packageName: com.sikiapp.springbootinaction.config
 * @description: 密码工具类
 * @author: Robert
 * @data: 2019-06-14 上午10:05
 * @version: V1.0
 **/
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    /**
     * 基础散列算法
     */
    public static final String ALGORITHM_NAME = "md5";
    /**
     * 自定义散列次数
     */
    public static final int HASH_ITERATIONS = 2;


    public void encryptPassword(User user) {
        // 随机字符串作为salt因子，实际参与运算的salt我们还引入其它干扰因子
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(ALGORITHM_NAME, user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), HASH_ITERATIONS).toHex();
        user.setPassword(newPassword);
    }
}
