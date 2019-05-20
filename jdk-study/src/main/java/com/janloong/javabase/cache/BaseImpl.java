package com.janloong.javabase.cache;


/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-12-17 23:07
 */
public class BaseImpl implements BaseA, BaseB {
    @Override
    public String getA() {
        return "this is a ";
    }

    @Override
    public String getB() {
        return "this is b";
    }
}

class test {

    public static void main(String[] args) {
        BaseA a = new BaseImpl();
        BaseB b = new BaseImpl();
        System.out.println(a.getA());
        System.out.println(((BaseImpl) a).getB());
        System.out.println(b.getB());
        System.out.println(((BaseImpl) b).getA());
    }
}
