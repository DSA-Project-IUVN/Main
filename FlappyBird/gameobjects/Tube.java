package gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import supers.GameObject;
import enums.TubeType;
import handlers.ObjectHandler;
import loaders.GraphicsLoader;
import main.Game;
import main.Sound;

public class Tube extends GameObject {
    TubeType type;
    BufferedImage tubeBlock;
    BufferedImage tube;
    Sound scoreSound;

    public Tube(int x, int y, int width, int height, TubeType type) {
        super(x, y, width, height);

        this.velX = 3;
        this.type = type;

        tube = GraphicsLoader.loadGraphics("rcs/tube.png");

        if (type == TubeType.BOTTOM) {
            tubeBlock = GraphicsLoader.loadGraphics("rcs/tubebottomdown.png");
        } else if (type == TubeType.TOP) {
            tubeBlock = GraphicsLoader.loadGraphics("rcs/tubebottomtop.png");
        }

        scoreSound = new Sound();
    }

    @Override
    public void tick() {
        x -= velX;

        if (x + width < 0) {
            ObjectHandler.removeObject(this);
            if (type == TubeType.BOTTOM) {
                Game.score += 1;
                scoreSound.setFile(1);
                scoreSound.play();
            }
        }
    }

    public void render(Graphics g) {
        if (type == TubeType.BOTTOM) {
            g.drawImage(tube, x, y, 72, height, null);
            g.drawImage(tubeBlock, x - 3, y, null);
        } else if (type == TubeType.TOP) {
            g.drawImage(tube, x, y, 72, height, null);
            g.drawImage(tubeBlock, x - 3, y + height - 36, null);
        }
    }
}
