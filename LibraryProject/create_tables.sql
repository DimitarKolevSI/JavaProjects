DROP TABLE BOOKS;
CREATE TABLE IF NOT EXISTS BOOKS (
	id SERIAL PRIMARY KEY,
	title VARCHAR(100) NOT NULL,
	author VARCHAR(100) NOT NULL,
	year_published SMALLINT NOT NULL CHECK (year_published > 0),
	pages SMALLINT NOT NULL CHECK (pages > 0),
	genre VARCHAR(20) NOT NULL,
	rating DECIMAL(3,2) NOT NULL CHECK (rating > 0) DEFAULT 0,
	number_of_ratings INTEGER NOT NULL DEFAULT 0,
	cover_url VARCHAR(100) NOT NULL DEFAULT 'http://4.bp.blogspot.com/_g3gjaai4a_0/TAhFLcAU7FI/AAAAAAAAAKY/4oHhAR-t-iQ/s1600/book%2Bclip%2Bart.jpg',
	review VARCHAR(500),
	UNIQUE(title,author)
);
SELECT * FROM books;

DROP TABLE READERS;
CREATE TABLE IF NOT EXISTS READERS (
	username VARCHAR(20) PRIMARY KEY,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	gender CHAR(1) DEFAULT '-',
	email VARCHAR(40) NOT NULL UNIQUE,
	password VARCHAR(20) NOT NULL
);
SELECT * FROM READERS;

DROP TABLE IF EXISTS READ_BOOKS;
CREATE TABLE IF NOT EXISTS READ_BOOKS(
	username VARCHAR(20),
	books_id INTEGER CHECK(books_id > 0),
	CONSTRAINT pk_username_books_id 
		PRIMARY KEY(username,books_id),
	CONSTRAINT fk_books_id FOREIGN KEY(books_id)
		REFERENCES books(id) ON DELETE CASCADE,
	CONSTRAINT fk_username FOREIGN KEY(username)
		REFERENCES readers(username) ON DELETE CASCADE
	
);
SELECT * FROM read_books;

DROP TABLE IF EXISTS readers_review;
CREATE TABLE IF NOT EXISTS readers_review(
	username VARCHAR(20),
	books_id INTEGER CHECK(books_id > 0),
	review VARCHAR(300),
	posted_date DATE DEFAULT CURRENT_DATE,
	posted_time TIME DEFAULT LOCALTIME(0),
	CONSTRAINT pk_rr_username_books_id 
		PRIMARY KEY(username,books_id),
	CONSTRAINT fk_rr_books_id FOREIGN KEY(books_id)
		REFERENCES books(id) ON DELETE CASCADE,
	CONSTRAINT fk_rr_username FOREIGN KEY(username)
		REFERENCES readers(username) ON DELETE CASCADE
);
SELECT * FROM readers_review;

DROP TABLE IF EXISTS rated_book;
CREATE TABLE IF NOT EXISTS rated_book(
	username VARCHAR(20),
	books_id INTEGER CHECK(books_id > 0),
	rating DECIMAL(3,2),
	CHECK (rating >= 0),
	CONSTRAINT pk_rb_username_books_id 
		PRIMARY KEY(username,books_id),
	CONSTRAINT fk_rb_books_id FOREIGN KEY(books_id)
		REFERENCES books(id) ON DELETE CASCADE,
	CONSTRAINT fk_rb_username FOREIGN KEY(username)
		REFERENCES readers(username) ON DELETE CASCADE
); 
SELECT * FROM rated_book;



