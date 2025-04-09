package com.algorack.nftstore.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "nft_books")
public class NFTBook {

    public enum Status {FOR_SALE, SOLD, REMOVED}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long assetId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private Long unitaryPrice;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private String ownerAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private LocalDateTime createdAt;

    public NFTBook() {
    }

    public NFTBook(LocalDateTime createdAt, Status status, String ownerAddress, Long quantity, Long unitaryPrice, String url, String name, Long assetId) {
        this.createdAt = createdAt;
        this.status = status;
        this.ownerAddress = ownerAddress;
        this.quantity = quantity;
        this.unitaryPrice = unitaryPrice;
        this.url = url;
        this.name = name;
        this.assetId = assetId;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getUnitaryPrice() {
        return unitaryPrice;
    }

    public void setUnitaryPrice(Long unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}