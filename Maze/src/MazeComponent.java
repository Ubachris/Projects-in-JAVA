// Name: Uche Uba
// USC loginid:uuba@aludra.usc.edu
// CS 455 PA3
// Fall 2017

import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 MazeComponent class

 A component that displays the maze and path through it if one has been found.
 */
public class MazeComponent extends JComponent
{

    private static final int START_X = 10; // top left of corner of maze in frame
    private static final int START_Y = 10;
    private static final int BOX_WIDTH = 20;  // width and height of one maze "location"
    private static final int BOX_HEIGHT = 20;
    private static final int INSET = 2;         // how much smaller on each side to make entry/exit inner box
    private Rectangle box;
    private Rectangle maze_dim;
    private Rectangle entryloc;
    private Rectangle exitloc;
    private Line2D.Double line;
    Maze maze= null;
//    MazeCoord loc= new MazeCoord(int i, int j);




    /**
     Constructs the component.
     @param maze   the maze to display
     */
    public MazeComponent(Maze maze)
    {
         this.maze= maze;

    }


    /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
     */
    public void paintComponent(Graphics g) {
        int maze_width = (maze.numCols() * BOX_WIDTH);
        int maze_height = (maze.numRows() * BOX_HEIGHT);

        int entrylocx = (maze.getEntryLoc().getCol()) * BOX_WIDTH;
        int entrylocy = (maze.getEntryLoc().getRow()) * BOX_HEIGHT;
        int exitlocx = (maze.getExitLoc().getCol()) * BOX_WIDTH;
        int exitlocy = (maze.getExitLoc().getRow()) * BOX_HEIGHT;
        Graphics2D g2 = (Graphics2D) g;

        maze_dim = new Rectangle(START_X, START_Y, maze_width, maze_height);
        g2.draw(maze_dim);


        for (int i = 0; i < maze.numRows(); i++) {
            for (int j = 0; j < maze.numCols(); j++) {
                if (maze.hasWallAt(new MazeCoord(i, j))) {
                    box = new Rectangle(START_X + (BOX_WIDTH * j), START_Y + (BOX_HEIGHT * i), BOX_WIDTH, BOX_HEIGHT);
                    g.setColor(Color.BLACK);
                    g2.draw(box);
                    g2.fill(box);
                } else {
                    box = new Rectangle(START_X + (BOX_WIDTH * j), START_Y + (BOX_HEIGHT * i), BOX_WIDTH, BOX_HEIGHT);
                    g.setColor(Color.WHITE);
                    g2.draw(box);
                    g2.fill(box);
                }
            }
        }


        entryloc= new Rectangle(START_X+entrylocx+INSET,START_Y+entrylocy+INSET,(BOX_WIDTH-2*INSET),(BOX_WIDTH-2*INSET));
        g.setColor(Color.YELLOW);
        g2.fill(entryloc);

        exitloc= new Rectangle(START_X+exitlocx+INSET,START_Y+exitlocy+INSET,(BOX_WIDTH-2*INSET),(BOX_WIDTH-2*INSET));
        g.setColor(Color.GREEN);
        g2.fill(exitloc);

        LinkedList<MazeCoord> list= maze.getPath();
        ListIterator<MazeCoord> iterator= list.listIterator();

//      //Prints out a path from the Linked List if there is one
        if(maze.foundPath()== true) {
            MazeCoord node1= iterator.next();
            while (iterator.hasNext()) {
                MazeCoord node2= iterator.next();
                Point2D.Double ri= new Point2D.Double((node1.getCol()*(BOX_WIDTH))+(BOX_WIDTH),(node1.getRow()*(BOX_HEIGHT))+(BOX_WIDTH));
                Point2D.Double r2 = new Point2D.Double((node2.getCol()*(BOX_WIDTH))+(BOX_WIDTH), (node2.getRow()*(BOX_HEIGHT))+(BOX_WIDTH));
                line = new Line2D.Double(ri, r2);
                g.setColor(Color.BLUE);
                g2.draw(line);
                node1= node2;
            }
        }

    }

}
