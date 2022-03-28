package ru.vsu.cs.maslovaei;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel {
    private ArrayList<Rectangle.Point> points;

    public void addRectangle(Rectangle r) {
        points = r.getPoints();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int height = this.getHeight();
        int width = this.getWidth();
        g.drawLine(0, height / 2, width, height / 2);
        g.drawLine(width / 2, 0, width / 2, height);
        g.setColor(Color.blue);

        try {
            for (int i = 0; i < points.size() - 1; i++) {
                int x1 = points.get(i).x;
                int y1 = points.get(i).y;
                int x2 = points.get(i + 1).x;
                int y2 = points.get(i + 1).y;
                g.drawLine(x1, y1, x2, y2);
            }
            Rectangle.Point First = points.get(0);
            Rectangle.Point Last = points.get(points.size() - 1);
            int x1 = Last.x;
            int y1 = Last.y;
            int x2 = First.x;
            int y2 = First.y;
            g.drawLine(x1, y1, x2, y2);
        } catch (NullPointerException e) {
            System.out.println("Прямоугольник не создан");
        }
    }
}
