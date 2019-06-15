/**
 * projectName: shiro
 * fileName: ShiroSampleService.java
 * packageName: com.sikiapp.shiro.service
 * date: 2019-06-15 下午5:54
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.service;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

/**
 * @className: ShiroSampleService
 * @packageName: com.sikiapp.shiro.service
 * @description: Shiro Service
 * @author: Robert
 * @data: 2019-06-15 下午5:54
 * @version: V1.0
 **/
@Service
public class ShiroSampleService {

    @RequiresPermissions("read")
    public String read() {
        return "reading...";
    }

    @RequiresPermissions("write")
    public String write() {
        return "writting...";
    }


}