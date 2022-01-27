package org.test;

import java.util.Random;

public class LifeGame {
    private static final int WIDTH = 25;
    private static final int HEIGHT = 25;

    public static void main(String[] args) throws InterruptedException {

        int[][] grid = generateRandomGrid();

        printGrid(grid);

        while(true) {
            Thread.sleep(2000);
            grid = getGridNextState(grid);
            printGrid(grid);
        }
    }

    private static int[][] generateRandomGrid() {
        Random random = new Random();
        int[][] grid = new int[HEIGHT][WIDTH];
        for(int i = 0; i < HEIGHT; i++) {
            for(int j = 0; j < WIDTH; j++) {
                grid[i][j] = random.nextInt(2);
            }
        }
        return grid;
    }

    private static int[][] getGridNextState(int[][] currentGrid) {
        int[][] nextGrid = new int[HEIGHT][WIDTH];

        for(int l = 1; l < HEIGHT - 1; l++) {
            for(int m = 1; m < WIDTH - 1; m++) {

                int aliveNeighbours = 0;
                for(int i = -1; i <= 1; i++) {
                    for(int j = -1; j <= 1; j++) {
                        aliveNeighbours += currentGrid[l + i][m + j];
                    }
                }
                aliveNeighbours -= currentGrid[l][m]; //deduct current cell cause it's already counted
                nextGrid[l][m] = getCellValue(currentGrid[l][m], aliveNeighbours);
            }
        }
        return nextGrid;
    }

    private static int getCellValue(int currentCell, int aliveNeighbours) {
        if(currentCell == 1) {
            if(aliveNeighbours < 2 || aliveNeighbours > 3) {
                return 0; //underpopulated or overcrowded
            } else {
                return 1; //cell survives
            }
        } else {
            if(aliveNeighbours == 3) {
                return 1; //it's alive! it's a miracle!
            }
        }
        return currentCell;
    }

    private static void printGrid(int[][] grid) {
        System.out.println("Grid state:");
        for(int i = 0; i < HEIGHT; i++) {
            for(int j = 0; j < WIDTH; j++) {
                if(grid[i][j] == 0) {
                    System.out.print(".");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
