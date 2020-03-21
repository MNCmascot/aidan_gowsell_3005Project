--clean up
delete from store_info;
delete from book;
delete from publisher;
delete from checkout;
delete from registered_user;

insert into store_info values (250, 30, 'Canada Book Express'); -- $250 expenditures, 30 sold last month

--All made up
--Publisher, email, address, bank_amount, phone_number, publisher_threshold
insert into publisher values ('Span Studio', 'spanstudio@email.com', '1234 Bank Street, A1B2C3, Ottawa, ON', 500, '613-123-4567', 10); 
insert into publisher values ('Dye Bookworks', 'dyebookworks@email.com', '333 Cool Street, D4E5F6, Ottawa, ON', 1500, '613-111-1111', 5); 
insert into publisher values ('Crystal Library', 'crystallibrary@email.com', '650 Mind Crescent, G7H8I9, Ottawa, ON', 200, '613-222-2222', 8); 

--Name, author, ISBN, genre, publisher, num_pages, price, checked out (0 or 1), publisher percent
insert into book values  ('Attacks', 'Michael Smith', 1, 'Sci-fi', 'Span Studio', 250, 50, 0, 25); 
insert into book values  ('Planes', 'Michael Smith', 2, 'Romance', 'Dye Bookworks', 350, 70, 0, 10);
insert into book values  ('Glasses Man', 'Michael Smith', 3, 'Action', 'Span Studio', 300, 100, 0, 25); 
insert into book values  ('Computer', 'Michael Smith', 4, 'Sci-fi', 'Crystal Library', 250, 40, 0, 50);
insert into book values  ('She Drinks', 'Michael Smith', 5, 'Romance', 'Span Studio', 275, 100, 0, 30); 
insert into book values  ('T.V. Wars', 'George Elmer', 6, 'Sci-fi', 'Span Studio', 250, 50, 0, 25); 
insert into book values  ('Closet Dweller', 'George Elmer', 7, 'Horror', 'Dye Bookworks', 200, 50, 0, 25); 
insert into book values  ('Fast Travel', 'George Elmer', 8, 'Sci-fi', 'Dye Bookworks', 150, 100, 0, 15); 
insert into book values  ('What Are You Doing?', 'Ella Sparkles', 9, 'Romance', 'Span Studio', 175, 100, 0, 20); 
insert into book values  ('Heat', 'Ella Sparkles', 10, 'Romance', 'Dye Bookworks', 125, 100, 0, 30); 
insert into book values  ('Sunburnt', 'Bush Bells', 11, 'Horror', 'Span Studio', 55, 100, 0, 5); 
insert into book values  ('The Cat Came Back', 'Becky Chen', 12, 'Romance', 'Crystal Library', 85, 100, 0, 40); 
insert into book values  ('Doctor Confuse', 'Becky Chen', 13, 'Mystery', 'Span Studio', 222, 100, 0, 35); 
insert into book values  ('Blastoff', 'Becky Chen', 14, 'Action', 'Span Studio', 1000, 500, 0, 80); 
insert into book values  ('Roof Jumper', 'Becky Chen', 15, 'Action', 'Crystal Library', 333, 100, 0, 45); 

--username, userID, address
insert into registered_user values ('bookbuyer123', 1, '111 Mint Avenue, K3TH1Z, Ottawa, ON');
insert into registered_user values ('emerson', 2, '222 Mint Avenue, K3TH1V, Ottawa, ON');

--Note: nothing added to checkout because values added to checkout have to change other values in the tables
