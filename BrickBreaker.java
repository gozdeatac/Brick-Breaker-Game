//GOZDE ATAC 201911010 Brick-Breaker Game
package project;

//these libraries used to determine time and etc. according to use StdDraw 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Timer;

public class BrickBreaker extends JPanel implements KeyListener, ActionListener{ //polymorphism
	private boolean play=false;
	private int score=0; //first score
	private int totalBricks=21; //number of bricks
	private Timer timer;
	private int delay=8;
	
	private int playerX=300; //paddle's first coordinate
	private int ballX=120; //ball's first coordinate
	private int ballY=300;
	private int ballXl=-1; //ball's last coordinate
	private int ballYl=-2;
	
	private BrickCoordinate map;
	
	public BrickBreaker(){		
		//game start with direction keys
		map=new BrickCoordinate(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics obj){    		
		// background
		obj.setColor(Color.black);
		obj.fillRect(1, 1, 690, 590);
		
		// drawing map
		map.draw((Graphics2D) obj);
		
		// borders
		obj.setColor(Color.MAGENTA);
		obj.fillRect(0, 0, 3, 590);
		obj.fillRect(0, 0, 690, 3);
		obj.fillRect(0, 4, 3, 590);
		obj.fillRect(690, 0, 3, 590);
		
		// the scores 		
		obj.setColor(Color.MAGENTA);
		obj.setFont(new Font("arial",Font.BOLD, 25));
		obj.drawString(" " + score, 570, 40);
		
		// the paddle
		obj.setColor(Color.ORANGE);
		obj.fillRect(playerX, 550, 100, 20);
		
		// the ball
		obj.setColor(Color.CYAN);
		obj.fillOval(ballX, ballY, 25, 25);
	
		// when you won the game
		if(totalBricks==0){
			 play=false;
             ballXl=-1;
     		 ballYl=-2;
     		 obj.setColor(Color.MAGENTA);
     		 obj.setFont(new Font("arial",Font.BOLD, 30));
     		 obj.drawString("Congrats, You Won!", 190,300);
             
     		 obj.setColor(Color.MAGENTA);
     		 obj.setFont(new Font("arial",Font.BOLD, 25));           
     		 obj.drawString("Press (Enter) to Restart",200,340);  
		}
		
		// when you lose the game
		if(ballY>570){
	         play=false;
             ballXl=0;
     		 ballYl=0;
     		 obj.setColor(Color.RED);
     		 obj.setFont(new Font("arial",Font.BOLD, 30));
     		 obj.drawString("Game Over! Scores: "+score, 190,300);
             
     		 obj.setColor(Color.RED);
     		 obj.setFont(new Font("arial",Font.BOLD, 25));           
     		 obj.drawString("Press (Enter) to Restart", 200,340);        
        }
		obj.dispose();
	}	

	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode()==KeyEvent.VK_RIGHT) {	        
			if(playerX>=600){
				playerX=600;
			}
			
			else{
				moveRight();
			}
        }
		
		if (key.getKeyCode()==KeyEvent.VK_LEFT){          
			if(playerX<10){
				playerX=10;
			}
			else{
				moveLeft();
			}
        }		
		if (key.getKeyCode()==KeyEvent.VK_ENTER){          
			if(!play){
				ballX=120;
				ballY=300;
				ballXl=-1;
				ballYl=-2;
				playerX=300;
				score=0;
				totalBricks=21;
				map=new BrickCoordinate(3,7);
				
				repaint();
			}
        }		
	}
    
	//Override
	public void keyReleased(KeyEvent key) {}
	public void keyTyped(KeyEvent key) {}
	
	public void moveRight(){ 
		play=true;
		playerX+=15;	
	}
	
	public void moveLeft(){
		play=true;
		playerX-=15;	 	
	}
	
	public void actionPerformed(ActionEvent key) {
		timer.start();
		if(play){			
			if(new Rectangle(ballX, ballY, 25, 25).intersects(new Rectangle(playerX, 550, 100, 8))){
				ballYl=-ballYl;
			}
			
			int i=0;
			// check map collision with the ball		
			A: //when collision happens between brick and ball, get point 5
				while(i<map.map.length){
					for(int j=0; j<map.map[0].length; j++){
						if(map.map[i][j]>0){
							//getting point
							int brickX=j*map.width+80;
							int brickY=i*map.height+60;
							int brickWidth=map.width;
							int brickHeight=map.height;

							Rectangle rectangle=new Rectangle(brickX, brickY, brickWidth, brickHeight);
							Rectangle ballRect=new Rectangle(ballX, ballY, 25, 25);

							if(ballRect.intersects(rectangle)){
								map.setBrickValue(0, i, j);
								totalBricks--;
								score+=5;

								// when ball hit right or left of brick
								if(ballX+19<= rectangle.x || ballX+1>=rectangle.x + rectangle.width){
									ballXl=-ballXl;
								}
								// when ball hits top or bottom of brick
								else{
									ballYl=-ballYl;
								}
								break A;
							}
						}
					}
					  i++;
				}
				
			ballX+=ballXl;
			ballY+=ballYl;
			
			if(ballX<0){
				ballXl=-ballXl;
			}
			if(ballY<0){
				ballYl=-ballYl;
			}
			if(ballX>660){
				ballXl=-ballXl;
			}		
			
			repaint();		
		}
	}
}
