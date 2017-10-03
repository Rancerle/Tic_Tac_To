package com.example.rancerle.tic_tac_to;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.util.Log;


public class MainActivity extends AppCompatActivity
{
    private TicTacToe tttGame;
    private Button [][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        tttGame = new TicTacToe( );
        buildGuibyCode( );
    }

    public void buildGuibyCode( )
    {
        Point size = new Point( );
        getWindowManager( ) . getDefaultDisplay( ). getSize( size );
        int w = size.x / TicTacToe.SIDE;

        GridLayout gridLayout = new GridLayout( this );
        gridLayout.setColumnCount( TicTacToe.SIDE );
        gridLayout.setRowCount( TicTacToe.SIDE );

        buttons = new Button[TicTacToe.SIDE][TicTacToe.SIDE];
        ButtonHandler bh = new ButtonHandler( );
        for ( int row = 0; row < TicTacToe.SIDE; row++ )
        {
            for( int col = 0; col < TicTacToe.SIDE; col++ )
            {
                buttons[row][col] = new Button( this );
                buttons[row][col].setTextSize( ( int ) ( w * .2 ) );
                buttons[row][col].setOnClickListener( bh );
                gridLayout.addView( buttons[row][col], w, w);
            }
        }

        setContentView( gridLayout );
    }

    public void update( int row, int col )
    {
        int play = tttGame.play( row, col );

        if ( play == 1 )
        {
            buttons[row][col].setText( "X" );
        }

        else if ( play == 2 )
        {
            buttons[row][col].setText( "O" );
        }

        if( tttGame.isGameOver() )
        {
            enableButtons( false );
        }
    }

    public void enableButtons ( boolean enabled )
    {
        for( int row = 0; row < TicTacToe.SIDE; row++ )
        {
            for( int col = 0; col < TicTacToe.SIDE; col++ )
            {
                buttons[row][col].setEnabled( enabled );
            }
        }
    }

    private class ButtonHandler implements View.OnClickListener
    {
        public void onClick( View v )
        {
            Log.w( "MainActivity", "Inside onClick, v = " + v);
            for ( int row = 0; row < TicTacToe.SIDE; row++ )
            {
                for ( int col = 0; col < TicTacToe.SIDE; col++ )
                {
                    if ( v == buttons[row][col] )
                    {
                        update( row, col );
                    }
                }
            }
        }
    }
}
