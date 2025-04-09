"# Algorack-spring-boot" 


-- Users Table
CREATE TABLE users (
    wallet_address VARCHAR(100) PRIMARY KEY,
    username VARCHAR(100),
    email VARCHAR(100)
);

-- NFT Books Table
CREATE TABLE nft_books (
    id BIGSERIAL PRIMARY KEY,
    asset_id BIGINT UNIQUE NOT NULL,
    name VARCHAR(255),
    url TEXT,
    unitary_price BIGINT,
    quantity BIGINT,
    owner_address VARCHAR(100) REFERENCES users(wallet_address),
    status VARCHAR(20) CHECK (status IN ('FOR_SALE', 'SOLD', 'REMOVED')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Transactions Table
CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    asset_id BIGINT,
    from_address VARCHAR(100),
    to_address VARCHAR(100),
    quantity BIGINT,
    txn_hash VARCHAR(255),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
