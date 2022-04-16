package sk.stuba.fei.uim.oop.board;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameBoard extends JPanel{

    private JPanel board;

    public final Square[][] c1squares = new Square[8][8];

    public GameBoard() {
        initializeGui();
    }

    public final void initializeGui() {

        for (int i = 0; i < c1squares.length; i++) {
            for (int j = 0; j < c1squares[i].length; j++) {

                Square square = new Square();
                c1squares[i][j] = square;
            }
        }

        Circle bCirc1 = new Circle();
        bCirc1.setColor(Color.BLACK);
        Circle bCirc2 = new Circle();
        bCirc2.setColor(Color.BLACK);
        c1squares[c1squares.length/2-1][c1squares.length/2-1].setCircle(bCirc1);
        c1squares[c1squares.length/2][c1squares.length/2].setCircle(bCirc2);

        Circle wCirc1 = new Circle();
        wCirc1.setColor(Color.WHITE);
        Circle wCirc2 = new Circle();
        wCirc2.setColor(Color.WHITE);
        c1squares[c1squares.length/2-1][c1squares.length/2].setCircle(wCirc1);
        c1squares[c1squares.length/2][c1squares.length/2-1].setCircle(wCirc2);

        board = new BoardPanel(c1squares);
        board.setBorder(new EmptyBorder(5, 5, 5, 5));
    }

    public final JComponent getGui() {
        return board;
    }

    public int translateY(int y) {
        int height = board.getHeight();
        if (y >= height) {
            y = height - 1;
        }
        float squareHeight = (float)height/8;
        float tY = y/squareHeight;
        return (int)tY;
    }

    public int translateX(int x) {
        int width = board.getWidth();
        if (x >= width) {
            x = width - 1;
        }
        float squareWidth = (float)width/8;
        float tX = x/squareWidth;
        return (int)tX;
    }

    public Square getSquare(int x, int y)
    {
        return c1squares[y][x];
    }
}
