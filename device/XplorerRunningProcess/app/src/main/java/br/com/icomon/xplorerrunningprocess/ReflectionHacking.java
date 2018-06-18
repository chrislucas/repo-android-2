package br.com.icomon.xplorerrunningprocess;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by r028367 on 22/03/2018.
 */

public class ReflectionHacking<T> {


    @IntDef ({Field.PUBLIC, Field.DECLARED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ModifierTypes {}

    public Method getAccessPrivateMethod(Class<T> clazz, String methodName, Class<?> ... params) throws NoSuchMethodException {
        Method method =clazz.getDeclaredMethod(methodName, params);
        method.setAccessible(true);
        return method;
    }

    public Method getAccessMethod(Class<T> clazz, String methodName, Class<?> ... params) throws NoSuchMethodException {
        return clazz.getMethod(methodName, params);
    }

    public Field getField(Class<T> clazz, String fieldName) throws NoSuchFieldException {
        return clazz.getField(fieldName);
    }


    public Field getPrivateField(Class<T> clazz, String fieldName) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

    public static void main(String[] args) {

    }

}
