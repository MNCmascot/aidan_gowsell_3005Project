
/*
Table used to track the bookstore's total expenditures and how many books were sold 
in the last month. Both values must be manually changed from the owner interface.
Note: There will only be one set of values in this table at all times.
*/
create table store_info
	(expenditures		numeric(12, 2),
	 lastMonthSold		numeric(4, 0), 
	 store_name			varchar(50),
	 primary key (store_name)
	);
	
create table publisher
	(publisher_name		varchar(50), --publisher name
	 email				varchar(50),
	 address			varchar(200), --Entire address in one line, assuming it is entered correctly
	 bank_amount		numeric(12, 2), --amount of money the publisher has made
	 phone_number		varchar(15),
	 publisher_threshold	numeric(3, 0), --minimum books a certain publisher wants in the store, if the store has less than this amount after a checkout, the publisher is notified to order as many books as were sold last month.
	 primary key (publisher_name)
	);
	
create table book
	(book_name			varchar(50),
	 book_author		varchar(50), --first and last name not separated
	 ISBN				numeric(8, 0), --ISBN entered when owner adds a book
	 genre				varchar(50),
	 publisher_name		varchar(50),
	 num_pages			numeric(4, 0),
	 price				numeric(7, 2),
	 checked_out		numeric(1, 0), --value used to determine if a book has been checkout out, default starts as 0, when checked out, set to 1. Value used for owner to check stats as well.
	 publisher_percent	numeric(3, 0), --percentage of money publisher earns from sale of this book. 
	 primary key (ISBN),
	 foreign key (publisher_name) references publisher
	);
	
create table registered_user --table for registered users
	(username			varchar(50), --not necessarily their name
	 userID				numeric(4, 0), --ID chosen based on highest ID + 1, user doesn't choose this
	 address			varchar(200), --address again all in one line
	 primary key (userID)
	);
	
create table checkout --checkout used for books selected by user (which are automatically bought)
	(order_number		numeric(6, 0), --chosen based on highest order num + 1, user doesn't choose
	 ISBN				numeric(8, 0), 
	 userID				numeric(4, 0),
	 location			varchar(200), --where the book is currently in shipping, not changable through user interfaces. Calue will be always the same for simplicity.
	 primary key (order_number, ISBN, userID),
	 foreign key (ISBN) references book,
	 foreign key (userID) references registered_user
	);