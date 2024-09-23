/**
 * No collaborators
 * @author Theo Ghanem
  */

import java.util.*;

public class A2_Q1 {
    final static int HEIGHT = 5;
    final static int WIDTH = 9;
    static  int minNbrOfMoves = 0;
    static int nbrOfBallsLeft = 0;
    static String[] DIRECTIONS = {"RIGHT", "LEFT", "UP", "DOWN"};

    /**
     * Get the minimum number of balls left on the board with the minimum number of moves
     * @param board
     * @return
     */
    public static int[] game(String[][] board) {
        findBestMoves(board);  //find the best moves out of all the possible moves
        Integer minNumberOfBalls = Collections.min(mapMinBallsAndMoves.keySet());   //get the minimum number of balls possible
        minNbrOfMoves = mapMinBallsAndMoves.get(minNumberOfBalls);          //get the minimum number of moves for the minimum number of balls
        int[] result = {minNumberOfBalls,minNbrOfMoves};    //put that in the int[] result
        mapMinBallsAndMoves.clear();  //clear the hashmap
        minNbrOfMoves=0;  //reset the number of moves
        return result;
    }



    //create hashmap that will keep track of the number of balls, and the number of moves it took to reach those nbr of balls
    static Map<Integer, Integer> mapMinBallsAndMoves = new HashMap<Integer, Integer>();

    /**
     * Find the best moves to get the least number of balls
     * @param board
     * @return
     */
    public static boolean findBestMoves(String[][] board) {

        if (checkBaseCase(board)) { //check is we have reached the base case (no legal moved are left)
            mapMinBallsAndMoves.put(getNbrOfBallsLeft(board), minNbrOfMoves);
            return false;
        }
        else {  //there are still legal moves available
            for (int row = 0; row < HEIGHT; row++)     //iterate through every row
                for (int column = 0; column < WIDTH; column++)  //iterate through every column
                    for (int direction = 0; direction < DIRECTIONS.length; direction++) {//iterate through every direction
                        if (legalMove(row, column, DIRECTIONS[direction], board)) {      //if there is a legal move at the position where we are
                            makeMove(row, column, DIRECTIONS[direction], board);         //then do that move
                            if (findBestMoves(board)){
                                return true;
                            }
                            mapMinBallsAndMoves.put(getNbrOfBallsLeft(board), minNbrOfMoves);
                            undoMove(row, column, DIRECTIONS[direction], board);
                            }
                        }
            return false;
        }
    }



