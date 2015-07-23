import java.awt.event.KeyEvent;

public class Man extends MoveableSprite {   

    public Man(int x, int y) {
        super(x, y);
        loadImage("man.png");
        setImageDimensions();
    }       
    
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;

    public void keyPressed(KeyEvent e) { 
    	
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	leftPressed = true;
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
        	rightPressed = true;
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
        	upPressed = true;
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
        	downPressed = true;
            dy = 2;
        }
        
        if (key == KeyEvent.VK_A) {
        	leftPressed = true;
            dx = -2;
        }

        if (key == KeyEvent.VK_D) {
        	rightPressed = true;
            dx = 2;
        }

        if (key == KeyEvent.VK_W) {
        	upPressed = true;
            dy = -2;
        }

        if (key == KeyEvent.VK_S) {
        	downPressed = true;
            dy = 2;
        } 
    }

    public void keyReleased(KeyEvent e) { 
    	
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	leftPressed = false;
        	if(rightPressed)
        		dx = 2;
        	else
        		dx = 0;        	
        }

        if (key == KeyEvent.VK_RIGHT) {
        	rightPressed = false;
        	if(leftPressed)
        		dx = -2;
        	else
        		dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
        	upPressed = false;
        	if(downPressed)
        		dy = 2;
        	else
        		dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
        	downPressed = false;
        	if(upPressed)
        		dy = -2;
        	else
        		dy = 0;
        } 
        
        if (key == KeyEvent.VK_A) {
        	leftPressed = false;
        	if(rightPressed)
        		dx = 2;
        	else
        		dx = 0;        	
        }

        if (key == KeyEvent.VK_D) {
        	rightPressed = false;
        	if(leftPressed)
        		dx = -2;
        	else
        		dx = 0;
        }

        if (key == KeyEvent.VK_W) {
        	upPressed = false;
        	if(downPressed)
        		dy = 2;
        	else
        		dy = 0;
        }

        if (key == KeyEvent.VK_S) {
        	downPressed = false;
        	if(upPressed)
        		dy = -2;
        	else
        		dy = 0;
        }  
    }
}