package GUI;

import javax.swing.JFrame;
import gui.ViewStudents;

public class Main {
    public static void main(String[] args) {
        // إنشاء نافذة رئيسية
        JFrame frame = new JFrame("Student Management System - View Students");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setLocationRelativeTo(null); // يجعلها في منتصف الشاشة

        // إنشاء صفحة ViewStudents
        ViewStudents viewPanel = new ViewStudents();
        frame.add(viewPanel);

        // عرض النافذة
        frame.setVisible(true);
    }
}
