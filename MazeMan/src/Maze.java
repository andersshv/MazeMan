import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Maze extends JPanel implements ActionListener {

    private Timer timer;
    private Man man;
    private ArrayList<Ghost> ghosts;
    private ArrayList<Skeleton> skeletons;
    private Key key1;
    private Key key2;
    private Door door;
    private Heart hearts;
    private GameTitle gameTitle;
    private EntrancePortal entrancePortal;
    private ArrayList<Wall> walls;
    private Heart heart1;
    private Heart heart2;
    private Heart heart3;
    private HalfHeart halfheart1;
    private HalfHeart halfheart2;
    private HalfHeart halfheart3;
    private int energy = 6;
    private boolean ingame;
    private boolean gameWon;
    private final int IMAN_X = 690;
    private final int IMAN_Y = 710;
    private final int B_WIDTH = 1440;
    private final int B_HEIGHT = 900;
    private final int DELAY = 15;
    private final int[][] posGhosts = { 
    		{105, 220}, {245, 300}, {415, 420}, {633, 467}, 
    		{105, 220}, {245, 300}, {415, 420}, {633, 467},
    		{105, 220}, {245, 300}, {415, 420}, {633, 467},
    		{501, 607}, {1265, 300}, {1015, 420}, {913, 567}, 
    		{251, 267}, {1065, 333}, {1215, 520}, {813, 367}, 
    		{351, 467}, {1165, 533}, {715, 523}, {613, 367},
    		{251, 267}, {1065, 333}, {1215, 520}, {813, 367}, 
    		{351, 467}, {1165, 533}, {715, 523}, {613, 367},
    		{351, 467}, {1165, 533}, {715, 523}, {613, 367},
    		{251, 267}, {1065, 333}, {1215, 520}, {813, 367}, 
    		{351, 467}, {1165, 533}, {715, 523}, {613, 367}, };
    private final int[][] posSkeletons = { 
    		{831, 411}, {231, 267}, {743, 343}, {501, 231},
    		{531, 211}, {931, 467}, {843, 343}, {601, 331},
    		{531, 211}, {931, 467}, {843, 343}, {601, 331} };
    
    private final int[][] posWalls = {
    		//{700, 0, 1, 600},
    									
			// Horizontal Walls 
    		// {x, y, width, height}
    		{650, 750, 100, 5},    		
    		{650, 700, 25, 5},
    		{725, 700, 25, 5},
    		{725, 650, 625, 5},
    		{50, 650, 625, 5},
    		{50, 150, 1300, 5},
    		{50, 400, 100, 5},
    		{150, 200, 200, 5},
    		{150, 500, 300, 5},
    		{725, 600, 300, 5},
    		{450, 400, 800, 5},
    		{300, 250, 300, 5},
    		
    		// Vertical Walls 
    		// {x, y, width, height}
    		{50, 150, 5, 500}, 
    		{1345, 150, 5, 500}, 
    		{650, 700, 5, 50},
    		{745, 700, 5, 50},
    		{670, 650, 5, 50},
    		{725, 650, 5, 50},
    		{100, 150, 5, 200},
    		{300, 200, 5, 400},
    		{1250, 250, 5, 300},
    		{550, 250, 5, 400},
    		{950, 150, 5, 200},
    		{725, 450, 5, 150},
    		
    };	
    
    public Maze() {
    	
        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
        initMaze();

        man = new Man(IMAN_X, IMAN_Y);

        initMonsters();
        
        gameTitle = new GameTitle(550, 25);
        key1 = new Key(450, 300);
        key2 = new Key(900, 75);
        key2.setVisible(false);
        door = new Door(55, 155);
        heart1 = new Heart(1000,75);
        heart2 = new Heart(1100,75);
        heart3 = new Heart(1200,75);
        halfheart1 = new HalfHeart(1000,75);
        halfheart1.setVisible(false);
        halfheart2 = new HalfHeart(1100,75);
        halfheart2.setVisible(false);
        halfheart3 = new HalfHeart(1200,75);
        halfheart3.setVisible(false);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initMaze() {
    	walls = new ArrayList<>();

        for (int[] p : posWalls) {
            walls.add(new Wall(p[0], p[1], p[2], p[3]));
        }        
	}

	public void initMonsters() {
        ghosts = new ArrayList<>();
        skeletons = new ArrayList<>();

        for (int[] p : posGhosts) {
            ghosts.add(new Ghost(p[0], p[1]));
        }
        
        for (int[] p : posSkeletons) {
            skeletons.add(new Skeleton(p[0], p[1]));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {

            drawObjects(g);

        } else if(gameWon) {
        	
        	drawGameWon(g);
        	
        } else {

            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawGameWon(Graphics g) {
    	String msg = "You found your way out! Yay!";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
		
	}

	private void drawObjects(Graphics g) {
    	
    	g.setColor(Color.WHITE);        	
    	for (Wall a : walls) {
            if (a.isVisible()) {
                g.fillRect(a.x, a.y, a.width, a.height);
            }
        }        

               
        
        if (gameTitle.isVisible()) {
            g.drawImage(gameTitle.getImage(), gameTitle.getX(), gameTitle.getY(),
                    this);
        }
        
        if (door.isVisible()) {
            g.drawImage(door.getImage(), door.getX(), door.getY(),
                    this);
        }
        
        if (halfheart1.isVisible()) {
            g.drawImage(halfheart1.getImage(), halfheart1.getX(), halfheart1.getY(),
                    this);
        }
        if (halfheart2.isVisible()) {
            g.drawImage(halfheart2.getImage(), halfheart2.getX(), halfheart2.getY(),
                    this);
        }
        if (halfheart3.isVisible()) {
            g.drawImage(halfheart3.getImage(), halfheart3.getX(), halfheart3.getY(),
                    this);
        }
        if (heart1.isVisible()) {
            g.drawImage(heart1.getImage(), heart1.getX(), heart1.getY(),
                    this);
        }
        if (heart2.isVisible()) {
            g.drawImage(heart2.getImage(), heart2.getX(), heart2.getY(),
                    this);
        }
        if (heart3.isVisible()) {
            g.drawImage(heart3.getImage(), heart3.getX(), heart3.getY(),
                    this);
        } 
        if (key1.isVisible()) {
            g.drawImage(key1.getImage(), key1.getX(), key1.getY(),
                    this);
        } 
        if (key2.isVisible()) {
            g.drawImage(key2.getImage(), key2.getX(), key2.getY(),
                    this);
        } 
        
        for (Ghost a : ghosts) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
        }
        
        for (Skeleton a : skeletons) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
        }  
        
        if (man.isVisible()) {
            g.drawImage(man.getImage(), man.getX(), man.getY(),
                    this);
        }
    }

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();
        
        moveSprite(man);

        for (Ghost ghost : ghosts) {
			moveSprite(ghost);
        } 
        
        for (Skeleton skeleton : skeletons) {
			moveSprite(skeleton);
        } 

        checkCollisionsWithMonstersAndStuff();

        repaint();
    }    

	private void inGame() {        
        if (!ingame) {
            timer.stop();
        }
    }

	private void moveSprite(MoveableSprite sprite) {
    	int ddx = sprite.dx;
    	sprite.moveX(ddx);    
    	Rectangle rsprite = sprite.getBounds();
		for (Wall wall : walls) {
			Rectangle rw = wall.getBounds();
			if (rsprite.intersects(rw)) {
				sprite.moveX(ddx * -1);
				sprite.hasHitWall = true;
				break;
			}
		}
		
		int ddy = sprite.dy;
		sprite.moveY(ddy);    
    	rsprite = sprite.getBounds();
		for (Wall wall : walls) {
			Rectangle rw = wall.getBounds();
			if (rsprite.intersects(rw)) {
				sprite.moveY(ddy * -1);
				sprite.hasHitWall = true;
				break;
			}
		}		
	}	

    public void checkCollisionsWithMonstersAndStuff() {

        Rectangle rman = man.getBounds();

        Ghost coll = null;
        for (Ghost ghost : ghosts) {
            Rectangle rg = ghost.getBounds();

            if (rman.intersects(rg)) {
                //man.setVisible(false);
                ghost.setVisible(false);
                coll = ghost;
                energy -= 1;
            }
        }
        ghosts.remove(coll);
        
        Skeleton colll = null;
        for (Skeleton skeleton : skeletons) {
            Rectangle rs = skeleton.getBounds();

            if (rman.intersects(rs)) {
                //man.setVisible(false);
                skeleton.setVisible(false);
                colll = skeleton;
                energy -= 2;
            }
        }
        skeletons.remove(colll);
        
        updateHearts();
        
        Rectangle rk = key1.getBounds();

        if (rman.intersects(rk)) {
            key1.setVisible(false);
            key2.setVisible(true);
        }
        
        Rectangle rd = door.getBounds();

        if (rman.intersects(rd) && key2.isVisible()) {
        	gameWon = true;
        	ingame = false;
        }
        
    }

    private void updateHearts() {
    	if(energy <= 0)
    		ingame = false;
    	if(energy == 6) {
    		heart1.setVisible(true);
    		heart2.setVisible(true);
    		heart3.setVisible(true);
    		halfheart1.setVisible(false);
    		halfheart2.setVisible(false);
    		halfheart3.setVisible(false);
    	} else if (energy == 5) {
    		heart1.setVisible(true);
    		heart2.setVisible(true);
    		heart3.setVisible(false);
    		halfheart1.setVisible(false);
    		halfheart2.setVisible(false);
    		halfheart3.setVisible(true);
    	} else if (energy == 4) {
    		heart1.setVisible(true);
    		heart2.setVisible(true);
    		heart3.setVisible(false);
    		halfheart1.setVisible(false);
    		halfheart2.setVisible(false);
    		halfheart3.setVisible(false);
    	} else if (energy == 3) {
    		heart1.setVisible(true);
    		heart2.setVisible(false);
    		heart3.setVisible(false);
    		halfheart1.setVisible(false);
    		halfheart2.setVisible(true);
    		halfheart3.setVisible(false);
    	} else if (energy == 2) {
    		heart1.setVisible(true);
    		heart2.setVisible(false);
    		heart3.setVisible(false);
    		halfheart1.setVisible(false);
    		halfheart2.setVisible(false);
    		halfheart3.setVisible(false);
    	} else if (energy == 1) {
    		heart1.setVisible(false);
    		heart2.setVisible(false);
    		heart3.setVisible(false);
    		halfheart1.setVisible(true);
    		halfheart2.setVisible(false);
    		halfheart3.setVisible(false);
    	} else {
    		heart1.setVisible(false);
    		heart2.setVisible(false);
    		heart3.setVisible(false);
    		halfheart1.setVisible(false);
    		halfheart2.setVisible(false);
    		halfheart3.setVisible(false);
    	}
		
	}

	private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        	man.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	int kc = e.getKeyCode();
        	if (kc == KeyEvent.VK_ESCAPE)
    			System.exit(0);
    		else if (kc == KeyEvent.VK_R) {
    			timer.stop();
    			initBoard();
    		}
    		else
    			man.keyPressed(e);
        }
    }
}