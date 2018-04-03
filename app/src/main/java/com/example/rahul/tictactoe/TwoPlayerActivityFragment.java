package com.example.rahul.tictactoe;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class TwoPlayerActivityFragment extends Fragment {


    private View mRootView;
    private FloatingActionButton defaultSelectorView;
    private int i, noOfTimes;
    private ImageButton button[] = new ImageButton[9];
    private TextView p1score, p2score;


    private View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Get click position
            int pos = getPosition((ImageButton) view);

            //If the position has not been clicked yet
            if (BoardManager.board[pos] == " ") {

                //put X or O in that position
                BoardManager.putValue(pos);

                //increment no of chance finished
                noOfTimes++;

                //Set respective image
                if (BoardManager.chance == "O") {
                    ((ImageButton) view).setImageResource(R.drawable.ic_x);
                } else {
                    ((ImageButton) view).setImageResource(R.drawable.ic_o);
                }
            } else {
                Toast.makeText(getContext(), "You should only tap on blank spaces", Toast.LENGTH_SHORT).show();
            }


            //If a chance has happened we check if we have our winner after this chance
            if (BoardManager.winner != null) {

                //Increase score of player who is winner
                ScoreManger.setWinner(BoardManager.winner);

                //Update score of both players
                p1score.setText("" + ScoreManger.scoreP1);
                p2score.setText("" + ScoreManger.scoreP2);

                //Now game should stop listening for taps
                releaseClickEvents();

                //Update UI to show user where is the winning line
                setWinningLine();

                //Notifying the user to display winner
                ((TextView) mRootView.findViewById(R.id.setWinner)).setText("The winner is " + BoardManager.winner);

            }

            //If winner is still null after 9 chances then game is draw
            else if (noOfTimes == 9) {
                releaseClickEvents();

                //Show user that it is a tie
                ((TextView) mRootView.findViewById(R.id.setWinner)).setText("Its a tie");
            } else{
                notifyWhoseTurn();
            }
            defaultSelectorView.setVisibility(View.INVISIBLE);

        }
    };


    private View.OnClickListener restartListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Get tap position
            int pos = getPosition((ImageButton) view);


            //Setting first launch to true
            BoardManager.isFirstLaunch = true;
            //Begin a new game--Reset the board to blank
            beginNewGame();


            //Now put X in the position selected
            BoardManager.putValue(pos);
            noOfTimes++;


            if (BoardManager.chance == "O") {
                //If chance got changed to O means X was clicked
                ((ImageButton) view).setImageResource(R.drawable.ic_x);
            } else {
                //If chan ce got changed to X means O was cliciked
                ((ImageButton) view).setImageResource(R.drawable.ic_o);
            }

            defaultSelectorView.setVisibility(View.INVISIBLE);
            //Set table with respective values
            setTable();

        }
    };


    public TwoPlayerActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two_player, container, false);

        //setting the global rootview so that we can access it everywhere
        mRootView = view;

        defaultSelectorView =view.findViewById(R.id.defaultSelector);

        //Initialisng every button to its respective view ids
        button[0] = view.findViewById(R.id.grid_button1);
        button[1] = view.findViewById(R.id.grid_button2);
        button[2] = view.findViewById(R.id.grid_button3);
        button[3] = view.findViewById(R.id.grid_button4);
        button[4] = view.findViewById(R.id.grid_button5);
        button[5] = view.findViewById(R.id.grid_button6);
        button[6] = view.findViewById(R.id.grid_button7);
        button[7] = view.findViewById(R.id.grid_button8);
        button[8] = view.findViewById(R.id.grid_button9);

        //Settinmg the scores
        p1score = view.findViewById(R.id.p1_score_textview);
        p2score = view.findViewById(R.id.p2_score_textview);

        //Giving command to start a new game
        beginNewGame();

        //Refresh Button
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Set board to firstLaunch
                BoardManager.isFirstLaunch = true;

                //Just begin a new game
                beginNewGame();

            }
        });



        defaultSelectorView.findViewById(R.id.defaultSelector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(BoardManager.isP1X){
                    BoardManager.setP1O();
                    notifyWhoseTurn();
                    defaultSelectorView.setImageResource(R.drawable.ic_x);
                    Toast.makeText(getContext(), "Player 1 : O       Player 2 : X", Toast.LENGTH_SHORT).show();
                }else {
                    BoardManager.setP1X();
                    notifyWhoseTurn();
                    defaultSelectorView.setImageResource(R.drawable.ic_o);
                    Toast.makeText(getContext(), "Player 1 : X       Player 2 : O", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }


    /**
     * Calling this function will prepare the screen for a new game
     */
    public void beginNewGame() {

        //Only if the the board is launched for the first time
        if (BoardManager.isFirstLaunch) {

            //reset the BoardManager
            BoardManager.resetBoard();

            //set the no. of grid square filled to zero
            noOfTimes = 0;

            //And finally setting isFirstLaunch to false so that next time it wont reset the board
            BoardManager.isFirstLaunch = false;

        }


        //If no has won the game till now and also less than 9 grid squares have been filled
        if (BoardManager.winner == null && noOfTimes < 9) {
            for (i = 0; i < 9; i++) {
                button[i].setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDoubleSecondary));
                button[i].setOnClickListener(itemClickListener);
            }
        }

        defaultSelectorView.setVisibility(View.VISIBLE);
        //Finally after updating every thing we need to set the Table according to
        //modified settings
        setTable();
    }


    /**
     * Calling this function will set the table based on the
     * the current settings and state of the board
     */
    private void setTable() {

        //Firstly handle the case of filling the board according to the value of
        //BoardManager.board
        for (i = 0; i < 9; i++) {
            if (BoardManager.board[i].equals("X")) {
                button[i].setImageResource(R.drawable.ic_x);
            } else if (BoardManager.board[i].equals("O")) {
                button[i].setImageResource(R.drawable.ic_o);
            } else {
                button[i].setImageResource(R.drawable.placeholder);
            }
        }

        //If winner is not null
        if (BoardManager.winner != null) {
            //releasing the click events
            releaseClickEvents();

            //setting the color of the winning line
            setWinningLine();

            //Setting the text to notify the user of the activity
            ((TextView) mRootView.findViewById(R.id.setWinner)).setText("The winner is " + BoardManager.winner);
        } else if(noOfTimes == 9){
            ((TextView) mRootView.findViewById(R.id.setWinner)).setText("It's a tie");
        }
        else {
            notifyWhoseTurn();
        }

        //Update the scores of the textviews to keep the scores up-to-date
        p1score.setText("" + ScoreManger.scoreP1);
        p2score.setText("" + ScoreManger.scoreP2);
    }


    /**
     * @param buttonClicked : the view which the user has pressed
     * @return the index of the button which corresponds to the tapped view
     */
    private int getPosition(ImageButton buttonClicked) {
        for (int i = 0; i < 9; i++) {
            if (buttonClicked == button[i]) {
                return i;
            }
        }
        return -1;
    }


    /**
     * In case of a draw or a win we set the onClickListener
     * to restartListener so that the user can restart a game on clicking
     * any icon
     */
    private void releaseClickEvents() {
        for (int i = 0; i < 9; i++) {
            button[i].setOnClickListener(restartListener);
        }
    }


    /**
     * In case someone wins the game then this function
     * changes the background color of the respective line
     * to show the user the way he has won
     */
    private void setWinningLine() {
        button[Utility.winnerLine1].setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDoubleSecondaryWin));
        button[Utility.winnerLine2].setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDoubleSecondaryWin));
        button[Utility.winnerLine3].setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDoubleSecondaryWin));
    }


    private void notifyWhoseTurn(){

        if(BoardManager.chance == "X" ){
            if(BoardManager.isP1X == true) {
                ((TextView) mRootView.findViewById(R.id.setWinner)).setText("Player 1's turn");
            }else {

                ((TextView) mRootView.findViewById(R.id.setWinner)).setText("Player 2's turn");
            }
        }

        else if(BoardManager.chance == "O" ){
            if(BoardManager.isP1X == true) {
                ((TextView) mRootView.findViewById(R.id.setWinner)).setText("Player 2's turn");
            }else {

                ((TextView) mRootView.findViewById(R.id.setWinner)).setText("Player 1's turn");
            }
        }

    }
}
