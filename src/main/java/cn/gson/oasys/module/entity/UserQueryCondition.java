package cn.gson.oasys.module.entity;


import io.swagger.annotations.ApiModelProperty;

/**
 * @description: 用户实体类
 * @author: xz
 */
public class UserQueryCondition {
    private int id;
    @ApiModelProperty(value = "用户名称")
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
