package com.example.jared.multithread;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator{
    public void createFile (Context aContext) {
        File numFile = new File(aContext.getFilesDir(), "numbers.txt");
        try{
            FileWriter aWriter = new FileWriter(numFile);
            for (Integer i = 1; i < 11; i++) {
                aWriter.write((i.toString()));
                System.out.println(i);
                try{
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
    }
}