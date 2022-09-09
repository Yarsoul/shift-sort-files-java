package com.task.controller;

import com.task.Main;
import com.task.service.Converter;
import com.task.service.MyFileReader;
import com.task.service.MyFileWriter;
import com.task.service.SortMerge;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;


public class MyController {
    private Logger logger = Logger.getLogger(Main.class.getName());
    private String fileOut = "";


    public void start(String[] args) {
        ArrayList<Integer[]> listIntegers = getListIntegers(args);

        if (listIntegers.isEmpty()) {
            logger.warning("Программа не выполнена - отсутствуют данные файлов и/или в файлах!");
            System.exit(1);
        }

        String resultString = getResultString(args, listIntegers);

        MyFileWriter myFileWriter = new MyFileWriter();
        myFileWriter.fileWrite(fileOut, resultString);
        logger.info("Программа выполнена успешно.");
    }


    private ArrayList<Integer[]> getListIntegers(String[] args) {
        String fileIn = "";
        ArrayList<Integer[]> listIntegers = new ArrayList<>();

        if (args != null && args.length > 2) {
            Path pathOut = Paths.get(args[1]);
            if (!Files.notExists(pathOut)) {
                fileOut = args[1];
            } else {
                logger.warning("Файл для записи \"" + args[1] + "\" - не существует!");
                System.exit(1);
            }

            for (int i = 2; i < args.length; i++) {
                Path pathIn = Paths.get(args[i]);
                if (!Files.notExists(pathIn)) {
                    fileIn = args[i];
                    MyFileReader myFileReader = new MyFileReader();
                    if (args[0].equals("-i")) {
                        Integer[] input = myFileReader.readFileInteger(fileIn);
                        if (input != null && input.length > 0) {
                            listIntegers.add(input);
                        }
                    } else if (args[0].equals("-s")) {
                        Integer[] input = myFileReader.readFileString(fileIn);
                        if (input != null && input.length > 0) {
                            listIntegers.add(input);
                        }
                    }
                } else {
                    logger.warning("Файл для чтения \"" + args[i] + "\" - не существует!");
                }
            }
        } else {
            logger.warning("Программа не выполнена - не все аргументы введены!");
            System.exit(1);
        }

        return listIntegers;
    }


    private String getResultString(String[] args, ArrayList<Integer[]> listIntegers) {
        String resultString = "";
        Converter converter = new Converter();

        if (args[0].equals("-i")) {
            Integer[] sortArr = getSortArray(listIntegers);
            resultString = converter.convertArrIntegerToString(sortArr).trim();
        } else if (args[0].equals("-s")) {
            Integer[] sortArrInt = getSortArray(listIntegers);
            String[] sortArrString = converter.convertArrIntegerToArrString(sortArrInt);
            resultString = converter.convertArrStringToString(sortArrString).trim();
        } else {
            logger.warning("Программа не выполнена - не задан параметр типа данных!");
            System.exit(1);
        }

        return resultString;
    }


    private Integer[] getSortArray(ArrayList<Integer[]> listIntegers) {
        Integer[] arrIntegerResult = new Integer[0];
        if (listIntegers.size() == 1) {
            arrIntegerResult = listIntegers.get(0);
        } else if (listIntegers.size() > 1) {
            for (Integer[] listInteger : listIntegers) {
                SortMerge sortMerge = new SortMerge();
                arrIntegerResult = sortMerge.sortMergeInteger(arrIntegerResult, listInteger);
            }
        }
        return arrIntegerResult;
    }
}
