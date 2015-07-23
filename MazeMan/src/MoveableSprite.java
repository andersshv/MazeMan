
public class MoveableSprite extends Sprite {

	public int dx;
	public int dy;
	
	public boolean hasHitWall = false;
	
	public MoveableSprite(int x, int y) {
		super(x, y);
	}	

    public void moveX(int ddx) {
        x += ddx;    
    }
    
    public void moveY(int ddy) {
        y += ddy;        
    }

}
