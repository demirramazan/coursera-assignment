ALTER TABLE book add constraint FK_AUTHOR foreign key (author_id) references author(id);
ALTER TABLE book add constraint FK_GENRE foreign key (genre_id) references genre(id);
ALTER TABLE book add constraint FK_PUBLISHER foreign key (publisher_id) references publisher(id);
ALTER TABLE book_loan add constraint FK_BOOK foreign key (book_id) references book(id);
ALTER TABLE book_loan add constraint FK_BORROWER foreign key (borrower_id) references borrower(id);