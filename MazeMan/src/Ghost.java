import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class Ghost extends MoveableSprite implements ActionListener {
	
    private static final int DELAY = 20;
	private Timer timer;

	public Ghost(int x, int y) {
        super(x, y);
        loadImage("ghost.png");
        setImageDimensions();
        
        dx = 1;
        dy = 0;
        
        timer = new Timer(DELAY, this);
        timer.start();
    }	

	@Override
	public void actionPerformed(ActionEvent e) {
		Random r = new Random();
		int i = 0;
		if(hasHitWall) {
			i = r.nextInt(6);
			hasHitWall = false; }
		else
			i = r.nextInt(50);
		if(i == 5) {
			i = r.nextInt(8);
			if(i == 0) {
				dx = -1;
				dy = -1;
			}
			if(i == 1) {
				dx = -1;
				dy = 0;
			}
			if(i == 2) {
				dx = -1;
				dy = 1;
			}
			if(i == 3) {
				dx = 0;
				dy = 1;
			}
			if(i == 4) {
				dx = 0;
				dy = -1;
			}
			if(i == 5) {
				dx = 1;
				dy = -1;
			}
			if(i == 6) {
				dx = 1;
				dy = 0;
			}
			if(i == 7) {
				dx = 1;
				dy = 1;
			}			
		}		
	}
    
}