package ru.clevertec.cleverbank.writer.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import ru.clevertec.cleverbank.factory.ReceiptFactory;
import ru.clevertec.cleverbank.factory.model.ReceiptData;
import ru.clevertec.cleverbank.writer.Writer;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class WriterPdf implements Writer {

    private static final String OUTPUT_NAME = "R_pdf_";
    private static final String FORMAT = ".pdf";

    @Override
    public void writeData(ReceiptData receiptData, ReceiptFactory factory) {
        String data = factory.createReceipt(receiptData);
        String htmlCheck = "<html><pre>" + data + "</pre></html>";
        try {
            Document document = new Document(PageSize.A4, 50, 50, 10, 10);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(generateName(factory.getDir(), OUTPUT_NAME, FORMAT)));
            document.open();
            document.newPage();
            InputStream checkHtmlIS = new ByteArrayInputStream(htmlCheck.getBytes(StandardCharsets.UTF_8));
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, checkHtmlIS);
            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
