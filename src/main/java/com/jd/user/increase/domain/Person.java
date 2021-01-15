package com.jd.user.increase.domain;

/**
 * 请填写类的描述
 *
 * @author wangyongpeng11
 * @date 2020-12-21 20:16
 */
public class Person {
    public Integer age;
    public String desc;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", desc='" + desc + '\'' +
                '}';
    }
}
