ALTER TABLE accounts ADD COLUMN user_id bigint;
ALTER TABLE accounts ADD CONSTRAINT fk_users_accounts FOREIGN KEY (user_id) REFERENCES users (id);