package com.exercise.cn.asm;

import org.springframework.asm.ClassReader;
import org.springframework.asm.ClassWriter;
import org.springframework.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yiren.meng
 * @date 2019/11/18
 */
public class Generator {
    public static void main(String[] args) {
        try {
            ClassReader classReader = new ClassReader(Account.class.getCanonicalName());
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            AddSecurityCheckClassAdapter classAdapter = new AddSecurityCheckClassAdapter(Opcodes.ASM7, classWriter);
            classReader.accept(classAdapter,ClassReader.SKIP_DEBUG);
            byte[] bytes = classWriter.toByteArray();
            File file = new File(Generator.class.getResource("").getPath()+"\\Account.class");
            FileOutputStream os = new FileOutputStream(file);
            os.write(bytes);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
