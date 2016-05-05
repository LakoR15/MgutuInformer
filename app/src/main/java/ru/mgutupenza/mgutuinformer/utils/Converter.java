package ru.mgutupenza.mgutuinformer.utils;

public class Converter {

    public static String toString(Double start, Double end){

        String startStr, endStr;

        startStr = String.valueOf(start);
        endStr = String.valueOf(end);

        if (startStr.split("\\.")[1].length() == 1){
            startStr += "0";
        }

        if (startStr.split("\\.")[0].length() == 1){
            startStr = "0" + startStr;
        }

        if (endStr.split("\\.")[1].length() == 1){
            endStr += "0";
        }

        if (endStr.split("\\.")[0].length() == 1){
            endStr = "0" + endStr;
        }

        return startStr+"-"+endStr;
    }

    public static Double startToDouble(String time){

        String start = time.split("-")[0];

        return Double.valueOf(start);
    }

    public static Double endToDouble(String time){

        String end = time.split("-")[1];

        return Double.valueOf(end);
    }

}
