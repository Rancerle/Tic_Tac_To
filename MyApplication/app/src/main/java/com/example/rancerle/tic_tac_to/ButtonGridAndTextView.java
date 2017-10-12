package com.example.rancerle.tic_tac_to;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class ButtonGridAndTextView extends GridLayout
{
    private int side;
    private Button [][] buttons;
    private TextView status;

    public ButtonGridAndTextView( Context context, int width, int newSide, OnClickListener listener )
    {
        super( context );
        side = newSide;
        setColumnCount( side );
        setRowCount( side + 1 );


        buttons = new Button[side][side];
        for( int row = 0; row < side; row++ )
        {
            for( int col = 0; col < side; col++ )
            {
                buttons[row][col] = new Button( context );
                buttons[row][col].setTextSize( ( int ) ( width * .2 ) );
                buttons[row][col].setOnClickListener( listener );
                addView( buttons[row][col], width, width );
            }
        }

        status = new TextView( context );
        Spec rowSpec = GridLayout.spec( side, 1 );
        Spec colSpec = GridLayout.spec( 0, side );
        LayoutParams lpStatus = new LayoutParams( rowSpec, colSpec );
        status.setLayoutParams( lpStatus );

        status.setWidth( side * width );
        status.setHeight( width );
        status.setGravity( Gravity.CENTER );
        status.setBackgroundColor( Color.GREEN );
        status.setTextSize( ( int ) ( width * .15 ) );

        addView( status );
    }

    public void setStatusText( String text ) { status.setText( text ); }

    public void setStatusBackgroundColor( int color ) { status.setBackgroundColor( color ); }

    public void setButtonText( int row, int col, String text ) { buttons[row][col].setText( text ); }

    public boolean isButton( Button b, int row, int col ) { return ( b == buttons[row][col] ); }

    public void resetButtons( )
    {
        for( int row = 0; row < side; row++ )
        {
            for (int col = 0; col < side; col++)
            {
                buttons[row][col].setText("");
            }
        }
    }

    public void enableButtons( boolean enabled )
    {
        for( int row = 0; row < side; row++ )
        {
            for (int col = 0; col < side; col++)
            {
                buttons[row][col].setEnabled(enabled);
            }
        }
    }
}
