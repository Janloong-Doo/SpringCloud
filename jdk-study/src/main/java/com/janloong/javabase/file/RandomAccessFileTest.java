package com.janloong.javabase.file;


import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用内存映射处理文件
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-03-03 23:22
 */
public class RandomAccessFileTest {

    public static void main(String[] args) {
        withDeal();
        //noDeal();

    }


    public static long getSize() {
        //File file = new File("F:\\xp.rar");
        //System.out.println(file.length());
        //return file.length();
        return 1392640;
    }

    /**
     * 处理操作
     * <p>
     * sum:175379375  time:5150
     * sum:175379375  time:20
     * sum:158663612  time:13
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/3/3 23:25
     **/
    private static void withDeal() {
        try {
            FileInputStream fis = new FileInputStream("F:\\xp.rar");
            int sum = 0;
            int n;
            long t1 = System.currentTimeMillis();
            try {
                while ((n = fis.read()) >= 0) {
                    sum += n;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            long t = System.currentTimeMillis() - t1;
            System.out.println("sum:" + sum + "  time:" + t);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream("F:\\xp.rar");
            BufferedInputStream bis = new BufferedInputStream(fis);
            int sum = 0;
            int n;
            long t1 = System.currentTimeMillis();
            try {
                while ((n = bis.read()) >= 0) {
                    sum += n;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            long t = System.currentTimeMillis() - t1;
            System.out.println("sum:" + sum + "  time:" + t);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        MappedByteBuffer buffer = null;
        try {
            buffer = new RandomAccessFile("F:\\xp.rar", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0,
                    getSize());
            int sum = 0;
            int n;
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < getSize(); i++) {
                n = 0x000000ff & buffer.get(i);
                sum += n;
            }
            long t = System.currentTimeMillis() - t1;
            System.out.println("sum:" + sum + "  time:" + t);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 无处理文件
     * <p>
     * sum:0  time:5165
     * sum:0  time:19
     * sum:0  time:5
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/3/3 23:32
     **/
    public static void noDeal() {
        try {
            FileInputStream fis = new FileInputStream("F:\\xp.rar");
            int sum = 0;
            int n;
            long t1 = System.currentTimeMillis();
            try {
                while ((n = fis.read()) >= 0) {
                    //sum+=n;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            long t = System.currentTimeMillis() - t1;
            System.out.println("sum:" + sum + "  time:" + t);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream("F:\\xp.rar");
            BufferedInputStream bis = new BufferedInputStream(fis);
            int sum = 0;
            int n;
            long t1 = System.currentTimeMillis();
            try {
                while ((n = bis.read()) >= 0) {
                    //sum+=n;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            long t = System.currentTimeMillis() - t1;
            System.out.println("sum:" + sum + "  time:" + t);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        MappedByteBuffer buffer = null;
        try {
            buffer = new RandomAccessFile("F:\\xp.rar", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0,
                    getSize());
            int sum = 0;
            int n;
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < getSize(); i++) {
                //n=0x000000ff&buffer.get(i);
                //sum+=n;
            }
            long t = System.currentTimeMillis() - t1;
            System.out.println("sum:" + sum + "  time:" + t);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
