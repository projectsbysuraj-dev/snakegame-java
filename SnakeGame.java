import javax.swing.JFrame;

public class SnakeGame {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.add(new GamePanel());


        frame.setTitle("Snake Game");
        frame.setSize(600, 600);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);

        frame.setVisible(true);
    }
}