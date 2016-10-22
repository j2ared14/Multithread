package com.example.jared.multithread;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ThreadManager extends AsyncTask<File,Void,ArrayList<String>> {
    ArrayList<String> numbers;
    ThreadManager () {
        numbers = new ArrayList<String>();
    }
    @Override
    protected ArrayList<String> doInBackground(File... params) {
        try {
            FileReader br = new FileReader(params[0]);
            BufferedReader bufferRead = new BufferedReader(br);
            String line = "";
            try {
                while ((line = bufferRead.readLine()) !=null) {
                    numbers.add(line);
                    try {
                        Thread.sleep(250);
                    }
                    catch (InterruptedException eq) {
                    }
                    line = "";
                }
     //           for(int i = 0;i < numbers.size(); i++) {
     //               System.out.println(numbers.get(i));
     //           }
            }
            catch (IOException eg) {
            }
        }
        catch (FileNotFoundException et) {
        }
        return numbers;
    }
}