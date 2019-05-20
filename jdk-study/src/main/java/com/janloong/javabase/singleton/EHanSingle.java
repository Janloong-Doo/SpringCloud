package com.janloong.javabase.singleton;


/**
 * 饿汉式 线程安全(提前实例化，不涉及多线程问题)
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018/3/5 16:35
 **/
class EHanSingle {
    private static EHanSingle eHanSingle = new EHanSingle();

    EHanSingle() {
    }

    public static EHanSingle getInstance() {
        return eHanSingle;
    }
}

/**
 * 懒汉式 线程安全
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018/3/5 16:44
 **/
class LanHanSingle {
    private static LanHanSingle lanHanSingle;

    LanHanSingle() {
    }

    public LanHanSingle getInstance() {
        synchronized (EHanSingle.class) {
            if (lanHanSingle == null) {
                lanHanSingle = new LanHanSingle();
            }
            return lanHanSingle;
        }
    }

    public synchronized LanHanSingle getInstance2() {
        if (lanHanSingle == null) {
            lanHanSingle = new LanHanSingle();
        }
        return lanHanSingle;
    }
}

/**
 * 内部类式单例类
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018/3/5 16:48
 **/
class InnerClassSingle {

    InnerClassSingle() {
    }

    private static class InnerClassSingleHolder {
        private static InnerClassSingle innerClassSingle = new InnerClassSingle();
    }

    private static InnerClassSingle getInstance() {
        return InnerClassSingleHolder.innerClassSingle;
    }
}

/**
 * 双检锁写法
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018/3/5 16:58
 **/
class DoubleSingle {
    private static DoubleSingle doubleSingle;

    DoubleSingle() {
    }

    public static DoubleSingle getInstance() {
        if (doubleSingle == null) {
            synchronized (DoubleSingle.class) {
                if (doubleSingle == null) {
                    doubleSingle = new DoubleSingle();
                }
            }
        }
        return doubleSingle;
    }
}
