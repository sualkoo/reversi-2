package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.board.Circle;
import sk.stuba.fei.uim.oop.board.GameBoard;
import java.awt.*;
import javax.swing.*;

public class Game {

    public Player getCurrPlayer() {
        return currPlayer;
    }

    private Player currPlayer;
    private Player player1;
    private Player player2;

    private GameBoard gb;
    private JFrame frame;

    public GameBoard getGb() { return gb; }

    public Game() {
        SwingUtilities.invokeLater(() -> {
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            createAndShowGUI();
        });
    }

    private void toggleCurrPlayer() {
        if (this.currPlayer == this.player1) {
            this.currPlayer = this.player2;
        } else {
            this.currPlayer = this.player1;
        }
    }

    public void nextRound() {
        this.toggleCurrPlayer();
        this.resetPossibleMoves();
        this.paintPossibleMoves();
        frame.repaint();
    }

    // Params x and y here are real coordinates of GameBoard
    public void putStone(int coordX, int coordY) {
        int x = this.gb.translateX(coordX);
        int y = this.gb.translateY(coordY);
        var square = this.gb.getSquare(x,y);
        if (square.getPossibleMove() == null) {
            return;
        }
        Circle circ = new Circle();
        circ.setColor(this.currPlayer.getColor());
        square.setCircle(circ);

        this.changeNeighbours(x, y, this.currPlayer.getColor());

        this.nextRound();
    }

    private void changeNeighbours(int x, int y, Color color) {
        int[][] directions = {{1, 1}, {1, 0}, {1, -1}, {0, 1}, {0, -1}, {-1, 1}, {-1, 0}, {-1, -1}};
        for (var d : directions) {
            changeNeighboursInDirection(x + d[0], y + d[1], d[0], d[1], color);
        }
    }

    private boolean changeNeighboursInDirection(int x, int y, int deltaX, int deltaY, Color color) {
        if (!isValid(x) || !isValid(y)) {
            return false;
        }
        Circle stone2 = this.gb.c1squares[y][x].getStone();
        if (stone2 == null) {
            return false;
        }
        if (stone2.getColor().equals(color)) {
                return true;
        }

        if (changeNeighboursInDirection(x + deltaX, y + deltaY, deltaX, deltaY, color)) {
            stone2.setColor(color);
            return true;
        }
        return false;
    }

    private void resetPossibleMoves() {
        for (int i = 0; i < this.gb.c1squares.length; i++) {
            for (int j = 0; j < this.gb.c1squares[i].length; j++) {
                this.gb.c1squares[i][j].setPossibleMove(null);
            }
        }
    }

    private void paintPossibleMoves() {
        for (int i = 0; i < this.gb.c1squares.length; i++) {
            for (int j = 0; j < this.gb.c1squares[i].length; j++) {
                // Check if stone is present at square and has current color
                var stone = this.gb.c1squares[i][j].getStone();
                if (stone != null && stone.getColor().equals(this.currPlayer.getColor())) {
                    checkAllDirections(j, i);
                }
            }
        }
    }

    private void checkAllDirections(int x, int y) {
        var stone = this.gb.c1squares[y][x].getStone();
        int[][] directions = {{1, 1}, {1, 0}, {1, -1}, {0, 1}, {0, -1}, {-1, 1}, {-1, 0}, {-1, -1}};
        for (var d : directions) {
            checkDirection(x, y, d[0], d[1], stone);
        }
    }

    private void checkDirection(int x, int y, int deltaX, int deltaY, Circle stone) {
        x += deltaX;
        y += deltaY;
        var currColor = stone.getColor();
        var flag = false;
        while (isValid(x) && isValid(y)) {
            Circle stone2 = this.gb.c1squares[y][x].getStone();
            if (stone2 == null) {
                if (flag) {
                    Circle circ = new Circle();
                    circ.setColor(Color.DARK_GRAY);
                    this.gb.c1squares[y][x].setPossibleMove(circ);
                }
                return;
            }
            if (stone2.getColor().equals(currColor)) {
                return;
            }
            flag = true; // stone2 has different color
            x += deltaX;
            y += deltaY;
        }
    }

    private boolean isValid(int c) {
        return 0 <= c && c < this.gb.c1squares.length;
    }

    private void createAndShowGUI() {
        MyAdapter mouse = new MyAdapter(this);
        this.player1 = new Player(Color.WHITE);
        this.player2 = new Player(Color.BLACK);
        this.currPlayer = this.player1;
        this.gb = new GameBoard();
        this.frame = new JFrame("Othello/Reversi");

        frame.add(this.gb.getGui());
        frame.setLocationByPlatform(true);
        frame.setMinimumSize(frame.getSize());
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(900,900));
        frame.setMinimumSize(new Dimension(900,900));
        frame.setLocation(50,50);
        frame.pack();
        frame.setVisible(true);
        frame.addMouseListener(mouse);

        this.paintPossibleMoves();
    }
}
