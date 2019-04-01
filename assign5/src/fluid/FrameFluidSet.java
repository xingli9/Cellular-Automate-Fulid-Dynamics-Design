/**
 * 
 */
package fluid;

import java.util.Observable;

/**
 * @author xingli
 *
 */
public class FrameFluidSet extends Observable implements Runnable {

	private boolean done = false; // Set true to exit (i.e. stop) the simulation
	private boolean paused = false;
	private boolean running = false;
	//private boolean paused = false; // Set true to pause the simulation
	
	private int MAX_FRAME_SIZE = 3; // How big is the simulation frame
	private int MAX_GENERATION = 5; // How many generations will we calculate before we're through?
	private int genCount = 1; // the count of the most recent generation

	public FluidFrame currentFrame = null;
	
	private RuleIntfs rule = null;
	
	/**
	 * 
	 */
	public FrameFluidSet() {
		currentFrame = new FluidFrame(MAX_FRAME_SIZE);
		currentFrame.addRandomParticles(0.8); // the percentage of cells that have a particle
		rule = new FDRule();
	}

	public void runSim() {
		
		System.out.println("FluidFrame: 1" );
		currentFrame.drawFrameToConsole();
		
		while(!done) {
			
			// Move target if needed
			
			FluidFrame nextFrame = rule.createNextFrame(currentFrame);
			
			// Average the results to create a lower-res display frame
			
			// Store the low-res picture and make it available for display
			
			// Advertise that we have a display displayable average FluidFrame and let it be drawn

			genCount++; // Keep track of how many frames have been calculated
			System.out.println("\nFluidFrame: " + genCount);
			nextFrame.drawFrameToConsole();
			
			currentFrame = nextFrame;

			if (genCount >= MAX_GENERATION) done = true;
		}
		
	}
	
	public void showSim() {
		System.out.println("Show FluidFrame: 1" );
		FluidFrame nextFrame = rule.createNextFrame(currentFrame);
		genCount++;
		
		currentFrame = nextFrame;
		nextFrame.drawFrameToPanel();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FrameFluidSet ffSim = new FrameFluidSet();
		ffSim.runSim(); // Perform a test run of the simulation
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		running = true;
		currentFrame.drawFrameToPanel();
		
		while(!done) {
			if (!paused) {
				showSim();
				delay(300L);
			} else {
				delay(500L);
			}
			if (genCount > MAX_GENERATION) done = true;
		}
		running = false;
	}
	
	private void delay(long duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
