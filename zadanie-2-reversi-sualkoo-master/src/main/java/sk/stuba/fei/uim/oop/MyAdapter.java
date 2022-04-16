package sk.stuba.fei.uim.oop;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MyAdapter extends MouseAdapter
{
    private Game game;

    public MyAdapter(Game game) {
        this.game = game;
    }

    public void mousePressed(MouseEvent me) {

        int x = me.getX();
        int y = me.getY();

        this.game.putStone(x, y);

    }
}

