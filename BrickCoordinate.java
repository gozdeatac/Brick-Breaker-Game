//GOZDE ATAC 201911010 Brick-Breaker Game

package project;
//these libraries used to determine shape, coordinate and color according to use StdDraw
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BrickCoordinate{
	public int map[][];
	public int width; //brick width
	public int height; //brick height
	
	public BrickCoordinate(int x, int y){
		map=new int[x][y];		
		for(int[] map1:map){ // 0 to size of map
			int j=0;
			while(j<map[0].length){ // 0 to size of first map 
				map1[j]=1;
				j++;
			}			
		}
		
		width=250/x; 
		height=200/y;
	}	
	 
	public void draw(Graphics2D g){
		for(int i=0; i<map.length; i++){
			for(int j=0; j<map[0].length; j++){
				if(map[i][j]>0){
					//setting brick's square's color
					g.setColor(Color.MAGENTA);
					g.fillRect(j*width+80, i*height+60, width, height);
					
					//setting brick's bound's color
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.CYAN);
					g.drawRect(j*width+80, i*height+60, width, height);				
				}
			}
		}
	}
	
	public void setBrickValue(int value, int x, int y){
		map[x][y]=value;
	}
}
