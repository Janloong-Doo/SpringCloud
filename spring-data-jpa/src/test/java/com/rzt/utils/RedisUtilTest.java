package com.rzt.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisUtilTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test() {
        RedisSerializer<?> defaultSerializer = redisUtil.template.getDefaultSerializer();
        System.out.println(defaultSerializer);
        System.out.println(redisUtil.template.getKeySerializer());
        System.out.println(redisUtil.template.getValueSerializer());
        System.out.println(redisUtil.template.getStringSerializer());
        System.out.println(redisUtil.template.getHashKeySerializer());
        System.out.println(redisUtil.template.getHashValueSerializer());
        //redisUtil.set("doo", "janoong");
        //Object a = redisUtil.get("a");
        //System.out.println("===============");
        //System.out.println(a);
        //Assert.assertEquals("a", a);
        //redisUtil.addLink("l", "1");
        //redisUtil.addLink("l", "2");
        //redisUtil.addLink("l", "3");
    }

    @Test
    public void test2() {
        long l = redisUtil.getLinkSize2("f");
        System.out.println("========");
        System.out.println(l);
    }

    //@Autowired
    //RedisConnection redisConnection;ioe

    @Test
    public void redisSend() {

        //redisConnection.publish("hello".getBytes(), "janloongdoo".getBytes());
        redisUtil.template.convertAndSend("hello", "janloongdoo");


    }

    public int upZipFile(File zipFile, String folderPath) throws IOException {
//        ZipFile zfile = new ZipFile(zipFile);
//        Enumeration zList = zfile.entries();
//        ZipEntry ze = null;
//        byte[] buf = new byte[1024];
//        while (zList.hasMoreElements()) {
//            ze = (ZipEntry) zList.nextElement();
//            if (ze.isDirectory()) {
//                Log.d(TAG, "ze.getName() = " + ze.getName());
//                String dirstr = folderPath + ze.getName();
//                dirstr = new String(dirstr.getBytes("8859_1"), "GB2312");
//                Log.d(TAG, "str = " + dirstr);
//                File f = new File(dirstr);
//                f.mkdir();
//                continue;
//            }
//            Log.d(TAG, "ze.getName() = " + ze.getName());
//            OutputStream os = new BufferedOutputStream(new FileOutputStream(getRealFileName(folderPath, ze.getName())));
//            InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
//            int readLen = 0;
//            while ((readLen = is.read(buf, 0, 1024)) != -1) {
//                os.write(buf, 0, readLen);
//            }
//            is.close();
//            os.close();
//        }
//        zfile.close();
        return 0;
    }

}