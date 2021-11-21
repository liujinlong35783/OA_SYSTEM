package cn.gson.oasys.module.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 控制层代码如下
 * @author: xz
 */
@Api(value = "API - UserController",description = "用户模块接口详情")
@RestController
@RequestMapping("/testUser")
public class UserController {

    /***
     *  @ApiParam 如果方法接受的是具体参数，此注解需要加到方法中的参数上
     */
    @RequestMapping(value = "/queryUserByName",method = RequestMethod.GET)
    @ApiOperation(value = "根据用户名称查询服务")
    public void queryUserByName(@ApiParam(value = "用户username") @RequestParam(name="username",required = false, defaultValue ="tom" ) String name){
        System.out.println("queryUser====="+name);
    }


    /***
     * @ApiOperation 可用在方法头上.参数的描述容器
     */
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation(value = "用户删除服务")
    public void delete(@PathVariable String id){
        System.out.println(id);
    }
}
