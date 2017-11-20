package com.example.android.miwok;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {


    public ColorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.activity_numbers,container,false);

        // Create a list of words
        ArrayList<Word> words = new ArrayList<Word>();

        for (int i=0;i<6;i++){
            words.add(new Word("color "+i,"lutti is" + i, R.drawable.syh ));
        }
        WordAdapter itemsAdapter =
                new WordAdapter(getActivity(),words );

        ListView listView = (ListView)rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        return rootView;
    }

}
