/**
 * 
 */
package ui;

/**
 * @author xingli
 *
 */
public class FDRule implements RuleIntfs{

	public FluidFrame createNextFrame(FluidFrame inFrame) {
		FluidFrame nxtFrame = new FluidFrame(inFrame.getSize());
		int sz = inFrame.getSize();
		for (int x = 0; x < sz; x++) {
			for (int y = 0; y < sz; y++) {
				int inboundVal = inFrame.getCellInValue(x, y); // Read all neighbors and create opposite inbound values from their outbound ones
				int nextOutCelVal = createNextCell(inboundVal);
				int isE = isEdge(x, y, sz);
				//System.out.println(isE + " self: " + inFrame.getCellOutValue(x, y) + " around: " + inboundVal + " ");
				if (isE > 1) {
					nextOutCelVal = rebound(nextOutCelVal, isE, inFrame.getCellOutValue(x, y), x);
					
				}
				//System.out.println("other" + nextOutCelVal);
				nxtFrame.setCellOutValue(x, y, nextOutCelVal);
			}
		}
		return nxtFrame;
	}
	

	public int createNextCell(int inVal) {
		
		int outVal = 0;
		int arrow = toArrow(inVal);
		int total1 = 0;
		int one = 1;
		int zero = 1;
		int[] rst = new int[6]; 
		for (int i = 0; i <= 5; i++) {
			int curt = inVal;
			if ((curt >> i) % 2 > 0) {
				rst[i] = 1;
				total1++;
				if (i > 0 && rst[i - 1] == rst[i]) one ++;
			} else {
				rst[i] = 0;
				if (i > 0 && rst[i - 1] == rst[i]) zero ++;
			}
		}
		if (rst[0] == rst[5]) {
			switch (rst[0]) {
			default:
			case 0: 
				zero++;
				break;
			case 1:
				one++;
				break;
			}	
		}
		
		if (total1 == 2 && zero == 2) {
			outVal = rotate(inVal);
		} else if (total1 == 3) {
			//System.out.println("total: " + total1 + "one: " + one + "zero: " + zero);
			if (one == 2) {
				outVal = ~inVal;
				if (outVal < 0) outVal = -outVal;
			} else if (zero == 1) {
				outVal = ~inVal;
			}
		} else if (total1 == 4 && one == 2) {
			outVal = rotate(inVal);
		} else {
			outVal = arrow;
		}
		return outVal;
	}
	
	private int toArrow(int input) {
		int outV = 0;
		for (int dir = 0; dir < 6; dir++) {
			if (FDCell.hasDirectionFlag(input, dir))
				outV = FDCell.setFlag(outV, FDCell.getOppositeDirection(dir)); 
		}
		return outV;
	}
	
	//rebound rule
	private int rebound(int input,int isE, int value, int x) {
		
		int[] dir = new int[6];
		int vurt = value;
		for (int i = 0; i < 6; i++) {
			if ((vurt >> i) % 2 > 0) {
				dir[i] = 1;
			} else {
				dir[i] = 0;
			}
		}
		//System.out.println("all " + value);

		if (isE % 2 == 0) {
			if (dir[1] > 0) input = FDCell.setFlag(input, 4);
			if (x % 2 == 0) { 
				if (dir[0] > 0) input = FDCell.setFlag(input, 3);
				if (dir[5] > 0) input = FDCell.setFlag(input, 2);
			}
		}
		if (isE % 3 == 0) {
			
			if (dir[1] > 0) input = FDCell.setFlag(input, 4);
			if (dir[2] > 0) input = FDCell.setFlag(input, 5);
			
		}
		if (isE % 5 == 0) {
			if (dir[3] > 0) input = FDCell.setFlag(input, 0);
			if (x % 2 > 0) {
				if (dir[2] > 0) input = FDCell.setFlag(input, 5);
				if (dir[4] > 0) input = FDCell.setFlag(input, 1);
			}
			
		}
		if (isE % 7 == 0) {
			
			if (dir[4] > 0) input = FDCell.setFlag(input, 1);
			if (dir[5] > 0) input = FDCell.setFlag(input, 2);
			
		}
		
		return input;
	}
	
	private int isEdge(int x, int y, int size) {
		int rst = 1;
		
		if (x == 0) rst *= 3;
		if (x == size - 1) rst *= 7;
		if (y == 0) rst *= 2;
		if (y == size - 1) rst *= 5;
		return rst;
	}

	private int rotate(int input) {
		boolean end = false;
		int five = 0b100000;
		if (input % 2 > 0) end = true;
		input = input >> 1;
		if (end) input = input | five;
		return input;
	}
	


}
