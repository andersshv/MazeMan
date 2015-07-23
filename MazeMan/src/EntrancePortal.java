public class EntrancePortal extends Sprite {

    private final int INITIAL_X = 400;

    public EntrancePortal(int x, int y) {
        super(x, y);

        initAlien();
    }

    private void initAlien() {

        loadImage("alien.png");
        setImageDimensions();
    }

    public void move() {

        if (x < 0) {
            x = INITIAL_X;
        }

        x -= 1;
    }
}