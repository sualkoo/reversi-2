package sk.stuba.fei.uim.oop;

import java.awt.*;

public class Player
{
    private int score;
    private Color color;

    public Color getColor() {
        return color;
    }

    public Player(Color color)
    {
        this.score = 0;
        this.color = color;
    }
}
