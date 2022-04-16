package sk.stuba.fei.uim.oop.board;

import java.awt.*;

public class Square {

    private Color background;

    public Circle getStone() {
        return stone;
    }

    private Circle stone;

    private Circle possibleMove;

    public Circle getPossibleMove() { return possibleMove; }

    public void setPossibleMove(Circle possibleMove) {
        this.possibleMove = possibleMove;
    }

    public void setBackground(final Color background) {
        this.background = background;
    }

    public void setCircle(Circle stone) {
        this.stone = stone;
    }

    public void printSquare(final Graphics g, int i, int j, int height, int width, int boardSize)
    {
        g.setColor(this.background);
        g.fillRect(i * width / boardSize, j * height / boardSize, width / boardSize,height / boardSize);

        if (stone != null)
        {
            stone.printCircle(g, i, j, height, width, boardSize);
        }
        else if (possibleMove != null)
        {
            possibleMove.printCircle(g, i, j, height, width, boardSize);
        }
    }



}

