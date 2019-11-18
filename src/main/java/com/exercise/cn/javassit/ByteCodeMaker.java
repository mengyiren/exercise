package com.exercise.cn.javassit;

import javassist.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yiren.meng
 * @date 2019/11/12
 */
public class ByteCodeMaker {
    public static void main(String[] args) {
        try {
            ClassPool classPool = ClassPool.getDefault();
            classPool.insertClassPath(new ClassClassPath(ByteCodeMaker.class));
            CtClass cc = classPool.makeClass("com.exercise.cn.javassit.Point");
            cc.addMethod(CtNewMethod.make("public void sayHello(){\r\n" + "System.out.println(\"hello world\");\r\n" + "}", cc));
            Class<?> aClass = cc.toClass();
            Object instance = aClass.newInstance();
            Method sayHello = aClass.getMethod("sayHello");
            sayHello.invoke(instance);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | CannotCompileException | InstantiationException e) {
            e.printStackTrace();
        }

    }
}
