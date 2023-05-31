package com.fetch.receiptprocessor.controller;
import com.fetch.receiptprocessor.model.Receipt;
import com.fetch.receiptprocessor.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processReceipt(@RequestBody Receipt receipt) {
        String processedReceipt = receiptService.processReceipt(receipt);
        return ResponseEntity.status(HttpStatus.OK).body(processedReceipt);
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<Integer> getPointsForReceipt(@PathVariable String id) {
        Integer points = receiptService.getPointsForReceipt(id);
        return ResponseEntity.status(HttpStatus.OK).body(points);
    }
}
