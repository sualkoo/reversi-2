package sk.stuba.fei.uim.oop.board;

import java.awt.*;

public class Circle {

    Color color;


    public Color getColor() { return color; }

    public void setColor(final Color color) {
        this.color = color;
    }

    public void printCircle(final Graphics g, int i, int j, int height, int width, int boardSize)
    {
        g.setColor(this.color);
        g.fillOval(i * width / boardSize,j * height / boardSize,width / boardSize,height / boardSize);
    }
}
