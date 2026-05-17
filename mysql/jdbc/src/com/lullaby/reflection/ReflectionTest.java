package com.lullaby.reflection;

public class ReflectionTest {

    public static void main(String[] args) {


    }

    private static void getClazz() {
        Class<Student> c1 = Student.class;
        System.out.println(c1.getName());
        Student student = new Student("张三", 20);
        Class<? extends Student> c2 = student.getClass();
        // 获取父类
        Class<? super Student> c3 = c1.getSuperclass();
        System.out.println(c3.getName());
        try {
            Class c4 = Class.forName("com.lullaby.reflection.Student");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Class c5 = Integer.TYPE;
        System.out.println(c5.getName());
    }
}
