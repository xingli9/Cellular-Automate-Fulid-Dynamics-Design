package ui;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;


/**
 * A sample canvas that draws a rainbow of lines
 * @author MMUNSON
 */
public class BGCanvas extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(BGCanvas.class.getName());
    int lineSize = 10;
    private Color col = null;
    private long counter = 0L;
    int rule = 0;
	private FluidFrame ffa = null;
    /**
     * CellAutCanvas constructor
     */
	
	public BGCanvas() {
		
	}
	public BGCanvas(int i) {
		col = Color.WHITE;
		rule = i;
		//frame = this.farme;
		System.out.println("BGconvas");
		
	}

	/**
	 * The UI thread calls this method when the screen changes, or in response
	 * to a user initiated call to repaint();
	 */
	public void paint(Graphics g) {
		System.out.println("paint" + rule);
		if (rule == 1) {
						//delay(1000L);
			drawfG(g);	
		} else if (rule == 2) {
			System.out.println("in" + rule);
			drawArrow(g);
		} else if (rule == 0){
			drawBG(g); // Our Added-on drawing
		} else {
			
		}
		
		
    }
	
	/**
	 * Draw the CA graphics panel
	 * @param g
	 */
	public void drawBG(Graphics g) {
		log.info("Drawing BG " + counter++);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
		
		g2d.setColor(Color.BLUE);
		g2d.fillRect(0, 0, size.width, size.height);//填充颜色
		
		g2d.setColor(Color.RED);
		g2d.drawString("Please select a rule and press 'Start' button", 0, 15);//添加文字
		
		int maxRows = size.height / lineSize;
		int maxCols = size.width / lineSize;
		/*for (int i = 0; i < maxRows; i++) {
		   for (int j = 0; j < maxCols; j++) {
			   int redVal = validColor(j*5);
			   int greenVal = validColor(255-i*5);
			   int blueVal = validColor((i*5)-(j*2));
			   col = new Color(redVal, greenVal, blueVal);
			   // Draw box, one pixel less to create a black outline
			   int startx = j*lineSize;
			   int starty = i*lineSize;
			   int endx = startx + 10;
			   int endy = starty + 10;
			   paintLine( g2d, startx, starty, endx, endy, col); 
		   }
		}*/
	}
	
	public void drawfG(Graphics g) {
		//log.info("Drawing BG " + counter++);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
		
		//g2d.setColor(Color.BLACK);
		//g2d.fillRect(0, 0, size.width, size.height);//填充颜色
		//g2d.setColor(Color.RED);
		//g2d.drawString("BG 2D", 0, 10);//添加文字
		col = Color.GREEN;
		int maxRows = size.height / lineSize;
		int maxCols = size.width / lineSize - 1;
		int length = maxRows / 2 + maxCols;
		for (int i = 0; i <= maxRows; i++) {
			int starty = i*lineSize;
			int startx = maxCols*lineSize;
			if (i % 2 == 0) {
				paintLine( g2d, 0, starty, startx, starty, col);
			} else {
				paintLine( g2d, lineSize / 2, starty, startx + lineSize / 2, starty, col);
			}
		}
		int ix = 0;
		int iy = 0;
		int jx = 0;
		int jy = 0;
		for (int k = 0; k <= length - 1; k++) {

			if (ix <= maxCols - 1) {
				ix++;
			} else {
				if (iy == 0) {
					iy++;
				} else {
					iy += 2;
				}
				
			}
			if (jy <= maxRows - 1) {
				if (maxRows % 2 > 0 && jy == maxRows - 1) {
					jy++;
				} else {
					jy += 2;
				}
				
			} else {
				jx++;
			}
			int startx = ix * lineSize;
			int starty = iy * lineSize;
			if (iy != 0) 
				startx = ix * lineSize + lineSize / 2;
			int endx = jx * lineSize;
			int endy = jy * lineSize;
			if (jy % 2 > 0) 
				endx = jx * lineSize + lineSize / 2;
			paintLine(g2d, startx, starty, endx, endy, col);

		}
		ix = maxCols;
		iy = 0;
		jx = maxCols;
		jy = 1;
		for (int k = 0; k <= length; k++) {
			int startx = ix * lineSize;
			int starty = iy * lineSize;
			int endx = jx * lineSize + lineSize / 2;
			int endy = jy * lineSize;
			paintLine(g2d, startx, starty, endx, endy, col);
			
			if (ix >= 1) {
				ix--;
			} else {
				iy += 2;
			}
			if (jy <= maxRows - 1) {
				if (maxRows % 2 > 0 && jy == maxRows - 1) {
					jy++;
				} else {
					jy += 2;
				}
				
			} else {
				jx--;
			}


		}
	}
	
	public void drawArrow(Graphics g) {
		//log.info("Drawing Arrow " + counter++);
		System.out.println("in drawArrow");
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
		
		int maxRows = size.height / lineSize;
		int maxCols = size.width / lineSize;
		
		drawfG(g2d);
		for (int i = 0; i < maxRows; i++) {
		   for (int j = 0; j < maxCols; j++) {
			   int redVal = validColor(j*5);
			   int greenVal = validColor(255-i*5);
			   int blueVal = validColor((i*5)-(j*2));
			   col = Color.BLACK;
			   //col = new Color(redVal, greenVal, blueVal);
			   // Draw box, one pixel less to create a black outline
			   
			   int startx = j*lineSize;
			   int starty = i*lineSize;
			   if (i % 2 != 0) {
				   startx = startx + lineSize / 2;
			   }
			 
			   int val = ffa.frame[j][i];

			   //System.out.println("in forloop" + val);
			   if (FDCell.hasDirectionFlag(val, 0)) {
				   //System.out.println("in 0" + val);
				    paintArrow( g2d, startx, starty, 0, col); 
			   }
			   if (FDCell.hasDirectionFlag(val, 1)) 
				    paintArrow( g2d, startx, starty, 1, col); 
			   if (FDCell.hasDirectionFlag(val, 2)) 
				    paintArrow( g2d, startx, starty, 2, col); 
			   if (FDCell.hasDirectionFlag(val, 3)) 
				    paintArrow( g2d, startx, starty, 3, col); 
			   if (FDCell.hasDirectionFlag(val, 4)) 
				    paintArrow( g2d, startx, starty, 4, col); 
			   if (FDCell.hasDirectionFlag(val, 5)) 
				    paintArrow( g2d, startx, starty, 5, col); 

		   }
		}
	}
	
	/*
	 * A local routine to ensure that the color value is in the 0 to 255 range.
	 */
	private int validColor(int colorVal) {
		if (colorVal > 255)
			colorVal = 255;
		if (colorVal < 0)
			colorVal = 0;
		return colorVal;
	}
	

	/**
	 * A convenience routine to set the color and draw a line
	 * @param g2d the 2D Graphics context
	 * @param startx the line start position on the x-Axis
	 * @param starty the line start position on the y-Axis
	 * @param endx the line end position on the x-Axis
	 * @param endy the line end position on the y-Axis
	 * @param color the line color
	 */
	private void paintLine(Graphics2D g2d, int startx, int starty, int endx, int endy, Color color) {
		g2d.setColor(color);
		g2d.drawLine(startx, starty, endx, endy);
	}
	
	private void paintArrow(Graphics2D g2d, int startx, int starty, int dir, Color color) {
		//System.out.println("in paintArrow" + dir);
		int endx = startx;
		int endy = starty;
		int len = lineSize / 3;
		switch (dir) {
		default:
		case 0:
			endx = startx - len;
			break;
		case 1:
			endx = startx - len / 2;
			endy = starty - (int) (0.866 * len);
			break;
		case 2:
			endx = startx + len / 2;
			endy = starty - (int) (0.866 * len);
			break;
		case 3:
			endx = startx + len;
			break;
		case 4:
			endx = startx + len / 2;
			endy = starty + (int) (0.866 * len);
			break;
		case 5:
			endx = startx - len / 2;
			endy = starty + (int) (0.866 * len);
			break;
			
		}
		arrow(g2d, startx, starty, endx, endy, color);	
		
	}
	
	private void delay(long duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//draw an arrow
	private void arrow(Graphics2D g2d, int startx, int starty, int endx, int endy, Color color) {
		g2d.setColor(color);
		g2d.drawLine(startx, starty, endx, endy);
		Double k = 0.5;//the width of the arrow
		
		Double l = Math.sqrt((endy - starty) * (endy - starty) + (endx - startx) * (endx - startx));
		Double angle = 1.57;
		if (endx != startx) {
			angle = Math.atan((endy * 1.0 - starty) / (endx * 1.0 - startx));
		}
		//System.out.println("jiaodu" + angle);
		//System.out.println("cos " + Math.cos(angle));
		//System.out.println("l " + l);
		//System.out.println("sx + sy + ex + ey " + startx + " " + starty + " " + endx + " " + endy + " " + (endy - starty) + " " + (endx - startx));
		Double arrowSize = l / 2;//the size of the arrow
		int topx = (int) (endx + arrowSize * Math.cos(angle));
		int topy = (int) (endy + arrowSize * Math.sin(angle));
		if (endx < startx) {
			topx = (int) (endx - arrowSize * Math.cos(angle));
			topy = (int) (endy - arrowSize * Math.sin(angle));
		} 
	
		int leftx = (int) (endx - k * arrowSize * Math.sin(angle));
		int lefty = (int) (endy + k * arrowSize * Math.cos(angle));
		int rightx = (int) (endx + k * arrowSize * Math.sin(angle));
		int righty = (int) (endy - k * arrowSize * Math.cos(angle));
		
        GeneralPath triangle = new GeneralPath();  
        triangle.moveTo(topx, topy);  
        triangle.lineTo(leftx, lefty);  
        triangle.lineTo(rightx, righty);  
        triangle.closePath();
        g2d.fill(triangle);
		
	}
	


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof FluidFrame) {
			this.ffa = (FluidFrame) arg;
			this.repaint();
		}
	}
}
