package handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.Game;
import main.Sound;

public class KeyHandler implements KeyListener {

    private Sound sound;

    public KeyHandler(Sound sound) {
        this.sound = sound;
        sound.setFile(0); // Load the sound file
    }
    

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Game.bird.setVelY(-6);
            sound.play(); 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    } 
}
