package com.fetch.receiptprocessor.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fetch.receiptprocessor.model.Receipt;
import org.springframework.stereotype.Repository;

public interface ReceiptRepository extends JpaRepository<Receipt, String> {
}
