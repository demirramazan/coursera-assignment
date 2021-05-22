insert into author (id, author_name) values (1,'Henri Alain-Fournier');

insert into publisher (id, publisher_name) values (1,'Tartarus Press');

insert into genre (id, genre_name) values (1,'Science Fiction');

insert into borrower (id, email, borrower_name, phone_number) values (1,'rdemir4434@gmail.com','Ramazan Demir',5064933643);

insert into book (id, available, description, edition, image_url, isbn, book_name, publish_date,
                  rating, author_id, genre_id, number_of_page)
values (1,true,'John Fowles described Le Grand Meaulnes as a novel that ‘has haunted the European mind since it first appeared in 1913. It is a novel one never quite forgets, a book like a secret garden...’',1,'http://www.tartaruspress.com/images/meaulnespbk2.jpg',19999445,'Le Grand Meaulnes and Miracles',
        '1999-01-01',10,1,1,294);

insert into book_loan (id, date_of_arrival, date_of_purchase, due_date, book_id, borrower_id)
values (1,'2021-05-20',null,'2021-05-31',1,1);