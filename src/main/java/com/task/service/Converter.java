package com.task.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class Converter {
    Logger logger = Logger.getLogger(MyFileReader.class.getName());

    public String convertArrIntegerToString(Integer[] arrInteger) {
        logger.info("Конвертация массива Integer в строку для записи...");

        StringBuilder result = new StringBuilder();
        for (Integer element : arrInteger) {
            result.append(element).append("\n");
        }

        logger.info("Конвертация массива Integer в строку завершена успешно.");
        return result.toString();
    }


    public String convertArrStringToString(String[] arrString) {
        logger.info("Конвертация массива String в строку для записи...");

        StringBuilder result = new StringBuilder();
        for (String element : arrString) {
            result.append(element).append("\n");
        }

        logger.info("Конвертация массива String в строку завершена успешно.");
        return result.toString();
    }


    public Integer[] convertListStringToArrInteger(ArrayList<String> stringArrayList) {
        Integer[] arrResult = new Integer[stringArrayList.size()];
        for (int i = 0; i < stringArrayList.size(); i++) {
            int intCh = stringArrayList.get(i).charAt(0);
            arrResult[i] = intCh;
        }
        return arrResult;
    }


    public String[] convertArrIntegerToArrString(Integer[] arrInteger) {
        String[] arrResult = new String[arrInteger.length];
        for (int i = 0; i < arrInteger.length; i++) {
            int number = arrInteger[i];
            char ch = (char) number;
            String s = Character.toString(ch);
            arrResult[i] = s;
        }
        return arrResult;
    }
}
