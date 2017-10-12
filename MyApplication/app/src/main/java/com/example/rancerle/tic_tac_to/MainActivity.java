package com.example.rancerle.tic_tac_to;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private TicTacToe tttGame;
    private ButtonGridAndTextView tttView;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        tttGame = new TicTacToe( );
        Point size = new Point( );
        getWindowManager( ).getDefaultDisplay( ).getSize( size );
        int w = size.x / TicTacToe.SIDE;
        ButtonHandler bh = new ButtonHandler();
        tttView = new ButtonGridAndTextView( this, w, TicTacToe.SIDE, bh );
        tttView.setStatusText( tttGame.result() );
        setContentView( tttView );
    }



    public void showNewGameDialog( )
    {
        AlertDialog.Builder alert = new AlertDialog.Builder( this );
        alert.setTitle( "This is fun!" );
        alert.setMessage( "Play again?" );
        PlayDialog playAgain = new PlayDialog( );
        alert.setPositiveButton( "YES", playAgain );
        alert.setNegativeButton( "NO", playAgain );
        alert.show();
    }

    private class ButtonHandler implements View.OnClickListener
    {
        public void onClick( View v )
        {
            for ( int row = 0; row < TicTacToe.SIDE; row++ )
            {
                for ( int col = 0; col < TicTacToe.SIDE; col++ )
                {
                    if ( tttView.isButton( ( Button ) v, row, col ) )
                    {
                        int play = tttGame.play( row, col );
                        if ( play == 1 )
                        {
                            tttView.setButtonText( row, col, "X" );
                        }
                        else if ( play == 2 )
                        {
                            tttView.setButtonText( row, col, "O" );
                        }
                        if( tttGame.isGameOver() )
                        {
                            tttView.setBackgroundColor( Color.RED );
                            tttView.enableButtons( false );
                            tttView.setStatusText( tttGame.result() );
                            showNewGameDialog();
                        }
                    }
                }
            }
        }
    }

    private class PlayDialog implements DialogInterface.OnClickListener
    {
        public void onClick( DialogInterface dialog, int id )
        {
            if( id == -1 )
            {
                tttGame.resetGame();
                tttView.enableButtons( true );
                tttView.resetButtons( );
                tttView.setStatusBackgroundColor( Color.GREEN );
                tttView.setStatusText( tttGame.result() );
            }

            else if( id == -2 )
            {
                MainActivity.this.finish( );
            }
        }
    }
}
