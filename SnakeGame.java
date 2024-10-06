import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;



public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private static class Tile{
        int x;
        int y;

        Tile(int x, int y){
            this.x = x;
            this.y = y;
        } // constructor
    } // class Tile

    int width;
    int height;
    int tileSize = 25;

    //Snake
    Tile snakeHead;
    ArrayList<Tile> snakeBody;

    //Food
    Tile snakeFood;
    Random here;

    // Game Logic
    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;

    SnakeGame(int width, int height){

        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(this.width, this.height));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();

        snakeFood = new Tile(10, 10);
        here = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 0;

        gameLoop = new Timer(100, this);
        gameLoop.start();

    } // constructor

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){

        //Food
        g.setColor(Color.red);
        //g.fillRect(snakeFood.x * tileSize, snakeFood.y * tileSize, tileSize, tileSize);
        g.fill3DRect(snakeFood.x * tileSize, snakeFood.y * tileSize, tileSize, tileSize, true);

        //Snake Head
        g.setColor(Color.green);
        //g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize, true);

        //Snake Body
        for (Tile snakePart : snakeBody) {
            //g.fillRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize);
            g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize, true);
        }

        //Score
        g.setFont(new Font("Georgia", Font.BOLD, 16));
        if(gameOver){
            g.setColor(Color.red);
            g.drawString("Game Over\n\n Final Score --> "+String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        }
        else{
            g.drawString("Score: "+ String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        }


    }

    public void placeFood(){
        snakeFood.x = here.nextInt(width/tileSize);
        snakeFood.y = here.nextInt(height/tileSize);
    }

    public boolean collision(Tile t1, Tile t2){
        return t1.x == t2.x && t1.y == t2.y;
    }

    public void move(){

        //Snake Growth
        if (collision(snakeHead, snakeFood)){
            snakeBody.add(new Tile(snakeFood.x, snakeFood.y));
            placeFood();
        }

        //Snake Tail
        for (int i = snakeBody.size()-1; i >= 0; i --){
            Tile snakePart = snakeBody.get(i);
            if (i == 0){
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else{
                Tile snakeTail = snakeBody.get(i-1);
                snakePart.x = snakeTail.x;
                snakePart.y = snakeTail.y;
            }
        }

        //Snake Head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        //Game Over conditions
        for (Tile snakePart : snakeBody) {
            //Collision with the head
            if (collision(snakeHead, snakePart)) {
                gameOver = true;
            }
        }

        if (snakeHead.x*tileSize < 0 || snakeHead.x*tileSize > width || snakeHead.y*tileSize < 0 || snakeHead.y*tileSize > height){
            gameOver = true;
        }

    }

    public void resetGame(){


    }

    @Override
    public void actionPerformed(ActionEvent e){
        move();
        repaint();
        if (gameOver){
            gameLoop.stop();
            resetGame();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
