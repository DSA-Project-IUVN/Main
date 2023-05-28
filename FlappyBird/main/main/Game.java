package main;

import java.net.ServerSocket;
import gameobjects.Bird;
import gameobjects.Ground;
import handlers.ObjectHandler;
import handlers.TubeHandler;
import handlers.KeyHandler;
import handlers.MouseHandler;
import loaders.GraphicsLoader;
import supers.Button;

// import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Game extends Canvas implements Runnable {
    
    public static final int WIDTH = 432;
    public static final int HEIGHT = 768;

    public boolean running;
    public static boolean gameover;

    public static int score;

    public static BufferedImage img_gameover;
    public static BufferedImage background;
    
    public static Ground ground;
    public static Bird bird;

    public static Button startButton;

    Thread thread;
    ServerSocket serverSocket;
    public static void main(String[] args) {
        new Window(WIDTH, HEIGHT, "Flappy Bird", new Game());
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
        run();
    }

    public void init() {

        Sound sound = new Sound();                      
        KeyHandler keyHandler = new KeyHandler(sound); 
        addKeyListener(keyHandler);
        addMouseListener(new MouseHandler());

        img_gameover = GraphicsLoader.loadGraphics("rcs/gameover.png");
        background = GraphicsLoader.loadGraphics("rcs/background.png");
        ground = new Ground();        
        
        bird = new Bird(50,50,51,36);

        startButton = new Button(Game.WIDTH / 2 - 156 / 2, 350, 156, 87, GraphicsLoader.loadGraphics("rcs/playbutton.png"));

    }

    public void tick() {

        if (!gameover) {
        ObjectHandler.tick();
        ground.tick();
       }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if( bs == null ) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(background,0,0,null);
        ground.render(g);

        // g.setColor(Color.WHITE);
        // g.fillRect(0,0, WIDTH, HEIGHT);

        ObjectHandler.render(g);

        if (gameover) {
            g.drawImage(img_gameover, Game.WIDTH / 2 - 280 / 2, 230, null);
            Game.startButton.render(g);
        }
        

        g.setFont(new Font("Arial", Font.BOLD, 26));
        g.setColor(Color.ORANGE);

        String s = Integer.toString(score);
        int textWidth = g.getFontMetrics().stringWidth(s);

        g.drawString(s, Game.WIDTH / 2 - textWidth / 2, 40);

        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        init();
        this.requestFocus();

        long pastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - pastTime) / ns;
            pastTime = now;

            while (delta > 0) {
                tick();
                updates++;
                
                render();
                frames++;

                delta--;
            }
            
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " | TICKS: " + updates);
                TubeHandler.tick();
                updates = 0;
                frames = 0;
            }
        }
    }
}