    /**
     * Gets the number of balls left at the end
     * @param board
     * @return
     */
    public static int getNbrOfBallsLeft(String[][] board) {
        nbrOfBallsLeft=0;
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                if (board[row][column].equals("o")) {
                    nbrOfBallsLeft++;
                }
            }
        }
        return nbrOfBallsLeft;
    }

    /**
     * Check to see if no other moves are possible
     * @param board
     * @return
     */
    public static boolean checkBaseCase(String[][] board) {
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                for (int direction = 0; direction < DIRECTIONS.length; direction++) {

                    if (legalMove(row, column, DIRECTIONS[direction], board)) {  //if there are still legal moves then we are not done
                        return false;
                    }
                }
            }
        }
        return true;  //we have checked all the board and there are no legal moves available
    }

    /**
     * Make the desired move
     * @param row
     * @param col
     * @param DIRECTION
     * @param board
     * @return
     */
    private static String[][] makeMove(int row, int col, String DIRECTION, String[][] board) {

        switch (DIRECTION) {

            case "RIGHT":
                if (board[row][col].equals("o")) {
                    if (col + 1 < 9 && board[row][col + 1].equals("o")) {
//                    System.out.println("there is a ball on the right");
                        if (col + 2 < 9 && board[row][col + 2].equals(".")) {  //if there is a hole after the other ball
                            board[row][col + 2] = "o"; //put the ball in its new spot
                            board[row][col + 1] = "."; //set a hole where there was the ball that got jumped over
                            board[row][col] = ".";   //set a hole where the ball jumped from
                            minNbrOfMoves++;
                        }
                    }
                }
                break;

            case "LEFT":
                if (board[row][col].equals("o")) {
                    if (col - 1 >= 0 && board[row][col - 1].equals("o")) {
//                    System.out.println("there is a ball on the left");
                        if (col - 2 >= 0 && board[row][col - 2].equals(".")) {  //if there is a hole after the other ball
                            board[row][col - 2] = "o"; //put the ball in its new spot
                            board[row][col - 1] = "."; //set a hole where there was the ball that got jumped over
                            board[row][col] = ".";   //set a hole where the ball jumped from
//                        System.out.println(Arrays.deepToString(board));
                            minNbrOfMoves++;
                        }
                    }
                }
                break;

            case "UP":
                if (board[row][col].equals("o")) {
                    if (row - 1 >= 0 && board[row - 1][col].equals("o")) {
//                    System.out.println("there is a ball on top");
                        if (row - 2 >= 0 && board[row - 2][col].equals(".")) {  //if there is a hole after the other ball
                            board[row - 2][col] = "o"; //put the ball in its new spot
                            board[row - 1][col] = "."; //set a hole where there was the ball that got jumped over
                            board[row][col] = ".";   //set a hole where the ball jumped from
//                        System.out.println(Arrays.deepToString(board));
                            minNbrOfMoves++;
                        }
                    }
                }
                break;

            case "DOWN":
                if (board[row][col].equals("o")) {
                    if (row + 1 <= 4 && board[row + 1][col].equals("o")) {
//                    System.out.println("there is a ball on the bottom");
                        if (row + 2 <= 4 && board[row + 2][col].equals(".")) {  //if there is a hole after the other ball
                            board[row + 2][col] = "o"; //put the ball in its new spot
                            board[row + 1][col] = "."; //set a hole where there was the ball that got jumped over
                            board[row][col] = ".";   //set a hole where the ball jumped from
//                        System.out.println(Arrays.deepToString(board));
                            minNbrOfMoves++;
                        }
                    }
                }
                break;

            default:
                break;
        }

        return board;
    }

    /**
     * Checks if the move we want to make is legal
     * @param row
     * @param col
     * @param DIRECTION
     * @param board
     * @return
     */
    private static boolean legalMove(int row, int col, String DIRECTION, String[][] board) {
        boolean moveIsLegal = false;
        switch (DIRECTION) {

            case "RIGHT":
                if (board[row][col].equals("o")) {
                    if (col + 1 < 9 && board[row][col + 1].equals("o")) {
//                    System.out.println("there is a ball on the right");
                        if (col + 2 < 9 && board[row][col + 2].equals(".")) {  //if there is a hole after the other ball
                            moveIsLegal = true;
                        }
                    }
                }
                break;

            case "LEFT":
                if (board[row][col].equals("o")) {
                    if (col - 1 >= 0 && board[row][col - 1].equals("o")) {
//                    System.out.println("there is a ball on the left");
                        if (col - 2 >= 0 && board[row][col - 2].equals(".")) {  //if there is a hole after the other ball
                            moveIsLegal = true;
                        }
                    }
                }
                break;

            case "UP":
                if (board[row][col].equals("o")) {
                    if (row - 1 >= 0 && board[row - 1][col].equals("o")) {
//                    System.out.println("there is a ball on top");
                        if (row - 2 >= 0 && board[row - 2][col].equals(".")) {  //if there is a hole after the other ball
                            moveIsLegal = true;
                        }
                    }
                }
                break;

            case "DOWN":
                if (board[row][col].equals("o")) {
                    if (row + 1 <= 4 && board[row + 1][col].equals("o")) {
//                    System.out.println("there is a ball on the bottom");
                        if (row + 2 <= 4 && board[row + 2][col].equals(".")) {  //if there is a hole after the other ball
                            moveIsLegal = true;
                        }
                    }
                }
                break;

            default:
                break;
        }

        return moveIsLegal;
    }


    /**
     * Undo the move we previously did
     * @param row
     * @param col
     * @param DIRECTION
     * @param board
     * @return
     */
    private static String[][] undoMove(int row, int col, String DIRECTION, String[][] board) {

        switch (DIRECTION) {

            case "RIGHT":
                if(col + 2 < 9 && board[row][col+2].equals("o")) {
                    if (board[row][col + 1].equals(".")) {
                        if (board[row][col].equals(".")) {  //if there is a hole after the other ball
                            board[row][col + 2] = "."; //put a hole where we had put a ball
                            board[row][col + 1] = "o"; //put back the ball we had jumped over
                            board[row][col] = "o";   //put back the ball that jumped over the other ball
                            minNbrOfMoves--;
                        }
                    }
                }
                break;

            case "LEFT":
                if(col-2 >=0 && board[row][col-2].equals("o")) {
                    if (board[row][col - 1].equals(".")) {
                        if (board[row][col].equals(".")) {  //if there is a hole after the other ball
                            board[row][col - 2] = "."; //put a hole where we had put a ball
                            board[row][col - 1] = "o"; //put back the ball we had jumped over
                            board[row][col] = "o";   //put back the ball that jumped over the other ball
                            minNbrOfMoves--;
                        }
                    }
                }
                break;

            case "UP":
                if(row - 2>=0  && board[row-2][col].equals("o")) {
                    if (board[row-1][col].equals(".")) {
                        if (board[row][col].equals(".")) {  //if there is a hole after the other ball
                            board[row-2][col] = "."; //put a hole where we had put a ball
                            board[row-1][col] = "o"; //put back the ball we had jumped over
                            board[row][col] = "o";   //put back the ball that jumped over the other ball
                            minNbrOfMoves--;
                        }
                    }
                }
                break;

            case "DOWN":
                if(row + 2<= 4  && board[row+2][col].equals("o")) {
                    if (board[row+1][col].equals(".")) {
                        if (board[row][col].equals(".")) {  //if there is a hole after the other ball
                            board[row+2][col] = "."; //put a hole where we had put a ball
                            board[row+1][col] = "o"; //put back the ball we had jumped over
                            board[row][col] = "o";   //put back the ball that jumped over the other ball
                            minNbrOfMoves--;
                        }
                    }
                }
                break;

            default:
                break;
        }
        return board;
    }
}