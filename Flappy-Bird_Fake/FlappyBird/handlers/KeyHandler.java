package handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.Game;
import main.Sound;

public class KeyHandler implements KeyListener {

    private Sound flap;
    
   
        public KeyHandler( Sound flap ) {
            this.flap = flap;
            flap.setFile(0);
            
        // Load the sound file
        }
       

    @Override
    public void keyTyped(KeyEvent e) {

    }
   
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Game.bird.setVelY(-5);
            flap.play();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    } 
   

}
