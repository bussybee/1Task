package ru.vsu.cs.maslovaei;

import java.util.ArrayList;

public class Rectangle {
    protected class Point {
        protected int x;
        protected int y;

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private final ArrayList<Point> points = new ArrayList<>();

    public ArrayList<Point> getPoints() {
        return points;
    }

    private boolean arePointsEqual(Point newPoint, Point oldPoint) {
        int newPointX = newPoint.x;
        int newPointY = newPoint.y;
        int oldPointX = oldPoint.x;
        int oldPointY = oldPoint.y;
        return newPointX == oldPointX && newPointY == oldPointY;
    }

    private boolean isPointsAlreadyAdded(Point newPoint) {
        for (Point point : points) {
            if (arePointsEqual(newPoint, point)) {
                return true;
            }
        }
        return false;
    }

    public void addPoint(int x, int y) throws Exception {
        Point newPoint = new Point(x, y);
        if (!isPointsAlreadyAdded(newPoint)) {
            points.add(newPoint);
        } else {
            throw new Exception("Точка уже добавлена");
        }
    }

    private double countSquare() {
        double S;
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            int X1 = points.get(i).x;
            int Y1 = points.get(i).y;
            int X2 = points.get(i + 1).x;
            int Y2 = points.get(i + 1).y;
            sum1 += X1 * Y2;
            sum2 += X2 * Y1;
        }
        int lastPointX = points.get(points.size() - 1).x;
        int lastPointY = points.get(points.size() - 1).y;
        int firstPointX = points.get(0).x;
        int firstPointY = points.get(0).y;
        S = (double) 1 / 2 * (sum1 - sum2 + lastPointX * firstPointY - firstPointX * lastPointY);
        return S;
    }

    public double getSquare() {
        return Math.abs(countSquare());
    }

    public double getPerimeter() {
        int p = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            p += distanceBetween(points.get(i), points.get(i + 1));
        }
        return p + distanceBetween(points.get(0), points.get(points.size() - 1));
    }

    public double distanceBetween(Point p1, Point p2) {
        int x1 = p1.x;
        int y1 = p1.y;
        int x2 = p2.x;
        int y2 = p2.y;
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public void moving(int mx, int my) {
        for (Point point : points) {
            point.setX(point.x + mx);
            point.setY(point.y + my);
        }
    }

    public void scale(double m) {
        if (m < 0) {
            return;
        }
        Point center = findCenter();
        moving(-center.x, -center.y);
        for (Point point : points) {
            int newX = (int) (point.x * m);
            point.setX(newX);
            int newY = (int) (point.y * m);
            point.setY(newY);
        }
        moving(center.x, center.y);
    }

    private Point findCenter() {
        int sumX = 0;
        int sumY = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            int x1 = points.get(i).x;
            int y1 = points.get(i).y;
            int x2 = points.get(i + 1).x;
            int y2 = points.get(i + 1).y;
            sumX += (x1 + x2) * (x1 * y2 - x2 * y1);
            sumY += (y1 + y2) * (x1 * y2 - x2 * y1);
        }
        int lastPointX = points.get(points.size() - 1).x;
        int lastPointY = points.get(points.size() - 1).y;
        int firstPointX = points.get(0).x;
        int firstPointY = points.get(0).y;
        int centerX = (int) ((sumX + (lastPointX + firstPointX) * (lastPointX * firstPointY - firstPointX * lastPointY))
                / (6 * countSquare()));
        int centerY = (int) ((sumY + (lastPointY + firstPointY) * (lastPointX * firstPointY - firstPointX * lastPointY))
                / (6 * countSquare()));
        return new Point(centerX, centerY);
    }
}

