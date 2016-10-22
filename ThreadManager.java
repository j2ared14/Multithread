package com.example.jared.multithread;
import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ThreadManager extends AsyncTask<Activity,Double,ArrayList<String>> {
    Activity mainActivity;
    ArrayList<String> numbers;
    ThreadManager () {
        numbers = new ArrayList<String>();
    }
    @Override
    protected ArrayList<String> doInBackground(Activity... params) {
        mainActivity = params[0];
        File theFile =new File(mainActivity.getFilesDir(), "numbers.txt");
        try {
            FileReader br = new FileReader(theFile);
            BufferedReader bufferRead = new BufferedReader(br);
            String line = "";
            Double progress= 1.0;
            try {
                while ((line = bufferRead.readLine()) !=null) {
                    progress ++;
                    numbers.add(line);
                    try {
                        publishProgress((progress*.1));
                        Thread.sleep(250);
                    }
                    catch (InterruptedException eq) {
                    }
                }
            }
            catch (IOException eg) {
            }
        }
        catch (FileNotFoundException et) {
        }
        return numbers;
    }

    @Override
    protected void onProgressUpdate(Double... values) {
        ProgressBar theBar = (ProgressBar) mainActivity.findViewById(R.id.progressBar);
        Double percent = theBar.getMax()*values[0];
        theBar.setProgress(percent.intValue());
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        ArrayList<String> theLines = strings;
        ArrayAdapter<String> nums = new ArrayAdapter<String>(mainActivity,android.R.layout.simple_list_item_1,theLines);
        ListView aList = (ListView) mainActivity.findViewById(R.id.textView);
        aList.setAdapter(nums);
    }
}