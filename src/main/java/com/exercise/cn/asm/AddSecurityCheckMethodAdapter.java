package com.exercise.cn.asm;

import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Opcodes;

/**
 * @author yiren.meng
 * @date 2019/11/18
 */
public class AddSecurityCheckMethodAdapter extends MethodVisitor {
    public AddSecurityCheckMethodAdapter(int api) {
        super(api);
    }

    public AddSecurityCheckMethodAdapter(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    @Override
    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC,"com.exercise.cn.asm.SecurityChecker","checkSecurity","()V",false);
    }
}
