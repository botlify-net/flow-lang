package net.botlify.flowlang;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Main {

  public static void main(String[] args) {
    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

    // Créez la classe HelloWorld
    cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "HelloWorld", null, "java/lang/Object", null);

    // Créez la méthode principale
    MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
    mv.visitCode();

    // Ajoutez l'instruction pour afficher "Hello, World!" sur la sortie standard
    mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    mv.visitLdcInsn("Hello, World!");
    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

    // Terminez la méthode
    mv.visitInsn(Opcodes.RETURN);
    mv.visitMaxs(2, 1);
    mv.visitEnd();

    // Terminez la classe
    cw.visitEnd();

    // Générez le bytecode sous forme d'octets
    byte[] bytecode = cw.toByteArray();

    // Écrivez le bytecode dans un fichier
    try {
      java.io.FileOutputStream fos = new java.io.FileOutputStream("HelloWorld.class");
      fos.write(bytecode);
      fos.close();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
  }

}