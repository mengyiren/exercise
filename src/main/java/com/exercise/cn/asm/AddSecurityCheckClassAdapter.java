package com.exercise.cn.asm;


import org.springframework.asm.ClassVisitor;
import org.springframework.asm.MethodVisitor;

import static org.springframework.asm.Opcodes.ASM7;

/**
 * @author yiren.meng
 * @date 2019/11/18
 */
public class AddSecurityCheckClassAdapter extends ClassVisitor {

    public AddSecurityCheckClassAdapter(int api) {
        super(api);
    }

    public AddSecurityCheckClassAdapter(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        MethodVisitor wrappedMv = mv;
        if (mv != null) {
            if ("operation".equals(name)) {
                wrappedMv = new AddSecurityCheckMethodAdapter(ASM7,mv);
            }
        }
        return wrappedMv;
    }
}
