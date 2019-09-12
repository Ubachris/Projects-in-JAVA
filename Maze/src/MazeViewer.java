// Name: Uche Uba
// USC loginid:uuba@aludra.usc.edu
// CS 455 PA3
// Fall 2017


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import java.io.File;


/**
 * MazeViewer class
 *
 * Program to read in and display a maze and a path through the maze. At user
 * command displays a path through the maze if there is one.
 *
 * How to call it from the command line:
 *
 *      java MazeViewer mazeFile
 *
 * where mazeFile is a text file of the maze. The format is the number of rows
 * and number of columns, followed by one line per row, followed by the start location,
 * and ending with the exit location. Each maze location is
 * either a wall (1) or free (0). Here is an example of contents of a file for
 * a 3x4 maze, with start location as the top left, and exit location as the bottom right
 * (we count locations from 0, similar to Java arrays):
 *
 * 3 4
 * 0111
 * 0000
 * 1110
 * 0 0
 * 2 3
 *
 */

public class MazeViewer {

    private static final char WALL_CHAR = '1';
    private static final char FREE_CHAR = '0';

    public static void main(String[] args)  {

        String fileName = "";

        try {

            if (args.length < 1) {
                System.out.println("ERROR: missing file name command line argument");
            }
            else {
                fileName = args[0];

                JFrame frame = readMazeFile(fileName);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setVisible(true);
            }

        }
        catch (FileNotFoundException exc) {
            System.out.println("ERROR: File not found: " + fileName);
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    /**
     readMazeFile reads in maze from the file whose name is given and
     returns a MazeFrame created from it.

     @param fileName
     the name of a file to read from (file format shown in class comments, above)
     @returns a MazeFrame containing the data from the file.

     @throws FileNotFoundException
     if there's no such file (subclass of IOException)
     @throws IOException
     (hook given in case you want to do more error-checking --
     that would also involve changing main to catch other exceptions)
     */
    private static MazeFrame readMazeFile(String fileName) throws IOException {
        File InputFile= new File (fileName);
        Scanner in= new Scanner (InputFile);
        String location= in.nextLine();
        String[] str= location.split(" ");
        int row= Integer.parseInt(str[0]);
        int col= Integer.parseInt(str[1]);
        boolean[][] position= new boolean[row][col];

        for (int i= 0; i<position.length; i++) {
            location = in.nextLine();
            for (int j = 0; j < position[i].length; j++){
                char value= location.charAt(j);
                if (value == WALL_CHAR) {
                    position[i][j] = true;
                }
                else {
                    position[i][j] = false;
                }

            }
        }

        int[] start_loc= new int[2];
        start_loc[0] =in.nextInt();
        start_loc[1]= in.nextInt();
        int[] end_loc= new int[2];
        end_loc[0] =in.nextInt();
        end_loc[1]= in.nextInt();

        return new MazeFrame(position,
                new MazeCoord( (start_loc[0]), (start_loc[1])),
                new MazeCoord((end_loc[0]), (end_loc[1])));
    }
}
