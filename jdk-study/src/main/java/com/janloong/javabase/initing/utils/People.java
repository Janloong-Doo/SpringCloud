package com.janloong.javabase.initing.utils;

/**
 * @author Janloong
 * @create 2017-06-17 下午5:15
 **/
public class People {
    private String name;
    private String tel;
    private Integer age;
    private com.janloong.javabase.initing.utils.Animal animal;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public com.janloong.javabase.initing.utils.Animal getAnimal() {
        return animal;
    }

    public void setAnimal(com.janloong.javabase.initing.utils.Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "people{" +
                "name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", age=" + age +
                ", animal=" + animal +
                '}';
    }
}
