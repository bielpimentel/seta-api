CREATE TABLE new_account_requests (
  email VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  token VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT now()
);