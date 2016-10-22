package com.example.jared.multithread;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator extends AsyncTask<Activity, Double, Void>{
    Activity mainActivity;
    FileCreator() {
        mainActivity = new Activity();
    }
    @Override
    protected Void doInBackground(Activity... params) {
        mainActivity = params[0];
        File numFile = new File(params[0].getFilesDir(), "numbers.txt");
        try{
            FileWriter aWriter = new FileWriter(numFile);
            for (Integer i = 1; i < 11; i++) {
                aWriter.write((i.toString()));
                System.out.println(i);
                try{
                    publishProgress((i*.1));
                    Thread.sleep(250);
                }
                catch (InterruptedException er) {
                }
                if (i < 10) {
                    aWriter.write(String.format("%n"));
                }
            }
            aWriter.close();
        }
        catch(IOException ec) {
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Double... values) {
        ProgressBar theBar = (ProgressBar) mainActivity.findViewById(R.id.progressBar);
        Double percent = theBar.getMax()*values[0];
        theBar.setProgress(percent.intValue());
    }
}