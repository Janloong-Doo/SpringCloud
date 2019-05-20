package com.janloong.javabase.initing;

import com.janloong.javabase.initing.utils.Animal;
import com.janloong.javabase.initing.utils.People;

/**
 * 对象初始化过程test
 *
 * @author Janloong
 * @create 2017-06-17 下午4:56
 **/
public class ObjectInitingTest {

    public static void main(String[] args) {

        /*对象初始化时的*/
        People people = new People();
        people.setName("Janloong");
        people.setAge(22);
        people.setTel("8008208820");
        Animal animal = new Animal();
        animal.setAge(7);
        animal.setName("喵星淫儿");
        people.setAnimal(animal);

        System.out.println(people.getAnimal().getName());
        System.out.println(people.getAnimal().getAge());
        System.out.println("-------------1---------------");

        Animal animal1 = people.getAnimal();
        animal1.setName("二哈儿");
        animal1.setAge(5);

        System.out.println(people.getAnimal().getName());
        System.out.println(people.getAnimal().getAge());
        System.out.println("-------------2---------------");

        Animal a = animal1;
        System.out.println(a.getName());
        System.out.println(a.getAge());
        System.out.println("-------------3---------------");

        a.setAge(111);
        a.setName("阿拉斯加");
        System.out.println("-------------4---------------");
        System.out.println(a.getName());
        System.out.println(a.getAge());

        System.out.println("-------------5---------------");
        System.out.println(people.getAnimal().getName());
        System.out.println(people.getAnimal().getAge());

        System.out.println("-------------6---------------");
        System.out.println(a==people.getAnimal());
        System.out.println(a.equals(people.getAnimal()));

        System.out.println("-------------7---------------");
        System.out.println("打印hash值");
        System.out.println(a.hashCode());
        System.out.println(people.getAnimal().hashCode());

    }
}
