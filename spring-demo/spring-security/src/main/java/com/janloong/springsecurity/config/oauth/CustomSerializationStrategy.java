package com.janloong.springsecurity.config.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.security.oauth2.provider.token.store.redis.StandardStringSerializationStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 自定义序列化策略，取消类的序列化限制
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/12/31 10:57
 **/
@Configuration
public class CustomSerializationStrategy extends StandardStringSerializationStrategy {

    private static final byte[] EMPTY_ARRAY = new byte[0];

    //private static final List<String> ALLOWED_CLASSES;

    //static {
        //ALLOWED_CLASSES = Collections.unmodifiableList(classes);
        //ALLOWED_CLASSES = List.of("java.lang.", "java.util.", "org.springframework.security.", "com.janloong.");
    //}

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T deserializeInternal(byte[] bytes, Class<T> clazz) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            //SaferObjectInputStream saferObjectInputStream = new SaferObjectInputStream(
            //        new ByteArrayInputStream(bytes), ALLOWED_CLASSES);
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            //ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes)){
            //    @Override
            //    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
            //        if (isProhibited(desc.getName())) {
            //            throw new NotSerializableException("Not allowed to deserialize " + desc.getName());
            //        }
            //        return super.resolveClass(desc);
            //    }
            //};
            return (T) objectInputStream.readObject();
        } catch (Exception e) {
            throw new SerializationFailedException("Failed to deserialize payload", e);
        }
    }

    @Override
    protected byte[] serializeInternal(Object object) {
        if (object == null) {
            return EMPTY_ARRAY;
        }
        if (!(object instanceof Serializable)) {
            throw new IllegalArgumentException(this.getClass().getSimpleName() + " requires a Serializable payload but received an object of type [" + object.getClass().getName() + "]");
        }

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new SerializationFailedException("Failed to serialize object", e);
        }
    }

    //private boolean isProhibited(String className) {
    //    for (String allowedClass : ALLOWED_CLASSES) {
    //        if (className.startsWith(allowedClass)) {
    //            return false;
    //        }
    //    }
    //    return true;
    //}
}

