import java.awt.EventQueue;
import javax.swing.JFrame;

public class MazeMan extends JFrame {

    public MazeMan() {
    	
        initUI();
    }
    
    private void initUI() {
        
        add(new Maze());
        
        setResizable(false);
        pack();
        System.out.println("Hej");
        
        setTitle("Collision");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("asdfasdf");
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MazeMan ex = new MazeMan();
                ex.setVisible(true);
            }
        });
    }
}