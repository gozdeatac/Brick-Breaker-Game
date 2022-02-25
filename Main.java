//GOZDE ATAC 201911010 Brick-Breaker Game
package project;

// this library for add to use functions in the JFrame
import javax.swing.JFrame;
 //first enter direction keys, game starts
public class Main {
	public static void main(String[] args) {
		JFrame obj=new JFrame();
        BrickBreaker gamePlay=new BrickBreaker();
	
		obj.setBounds(10, 10, 700, 600);
	    obj.setTitle(" Brick Breaker Game ");		
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);
	}

}
