import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class Skeleton extends MoveableSprite implements ActionListener {
	
    private static final int DELAY = 15;
	private Timer timer;

	public Skeleton(int x, int y) {
        super(x, y);
        loadImage("skeleton.png");
        setImageDimensions();
        
        dx = 0;
        dy = 2;
        
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
			i = r.nextInt(40);
		if(i == 5) {
			i = r.nextInt(8);
			if(i == 0) {
				dx = -2;
				dy = -2;
			}
			if(i == 1) {
				dx = -2;
				dy = 0;
			}
			if(i == 2) {
				dx = -2;
				dy = 2;
			}
			if(i == 3) {
				dx = 0;
				dy = 2;
			}
			if(i == 4) {
				dx = 0;
				dy = -2;
			}
			if(i == 5) {
				dx = 2;
				dy = -2;
			}
			if(i == 6) {
				dx = 2;
				dy = 0;
			}
			if(i == 7) {
				dx = 2;
				dy = 2;
			}			
		}		
	}
    
}