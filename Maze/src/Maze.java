// Name: Uche Uba
// USC loginid: uuba@aludra.usc.edu
// CS 455 PA3
// Fall 2017

import java.util.LinkedList;
import java.util.*;


/**
 Maze class

 Stores information about a maze and can find a path through the maze
 (if there is one).

 Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
 (parameters to constructor), and the path:
 -- no outer walls given in mazeData -- search assumes there is a virtual
 border around the maze (i.e., the maze path can't go outside of the maze
 boundaries)
 -- start location for a path is maze coordinate startLoc
 -- exit location is maze coordinate exitLoc
 -- mazeData input is a 2D array of booleans, where true means there is a wall
 at that location, and false means there isn't (see public FREE / WALL
 constants below)
 -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
 -- only travel in 4 compass directions (no diagonal paths)
 -- can't travel through walls

 */

public class Maze {

    public static final boolean FREE = false;
    public static final boolean WALL = true;
    private boolean[][] mazeData;
    private MazeCoord startLoc;
    private MazeCoord exitLoc;
    private boolean [][] visited;
    private LinkedList<MazeCoord> soln_path;
    private boolean PathExists= false;


    /**
     Constructs a maze.
     * @param mazeData the maze to search.  See general Maze comments above for what
     goes in this array.
     * @param startLoc the location in maze to start the search (not necessarily on an edge)
     * @param exitLoc the "exit" location of the maze (not necessarily on an edge)
PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length
     */
    public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord exitLoc) {
    this.mazeData= mazeData;
    this.startLoc= startLoc;
    this.exitLoc= exitLoc;
    soln_path= new LinkedList<>();

    }


    /**
     Returns the number of rows in the maze
     @return number of rows
     */
    public int numRows() {
        return mazeData.length;
    }


    /**
     Returns the number of columns in the maze
     @return number of columns
     */
    public int numCols() {
        return mazeData[0].length;
    }


    /**
     Returns true iff there is a wall at this location
     @param loc the location in maze coordinates
     @return whether there is a wall here
     PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
     */
    public boolean hasWallAt(MazeCoord loc) {
        int x= loc.getRow();
        int y= loc.getCol();

        if(mazeData[x][y]== FREE) {
            return false;
        }
        return true;
    }


    /**
     Returns the entry location of this maze.
     */
    public MazeCoord getEntryLoc() {

        //return new MazeCoord(startLoc.getRow(),startLoc.getCol());
        return startLoc;
    }


    /**
     Returns the exit location of this maze.
     */
    public MazeCoord getExitLoc() {

        return new MazeCoord(exitLoc.getRow(),exitLoc.getCol() );
    }


    /**
     Returns the path through the maze. First element is start location, and
     last element is exit location.  If there was not path, or if this is called
     before a call to search, returns empty list.

     @return the maze path
     */
    public LinkedList<MazeCoord> getPath() {

        return soln_path;

    }


    /**
     Find a path from start location to the exit location (see Maze
     constructor parameters, startLoc and exitLoc) if there is one.
     Client can access the path found via getPath method.

     @return whether a path was found.
     */
    public boolean search()  {

        visited= new boolean[numRows()][numCols()];
        if(mazeData[startLoc.getRow()][startLoc.getCol()]== WALL || mazeData[exitLoc.getRow()][exitLoc.getCol()]== WALL ) {
            System.out.print("There is no path");
            return false;
        }

        return solve(startLoc.getRow(),startLoc.getCol(),visited);
    }

    //Flag to indicate if there is a path

    public boolean foundPath(){
        return PathExists;
    }
    /*
    / Recursively solves the maze
    @param x is the row value of each position
    @param y is the column value
    @param visited keeps track of the positions of maze data visited
    */

    private boolean solve(int x, int y, boolean[][] visited){

        if(x== exitLoc.getRow() && y== exitLoc.getCol()){
            soln_path.addFirst(new MazeCoord(x,y));
            return true;}
        if(x<0 || y<0 || x>=numRows() || y>=numCols()) {return false;}
//        System.out.println(x+ " " +y);
        if(visited[x][y]|| mazeData[x][y]){return false;}

        visited[x][y]= true;
        if(solve(x+1,y,visited)||
                solve(x,y+1,visited)||
                solve(x-1,y,visited)||
                solve(x,y-1,visited)){
            soln_path.addFirst(new MazeCoord(x,y));
            PathExists= true;
            return true;
        }

        return false;

    }


}
