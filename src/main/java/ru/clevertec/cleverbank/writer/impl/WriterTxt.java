package ru.clevertec.cleverbank.writer.impl;

import ru.clevertec.cleverbank.writer.Writer;
import ru.clevertec.cleverbank.factory.ReceiptFactory;
import ru.clevertec.cleverbank.factory.model.ReceiptData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterTxt implements Writer {

    private static final String OUTPUT_NAME = "R_txt_";
    private static final String FORMAT = ".txt";

    @Override
    public void writeData(ReceiptData receiptData, ReceiptFactory factory) {
        String receipt = factory.createReceipt(receiptData);
        File file = new File(generateName(factory.getDir(), OUTPUT_NAME, FORMAT));
        try (FileWriter writer = new FileWriter(file)) {
            writer.append(receipt);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
