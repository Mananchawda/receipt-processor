package com.fetch.receiptprocessor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "receipts")
public class Receipt {
    @Id
    private String id;

    @Column(name = "retailer")
    private String retailer;
    @Column(name = "purchase_date")
    private String purchaseDate;

    @Column(name = "purchase_time")
    private String purchaseTime;

    public Receipt(String id, String retailer, String purchaseDate, String purchaseTime, List<Item> items, String total, Integer points) {
        this.id = id;
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.items = items;
        this.total = total;
        this.points = points;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;

    @Column(name = "total")
    private String total;

    @Column(name = "points")
    private Integer points;

    public Receipt() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
