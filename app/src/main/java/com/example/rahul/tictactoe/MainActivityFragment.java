package com.example.rahul.tictactoe;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    private final int onePlayerActivity = 1;
    private final int twoPlayerActivity = 2;

    private int lastActivity = 0;
    private FloatingActionButton resumeFab;

    public MainActivityFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        resumeFab = view.findViewById(R.id.resumeFab);
        resumeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(lastActivity == onePlayerActivity){
                     intent = new Intent(getActivity(), SinglePlayerActivity.class);
                }
                else{
                    intent = new Intent(getActivity(), TwoPlayerActivity.class);
                }
                startActivity(intent);
            }
        });

        view.findViewById(R.id.mainlist_oneplayer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SinglePlayerActivity.class);
                BoardManager.resetBoard();
                ScoreManger.resetScore();
                lastActivity = onePlayerActivity;
                startActivity(intent);
            }
        });


        view.findViewById(R.id.mainlist_twoplayer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TwoPlayerActivity.class);
                BoardManager.resetBoard();
                ScoreManger.resetScore();
                lastActivity = twoPlayerActivity;
                startActivity(intent);
            }
        });



        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        if(lastActivity == 0){
            resumeFab.setVisibility(View.GONE);
        }
        else{
            resumeFab.setVisibility(View.VISIBLE);
        }
    }
}
