package supers;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected float velX;
    protected float velY;
    
    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
