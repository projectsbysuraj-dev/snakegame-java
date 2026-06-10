import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    int x = 100;
    int y = 100;
    boolean gameOver = false;

    int speed = 20;
    char direction = 'R';
    int foodX = 200;
    int foodY = 200;
    int bodyParts = 3;

    int[] snakeX = new int[100];
    int[] snakeY = new int[100];

    Random random = new Random();

    Timer timer;

    public GamePanel() {

        this.setBackground(Color.BLACK);

        timer = new Timer(100, this);
        timer.start();
        this.setFocusable(true);
        this.addKeyListener(this);
        snakeX[0] = 100;
        snakeY[0] = 100;


        this.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                if(gameOver) {

                    int mx = e.getX();
                    int my = e.getY();

                    if(mx >= 220 && mx <= 360 &&
                            my >= 280 && my <= 320) {

                        restartGame();
                    }
                }
            }
        });
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);

        for(int i = 0; i < 600; i += 20) {

            g.drawLine(i, 0, i, 600);
            g.drawLine(0, i, 600, i);
        }

        g.setColor(new Color(255, 50, 50));
        g.fillOval(foodX, foodY, 20, 20);

        for(int i = 0; i < bodyParts; i++) {

            if(i == 0) {
                g.setColor(Color.GREEN);
            }
            else {
                g.setColor(Color.YELLOW);
            }

            g.fillRect(snakeX[i], snakeY[i], 20, 20);
        }
        if(gameOver) {

            // White Box
            g.setColor(Color.WHITE);
            g.fillRect(170, 200, 250, 150);

            // Black Border
            g.setColor(Color.BLACK);
            g.drawRect(170, 200, 250, 150);

            // GAME OVER Text
            g.setFont(new Font("Arial", Font.BOLD, 28));
            g.drawString("GAME OVER ", 200, 250);

            // Restart Button Box
            g.drawRect(220, 280, 140, 40);

            // Restart Text
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("RESTART", 245, 305);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = bodyParts; i > 0; i--) {

            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        if(direction == 'R') {
            snakeX[0] = snakeX[0] + speed;
            snakeY[0] = snakeY[0];
        }

        if(direction == 'L') {
            snakeX[0] = snakeX[0] - speed;
            snakeY[0] = snakeY[0];
        }

        if(direction == 'U') {
            snakeY[0] = snakeY[0] - speed;
            snakeX[0] = snakeX[0];
        }

        if(direction == 'D') {
            snakeY[0] = snakeY[0] + speed;
            snakeX[0] = snakeX[0];
        }

        if(snakeX[0] == foodX && snakeY[0] == foodY) {

            bodyParts++;

            foodX = random.nextInt(25) * 20;
            foodY = random.nextInt(25) * 20;
        }
        if(snakeX[0] < 0 || snakeX[0] >= 600 ||
                snakeY[0] < 0 || snakeY[0] >= 600) {

            gameOver = true;

            timer.stop();
        }
        for(int i = 1; i < bodyParts; i++) {

            if(snakeX[0] == snakeX[i] &&
                    snakeY[0] == snakeY[i]) {

                gameOver = true;

                timer.stop();
            }
        }

        repaint();
    }
    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT && direction != 'L') {
            direction = 'R';
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT && direction != 'R') {
            direction = 'L';
        }

        if(e.getKeyCode() == KeyEvent.VK_UP && direction != 'D') {
            direction = 'U';
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN && direction != 'U') {
            direction = 'D';
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    public void restartGame() {

        bodyParts = 3;

        snakeX = new int[100];
        snakeY = new int[100];

        snakeX[0] = 100;
        snakeY[0] = 100;

        direction = 'R';

        gameOver = false;

        foodX = random.nextInt(25) * 20;
        foodY = random.nextInt(25) * 20;

        timer.start();

        repaint();
    }
}
