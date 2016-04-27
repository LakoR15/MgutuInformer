package ru.mgutupenza.mgutuinformer.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FileIO {

    public static void saveString(String filename, String data, Context context){
        try {
            OutputStream outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
            Log.e("FileIO", "Error write file " + e.getMessage());
        }
    }

    public static String openString(String filename, Context context){

        String str = null;

        try {
            InputStream inputStream = context.openFileInput(filename);

            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String buf;
                StringBuilder builder = new StringBuilder();
                while ((buf = reader.readLine()) != null){
                    builder.append(buf);
                }

                inputStream.close();
                str = builder.toString();
            }
            return str;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            Log.e("FileIO", "Error open file " + e.getMessage());
            return "";
        }

    }

}
