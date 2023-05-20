package handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;

public class KeyHandler implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Game.bird.setVelY(-6);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    } 
}
