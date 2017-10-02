package com.example.rancerle.tic_tac_to;

/**
 * Created by Rancerle on 10/2/2017.
 */

public class TicTacToe
{
    public boolean canNotPlay()
    {
        boolean result = true;
        for(int row = 0; row < SIDE; row++)
        {
            for (int col = 0; col < SIDE; col++)
            {
                if (game[row][col] == 0)
                {
                    result = false;
                }
            }
        }
        return result;
    }

    public boolean isGameOver() {return canNotPlay() || ( whoWon() > 0);}

    public void resetGame()
    {
        for (int row = 0; row < SIDE; row++)
        {
            for(int col = 0; col < SIDE; col++)
            {
                game[row][col] = 0;
            }
        }
        turn = 1;
    }
}
