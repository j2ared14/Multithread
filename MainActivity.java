package com.example.jared.multithread;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Context aContext = MainActivity.this;
                FileCreator createTheFile = new FileCreator();
                createTheFile.createFile(aContext);
            }
        }).start();
    }
    public void onClick2(View w) {

        Context aContext = MainActivity.this;
        File theFile =new File(aContext.getFilesDir(), "numbers.txt");
        ThreadManager aManager = new ThreadManager();
        ArrayList<String> theLines = aManager.doInBackground(theFile);
        ArrayAdapter<String> nums = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,theLines);
        ListView aList = (ListView) findViewById(R.id.textView);
        aList.setAdapter(nums);
    }
    public void onClick3(View w) {
        ArrayList<String> empty = new ArrayList<String>();
        ArrayAdapter<String> nums2 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,empty);
        ListView aList = (ListView) findViewById(R.id.textView);
        aList.setAdapter(nums2);
    }
}