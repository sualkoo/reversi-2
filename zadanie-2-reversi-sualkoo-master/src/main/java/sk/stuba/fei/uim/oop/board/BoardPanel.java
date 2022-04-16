package sk.stuba.fei.uim.oop.board;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    Square[][] squares;

    public BoardPanel(final Square[][] squares) {

        this.squares = squares;
    }

    @Override
    public void paintComponent(final Graphics g) {

        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {

                Square currentSquare = squares[j][i];
                currentSquare.printSquare(g, i, j,height,width, squares.length);

                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    currentSquare.setBackground(Color.CYAN);
                } else {
                    currentSquare.setBackground(Color.BLUE);
                }
            }
        }
    }
}
