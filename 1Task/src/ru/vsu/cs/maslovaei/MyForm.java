package ru.vsu.cs.maslovaei;

import javax.swing.*;
import java.awt.*;

public class MyForm {
    private JPanel mainPanel;
    private static JFrame frame;
    private final Rectangle rectangle = new Rectangle();
    private final Panel paint = new Panel();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        frame = new JFrame("Rectangle");
        frame.setContentPane(new MyForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    public MyForm() {
        mainPanel.setLayout(new GridLayout());

        try {
            rectangle.addPoint(200, 200);
            rectangle.addPoint(200, 300);
            rectangle.addPoint(400, 300);
            rectangle.addPoint(400, 200);
        }catch (Exception ignored){
        }

        mainPanel.add(paint);
        paint.addRectangle(rectangle);
        frame.repaint();

        System.out.println("Периметр: " + rectangle.getPerimeter());
        System.out.println("Площадь: " + rectangle.getSquare());

        rectangle.moving(70, 50);
        rectangle.scale(1.5);
        frame.repaint();

        System.out.println("Периметр после масштабирования: " + rectangle.getPerimeter());
        System.out.println("Площадь после масштабирования: " + rectangle.getSquare());

    }
}
