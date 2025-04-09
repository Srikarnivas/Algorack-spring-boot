package com.algorack.nftstore.repository;

import com.algorack.nftstore.entity.NFTBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NFTBookRepository extends JpaRepository<NFTBook, Long> {
    List<NFTBook> findByOwnerAddress(String walletAddress);

    List<NFTBook> findByStatus(NFTBook.Status status);

    Optional<NFTBook> findByAssetId(Long assetId);
}
