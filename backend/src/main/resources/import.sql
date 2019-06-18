INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

DROP SEQUENCE if Exists account_seq;
CREATE SEQUENCE account_seq start 1345 increment 2;

INSERT INTO accounts (id, description, iban, initial_balance, type) VALUES (nextval('account_seq'), 'External Account', 'GR1232133123123312312312321999', 0, 'EXTERNAL');
