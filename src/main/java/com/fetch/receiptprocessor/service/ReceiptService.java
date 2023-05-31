package com.fetch.receiptprocessor.service;

import com.fetch.receiptprocessor.model.Item;
import com.fetch.receiptprocessor.model.Receipt;
import com.fetch.receiptprocessor.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public ReceiptService() {
    }

    public String processReceipt(Receipt receipt) {

        Integer points = calculatePoints(receipt);

        receipt.setPoints(points);
        receipt.setId(UUID.randomUUID().toString());
        receiptRepository.save(receipt);
        return receipt.getId();
    }

    public Integer getPointsForReceipt(String receiptId) {
        Optional<Receipt> optionalReceipt = receiptRepository.findById(receiptId);
        if (optionalReceipt.isPresent()) {
            Receipt receipt = optionalReceipt.get();
            return receipt.getPoints();
        }
        return 0;
    }

    private int calculatePoints(Receipt receipt) {
        int points = 0;

        String retailer = receipt.getRetailer();
        points += retailer.replaceAll("[^a-zA-Z0-9]", "").length();
        System.out.println("Retailer: " + retailer + ", Points: " + points);

        String total = receipt.getTotal();
        if (total.endsWith(".00")) {
            points += 50;
        }

        double totalAmount = Double.parseDouble(total);
        if (totalAmount % 0.25 == 0) {
            points += 25;
        }

        int itemCount = receipt.getItems().size();
        points += (itemCount / 2) * 5;

        List<Item> items = receipt.getItems();
        for (Item item : items) {
            String description = item.getShortDescription().trim();
            if (description.length() % 3 == 0) {
                double price = Double.parseDouble(item.getPrice());
                int earnedPoints = (int) Math.ceil(price * 0.2);
                points += earnedPoints;
            }
        }

        String purchaseDate = receipt.getPurchaseDate();
        int day = Integer.parseInt(purchaseDate.split("-")[2]);
        if (day % 2 != 0) {
            points += 6;
        }

        String purchaseTime = receipt.getPurchaseTime();
        int hour = Integer.parseInt(purchaseTime.split(":")[0]);
        int minute = Integer.parseInt(purchaseTime.split(":")[1]);

        if (hour == 14 && minute >= 1 || hour == 15 && minute <= 59) {
            points += 10;
        }
        return points;
    }
}
