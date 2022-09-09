package com.task.service;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyFileReader {
    private static final Logger logger = Logger.getLogger(MyFileReader.class.getName());

    public Integer[] readFileInteger(String fileInput) {
        int flag = 0;
        Integer[] arrResult = new Integer[0];
        ArrayList<Integer> listIntegers = new ArrayList<>();
        String fileName = fileInput.substring(Math.max(0, fileInput.length() - 8));

        try {
            logger.info("������ ����� � ������ ������� \"" + fileName + "\"...");
            int i = 0;
            String line;
            BufferedReader br = new BufferedReader(new FileReader(fileInput));

            while ((line = br.readLine()) != null) {
                if ((line.indexOf('\u0020') < 0) && isNumber(line)) {
                    if (i == 0) {
                        listIntegers.add(Integer.valueOf(line));
                    } else if (i > 0 && listIntegers.get(i - 1) < Integer.parseInt(line)) {
                        listIntegers.add(Integer.valueOf(line));
                    } else if (i > 0 && listIntegers.get(i - 1) > Integer.parseInt(line)) {
                        logger.warning("���� \"" + fileName + "\" �� ������������!");
                        flag = 1;
                        listIntegers.add(Integer.valueOf(line));
                    }
                } else {
                    logger.warning("��������� ������ ����� \""
                            + fileName
                            + "\": ���� �������� ������ ������� "
                            + "��� ������ ����� �� �������� ��������!");
                    break;
                }
                ++i;
            }

            br.close();
            logger.info("���� \"" + fileName + "\" - ��������.");
        } catch (IOException | NumberFormatException e) {
            logger.log(Level.WARNING, e.getMessage());
        }


        if (!listIntegers.isEmpty()) {
            arrResult = listIntegers.toArray(new Integer[0]);
        } else {
            return arrResult;
        }

        if (flag == 1) {
            sortFileData(fileName, arrResult);
        }

        return arrResult;
    }


    public Integer[] readFileString(String fileInput) {
        int flag = 0;
        Integer[] arrResult = new Integer[0];
        ArrayList<String> listStrings = new ArrayList<>();
        String fileName = fileInput.substring(Math.max(0, fileInput.length() - 8));

        try {
            logger.info("������ ����� �� �������� \"" + fileName + "\"...");
            int i = 0;
            String line;
            BufferedReader br = new BufferedReader(new FileReader(fileInput));

            while ((line = br.readLine()) != null) {
                if (line.indexOf('\u0020') < 0 && isValid(line)) {
                    if (i == 0) {
                        listStrings.add(line);
                    } else if (i > 0 && listStrings.get(i - 1).charAt(0) < line.charAt(0)) {
                        listStrings.add(line);
                    } else if (i > 0 && listStrings.get(i - 1).charAt(0) > line.charAt(0)) {
                        logger.warning("���� \"" + fileName + "\" �� ������������!");
                        flag = 1;
                        listStrings.add(line);
                    }
                } else {
                    logger.warning("��������� ������ ����� \""
                            + fileName +
                            "\": ���� �������� ������ ������� ��� � ������ ������ ����� 1 �������!");
                    break;
                }
                ++i;
            }

            br.close();
            logger.info("���� \"" + fileName + "\" - ��������.");
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }


        if (!listStrings.isEmpty()) {
            Converter converter = new Converter();
            arrResult = converter.convertListStringToArrInteger(listStrings);
        } else {
            return arrResult;
        }


        if (flag == 1) {
            sortFileData(fileName, arrResult);
        }

        return arrResult;
    }


    private boolean isNumber(String string) {
        int intValue;

        if (string == null || string.equals("")) {
            logger.warning("������� ������ ������!");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            logger.warning("� ������ ������� �� �������� ��������!");
            return false;
        }
    }

    private boolean isValid(String string) {
        if (string == null || string.isEmpty()) {
            logger.warning("������� ������ ������!");
            return false;
        }

        if (string.length() > 1) {
            logger.warning("� ������ ������� ����� 1 �������!");
            return false;
        } else {
            return true;
        }
    }

    private void sortFileData(String fileName, Integer[] arrResult) {
        logger.info("���������� ������� �� ����� \"" + fileName + "\"...");
        SortMerge mySortClass = new SortMerge();
        mySortClass.myQuickSort(arrResult, 0, arrResult.length - 1);
        logger.info("���������� ������� �� ����� \"" + fileName + "\" - ���������.");
    }
}
