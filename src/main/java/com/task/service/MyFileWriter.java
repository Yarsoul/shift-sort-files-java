package com.task.service;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyFileWriter {
    Logger logger = Logger.getLogger(MyFileWriter.class.getName());

    public void fileWrite(String fileOutput, String result) {
        String fileName = fileOutput.substring(Math.max(0, fileOutput.length() - 8));
        logger.info("Запись файла с результатами \"" + fileName + "\"...");

        File fileOut = new File(fileOutput);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOut),
                    "UTF8"));
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
            System.exit(1);
        }
        logger.info("Файл с результатами \"" + fileName + "\" - успешно записан.");
    }
}
