package ru.clevertec.cleverbank.factory;

import ru.clevertec.cleverbank.factory.model.ReceiptData;

public interface ReceiptFactory<T extends ReceiptData> {

    String createReceipt(T receiptData);

    String getDir();
}
