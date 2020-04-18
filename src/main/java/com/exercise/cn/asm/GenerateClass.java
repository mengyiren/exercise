package com.exercise.cn.asm;


import org.springframework.asm.ClassWriter;
import org.springframework.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * @author yiren.meng
 * @date 2019/11/18
 */
public class GenerateClass {
    public static void main(String[] args) {
        try {
            ClassWriter cw = new ClassWriter(0);
            cw.visit(Opcodes.V1_8, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "com.exercise.cn.asm.GenInterface"
                    , null, "java.lang.Object", new String[]{"java.lang.Iterable"});
            cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, 1);
            cw.visitMethod(ACC_PUBLIC+ACC_ABSTRACT,"compareTo","(Ljava/lang/Object;)I",null,null);
            cw.visitEnd();
            byte[] bytes = cw.toByteArray();
            File file = new File(GenerateClass.class.getResource("").getPath()+"\\GenInterface.class");
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
