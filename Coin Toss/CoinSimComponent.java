// Name: Uche Uba
// USC NetID: 9239603212
// CS 455 PA1
// Spring 2018

import javax.swing.JComponent;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class CoinSimComponent extends JComponent {

    private int trials;
    private static final int BAR_WIDTH= 50;
    private static final int VERTICAL_BUFFER= 25;
    private static final int PERCENTAGE= 100;
    private static final Color TWO_HEADS_COLOR= Color.RED;
    private static final Color TWO_TAILS_COLOR= Color.BLUE;
    private static final Color HEAD_TAIL_COLOR= Color.GREEN;
    private String label1= "Two Heads";


    /** Constructor
     * @param num_trials the number of trials for a particular run
     */
    public CoinSimComponent(int num_trials){

        trials= num_trials;
    }

    /**
     * Method creates the component that draws each bar
     * @param g creates the graphics that draws the bar components
     */
    public void paintComponent (Graphics g){
        Graphics2D g2= (Graphics2D) g;

        Font font = g2.getFont();
        FontRenderContext context= g2.getFontRenderContext();
        Rectangle2D labelBounds= font.getStringBounds(label1, context);
        int heightoflabel = (int) labelBounds.getHeight();

        // Creates a scale relative to the frame height
        int SCALE_FACTOR= getHeight()-(2*VERTICAL_BUFFER+heightoflabel);
        double scale= SCALE_FACTOR/PERCENTAGE;

        // The width between each bar on the frame
        int W = (getWidth()-3*BAR_WIDTH)/4;

        CoinTossSimulator sim = new CoinTossSimulator();
        sim.run(trials);

        // Calculates the height of each bar in application units gotten from the simulation run
        int Two_Heads_Height= (sim.getTwoHeads()*PERCENTAGE/sim.getNumTrials());
        int Two_Tails_Height= (sim.getTwoTails()*PERCENTAGE/sim.getNumTrials());
        int Heads_Tails_Height= (sim.getHeadTails()*PERCENTAGE/sim.getNumTrials());

        Bar bar1= new Bar(getHeight()-(VERTICAL_BUFFER+ heightoflabel), W, BAR_WIDTH, Two_Heads_Height,
                scale, TWO_HEADS_COLOR, "Two Heads: "+ sim.getTwoHeads()+" ("+Two_Heads_Height+"%)");
        Bar bar2= new Bar(getHeight()-(VERTICAL_BUFFER+ heightoflabel),(W*2+BAR_WIDTH),BAR_WIDTH, Heads_Tails_Height,
                scale, HEAD_TAIL_COLOR, "A Head and a Tail: "+ sim.getHeadTails()+" ("+Heads_Tails_Height+"%)");
        Bar bar3 = new Bar(getHeight()-(VERTICAL_BUFFER+ heightoflabel), (W*3+(2*BAR_WIDTH)), BAR_WIDTH, Two_Tails_Height,
                scale, TWO_TAILS_COLOR, "Two Tails: "+ sim.getTwoTails()+" ("+Two_Tails_Height+"%)");

        // Draws the bars on the component
        bar1.draw(g2);
        bar2.draw(g2);
        bar3.draw(g2);
    }
}
