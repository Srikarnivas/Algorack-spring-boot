package com.algorack.nftstore.controller;

import com.algorack.nftstore.entity.NFTBook;
import com.algorack.nftstore.entity.Transaction;
import com.algorack.nftstore.repository.NFTBookRepository;
import com.algorack.nftstore.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nft")
public class NFTBookController {

    @Autowired
    private NFTBookRepository repo;

    @Autowired
    private TransactionRepository txnRepo;

    @PostMapping("/create")
    public ResponseEntity<?> createNFT(@RequestBody NFTBook book) {
        return ResponseEntity.ok(repo.save(book));
    }

    @GetMapping("/listings")
    public List<NFTBook> getListings() {
        return repo.findByStatus(NFTBook.Status.FOR_SALE);
    }

    @GetMapping("/owned/{address}")
    public List<NFTBook> getOwned(@PathVariable String address) {
        return repo.findByOwnerAddress(address);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteNFT(@RequestParam Long assetId, @RequestParam String requester) {
        var book = repo.findByAssetId(assetId).orElseThrow();
        if (!book.getOwnerAddress().equals(requester)) return ResponseEntity.status(403).body("Not owner");
        book.setStatus(NFTBook.Status.REMOVED);
        return ResponseEntity.ok(repo.save(book));
    }

    @PutMapping("/set-price")
    public ResponseEntity<?> updatePrice(@RequestParam Long assetId, @RequestParam Long newPrice, @RequestParam String requester) {
        var book = repo.findByAssetId(assetId).orElseThrow();
        if (!book.getOwnerAddress().equals(requester)) return ResponseEntity.status(403).body("Not owner");
        book.setUnitaryPrice(newPrice);
        return ResponseEntity.ok(repo.save(book));
    }

    @PostMapping("/buy")
    public ResponseEntity<?> recordBuy(@RequestBody Transaction txn) {
        // Update ownership in nft_books
        var book = repo.findByAssetId(txn.getAssetId()).orElseThrow();
        book.setOwnerAddress(txn.getToAddress());
        book.setStatus(NFTBook.Status.SOLD);
        repo.save(book);

        return ResponseEntity.ok(txnRepo.save(txn));
    }

}

