CREATE TABLE mail_extensions (
  id BIGSERIAL PRIMARY KEY,
  mail_extension VARCHAR(255) NOT NULL UNIQUE,
  created_at TIMESTAMP DEFAULT now(),
  updated_at TIMESTAMP DEFAULT now()
);