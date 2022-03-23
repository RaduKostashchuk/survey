CREATE TABLE IF NOT EXISTS ADMIN (
  id SERIAL PRIOMARY KEY,
  login varchar(50),
  password varchar(50)
);

INSERT INTO admin VALUES(1, 'admin', '$2a$10$oa0OJWsxkby2PqU8DGzF6excVYe35rb7ahzgwFeDk9caNk7Bgr6aG');