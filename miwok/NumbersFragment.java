package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    //声明音频操作对象
    private MediaPlayer mMediaPlayer;
    //声明播放管理
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener afChangeListener=
            new AudioManager.OnAudioFocusChangeListener(){
                public void onAudioFocusChange(int foucusChange){
                    if (foucusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }else if (foucusChange == AudioManager.AUDIOFOCUS_GAIN){
                        mMediaPlayer.start();
                    }else if (foucusChange == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }
                }
            } ;

    private MediaPlayer.OnCompletionListener mCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
            Toast.makeText(getActivity(),"I'm Over!",Toast.LENGTH_SHORT).show();
        }
    };

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TextView textView = new TextView(getActivity());
        //textView.setText(R.string.hello_blank_fragment);
        View rootView=inflater.inflate(R.layout.activity_numbers,container,false);
        //创建音频播放管理
        mAudioManager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<Word> wordsy = new ArrayList<Word>();
        ArrayList<Integer> words = new ArrayList<Integer>();
        words.add(R.drawable.number_one);
        words.add(R.drawable.number_two);
        words.add(R.drawable.number_three);
        words.add(R.drawable.number_four);
        words.add(R.drawable.number_five);
        words.add(R.drawable.number_six);
        words.add(R.drawable.number_seven);
        words.add(R.drawable.number_eight);
        words.add(R.drawable.number_nine);
        words.add(R.drawable.number_ten);
        for (int i=0;i<6;i++){
            wordsy.add(new Word("number "+i,"lutti is" + i , words.get(i) ,R.raw.thankyoucloveyou));
        }



        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter itemsAdapter =
                new WordAdapter(getActivity(),wordsy );

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //如果用户点击播放过音频不管是否播放完直接释放，然后继续新的开始
                releaseMediaPlayer();

                int result =mAudioManager.requestAudioFocus(afChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN
                );
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    //下面为了叙述方便，我直接使用ComponentName类来替代MediaoButtonReceiver类
                    //ComponentName mbCN = new ComponentName(getActivity().getPackageName(),MediaButtonReceiver.class.getName());
                    //mAudioManager.registerMediaButtonEventReceiver(afChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                    //根据索引获取传入的对象集合项
                    Word word=wordsy.get(i);
                    //创建音乐操作类
                    mMediaPlayer = MediaPlayer.create(getActivity(),word.getmAudioResourceId());
                    mMediaPlayer.start();
                    Toast.makeText(getActivity(),"OK HELLO",Toast.LENGTH_SHORT).show();
                    //设置音频文件播放结束后调用的回调
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer(){
        if (mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer=null;

            mAudioManager.abandonAudioFocus(afChangeListener);
        }
    }
}
