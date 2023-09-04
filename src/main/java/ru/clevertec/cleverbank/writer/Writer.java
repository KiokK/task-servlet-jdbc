package ru.clevertec.cleverbank.writer;

import ru.clevertec.cleverbank.factory.ReceiptFactory;
import ru.clevertec.cleverbank.factory.model.ReceiptData;

import java.io.File;
import java.util.UUID;


public interface Writer {

    String DIR = System.getProperty("user.dir");

    void writeData(ReceiptData receiptData, ReceiptFactory factory);

    default String generateName(String dir, String outputName, String format) {
        String fileName = DIR + dir;
        File file = new File(fileName);
        if (!file.exists()){
            file.mkdirs();
        }
        return fileName + outputName + UUID.randomUUID() + format;
    }

}
