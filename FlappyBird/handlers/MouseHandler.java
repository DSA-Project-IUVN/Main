package handlers;

import supers.Button;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Game;

public class MouseHandler implements MouseListener {

    @Override
    public void mousePressed(MouseEvent e) {
        if (Button.checkCollision(e.getX(), e.getY(), Game.startButton)) {
            if (Game.gameover) {
                Game.startButton.pressed = true;
                ObjectHandler.list.clear();
                
                // Set the bird's x and y positions to the center of the screen
                int birdX = 50;
                int birdY = 50;
                Game.bird.setX(birdX);
                Game.bird.setY(birdY);
                
                ObjectHandler.addObject(Game.bird);
                Game.gameover = false;
                Game.startButton.pressed = false;
                Game.score = 0;
            }
        }
    }
    

    // Đống này ko xài
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
