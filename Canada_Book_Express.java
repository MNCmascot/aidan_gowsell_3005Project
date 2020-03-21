import java.sql.*;
import java.util.*;

 
public class Canada_Book_Express {

    public static void main(String[] args) {
    	
    	String in = ""; //Used for all user input.
    	
    	Scanner input = new Scanner(System.in);

    	//System.out.println("You entered " + courseid);
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Canada Book Express", "postgres", "puredata");
            Statement statement = connection.createStatement();
			)
			{
        	
        	do {
            	System.out.print("Enter '1' for User\n"
            			+ "Enter '2' for Owner\n"
            			+ "and 'q' to quit: ");
            	in = input.nextLine();
            	in = in.toLowerCase();
        	} while (!in.equals("1") && !in.equals("2") && !in.equals("q"));
        	
        	if (in.equals("q")) { //exit
        		System.out.println("Exiting...");
        		System.exit(0);
        	}
        	//----------------USER---------------
        	else if (in.equals("1")){ 
        		String user = ""; //Remember user
        		int userID = -1;
        		
        		in = "-1"; //Set as not one of the options
        		while (!in.equals("1") && !in.equals("2") && !in.equals("q")) {
        			
        			//Get user choice
        			System.out.print("\nEnter '1' to login\n"
                			+ "Enter '2' to register a new user\n"
                			+ "Enter 'q' to quit: ");
                	in = input.nextLine();
                	in = in.toLowerCase();
                	
                	
        			//////////////////LOG IN///////////////
        			if(in.equals("1")){
        				ResultSet resultSet;
        				
        				in = "-1"; //set to something to enter loop again
        				
        				System.out.println("Enter your username: ");
        				user = input.nextLine();
        				
        				resultSet = statement.executeQuery("select *"
                				+ " from registered_user"
                				+ " where username = '" + user + "'");
        				
        				if (resultSet.next()){ //Found a user with that name
        					System.out.println("Welcome user " + user + "!");
        					userID = resultSet.getInt("userid");
        					String address = "";
        					
        					//Handle address
        					System.out.println("Your current address is: " + resultSet.getString("address"));
        	
                    		while (!in.equals("y") && !in.equals("n")){
                    			System.out.println("Would you like to change your address(y / n)?");
            					in = input.nextLine();
            					in = in.toLowerCase();
                    		}
                    		if (in.equals("y")) {
                    			System.out.print("Enter new address here: ");
                				address = input.nextLine();
                				
                    			statement.execute("update registered_user"
                    					+ " set address = '" + address + "'"
                    					+ " where username = '" + user + "'");
                    			System.out.println("Address updated!");
                    		} 
        					
        					//--------------USER LOOP 2------------
                    		//Note: this loop is set within the other loop to allow users to logout
        	        		in = "-1"; //Set as not one of the options to enter LOOP 2
        	        		while (!in.equals("1") && !in.equals("2") && !in.equals("3") 
        	        				&& !in.equals("4") && !in.equals("5") && !in.equals("q")) {
        	        					
        	        			
        	        			//Get user choice
        	        			System.out.print("\nEnter '1' to search for a book\n"
        	                			+ "Enter '2' to select a book\n"
        	                			+ "Enter '3' to checkout a book\n"
        	                			+ "Enter '4' to check your order\n"
        	                			+ "Enter '5' to logout\n"
        	                			+ "Enter 'q' to quit: ");
        	                	in = input.nextLine();
        	                	in = in.toLowerCase();
        	                	
        	                	//////////////SEARCH FOR A BOOK//////////////////
        	                	if (in.equals("1")){
        	                		String search = "";
        	                		int isbn = 1;
        	                		
        	                		in = "-1"; //set as val to enter loop
            	        			//Get user choice
        	                		while (!in.equals("1") && !in.equals("2") 
        	                				&& !in.equals("3") && !in.equals("4")){
        	                			System.out.print("\nEnter '1' to search by title\n"
	            	                			+ "Enter '2' to search by author\n"
	            	                			+ "Enter '3' to search by isbn\n"
	            	                			+ "Enter '4' to search by genre: ");
        	                			in = input.nextLine();
        	                		}
        	                		
        	                		//Search by name
        	                		if (in.equals("1")){
        	                			System.out.print("\nEnter title to search for: ");
        	                			search = input.nextLine();
        	                			
        	                			resultSet = statement.executeQuery("select *"
        	                					+ " from book"
        	                					+ " where book_name = '" + search + "' and checked_out = 0");
        	                			System.out.println("Printing results...");
        	                			while (resultSet.next()){
        	                        		System.out.printf("ISBN: %-8d", resultSet.getInt("isbn"));
        	                        		System.out.printf("Book Title: %-51s\n", resultSet.getString("book_name"));
        	                			}
        	                		} 
        	                		//Search by author
        	                		else if (in.equals("2")){
        	                			System.out.print("\nEnter author to search for: ");
        	                			search = input.nextLine();
        	                			
        	                			resultSet = statement.executeQuery("select *"
        	                					+ " from book"
        	                					+ " where book_author = '" + search + "' and checked_out = 0");
        	                			System.out.println("Printing results...");
        	                			while (resultSet.next()){
        	                				System.out.printf("ISBN: %-8d", resultSet.getInt("isbn"));
        	                        		System.out.printf("Book Title: %-51s", resultSet.getString("book_name"));
        	                        		System.out.printf("Book Author: %-50s\n", resultSet.getString("book_author"));
        	                			}
        	                		}
        	                		//Search by isbn
        	                		else if (in.equals("3")){
        	                			System.out.print("\nEnter isbn to search for: ");
        	                			isbn = input.nextInt();
        	                			input.nextLine(); //clear input
        	                			
        	                			resultSet = statement.executeQuery("select *"
        	                					+ " from book"
        	                					+ " where isbn = " + isbn + " and checked_out = 0");
        	                			System.out.println("Printing results...");
        	                			if (resultSet.next()){ //only one result possible
        	                        		System.out.printf("ISBN: %-8d", resultSet.getInt("isbn"));
        	                        		System.out.printf("Book Title: %-51s\n", resultSet.getString("book_name"));
        	                			} else {
        	                				System.out.println("Nothing found!");
        	                			}
        	                		}
        	                		//Search by genre
        	                		else if (in.equals("4")){
        	                			System.out.print("\nEnter author to search for: ");
        	                			search = input.nextLine();
        	                			
        	                			resultSet = statement.executeQuery("select *"
        	                					+ " from book"
        	                					+ " where genre = '" + search + "' and checked_out = 0");
        	                			System.out.println("Printing results...");
        	                			while (resultSet.next()){
        	                				System.out.printf("ISBN: %-8d", resultSet.getInt("isbn"));
        	                        		System.out.printf("Book Title: %-50s", resultSet.getString("book_name"));
        	                        		System.out.printf("Book Genre: %-50s\n", resultSet.getString("genre"));
        	                			}
        	                		}
        	                		
        	                		in = "-1"; //set as val to enter loop
        	                		
        	                	} 
        	                	//////////////SELECT A BOOK///////////////
        	                	else if (in.equals("2")){
        	                		System.out.print("\nEnter ISBN to search for: ");
    	                			int isbn = input.nextInt();
    	                			input.nextLine(); //clear
    	                			
    	                			in = "-1"; //set as val to enter loop
    	                			
    	                			resultSet = statement.executeQuery("select *"
    	                					+ " from book"
    	                					+ " where isbn = '" + isbn + "' and checked_out = 0");
    	                			if (resultSet.next()){
    	                				System.out.println("Title: " + resultSet.getString("book_name"));
    	                				System.out.println("Author: " + resultSet.getString("book_author"));
    	                				System.out.println("Genre: " + resultSet.getString("genre"));
    	                				System.out.println("Publisher: " + resultSet.getString("publisher_name"));
    	                				System.out.println("Number of pages: " + resultSet.getInt("num_pages"));
    	                				System.out.println("Price: $" + resultSet.getDouble("price"));
    	                			} else {
    	                				System.out.println("Book not found!");
    	                			}
    	                			
    	                        	System.out.println("\nPress enter to return.");
    	                        	input.nextLine();
    	                			
        	                	} 
        	                	
        	                	//////////////CHECKOUT A BOOK///////////////
        	                	else if (in.equals("3")){
        	                		ResultSet checkoutSet, publisherSet, storeSet;
        	                		System.out.print("\nEnter ISBN to search for: ");
    	                			int isbn = input.nextInt();

    	                			input.nextLine(); //clear input

    	                			in = "-1"; //set as val to enter loop
    	                			
    	                			resultSet = statement.executeQuery("select *"
    	                					+ " from book"
    	                					+ " where isbn = '" + isbn + "' and checked_out = 0");
    	                			
    	                			//BOOK FOUND and not checked out
    	                			if (resultSet.next() && resultSet.getString("checked_out").equals("0")){ 
    	                				String publisher_name = resultSet.getString("publisher_name");
    	                				double price = resultSet.getDouble("price");
    	                				int publisher_percent = resultSet.getInt("publisher_percent");
    	                				int order = 1;
  
    	                				
    	                				checkoutSet = statement.executeQuery("select max(order_number)"
    	                						+ " from checkout");
    	                				if (checkoutSet.next()){
    	                					order = checkoutSet.getInt("max");
    	                					if (order != 0) {
    	                						order = checkoutSet.getInt("max") + 1;
        	                				} else {
        	                					order = 1;
        	                				}
    	                					System.out.println("Your order number is: " + order + ", don't forget it!");

    	                            		//add the order
    	                            		statement.execute("insert into checkout"
    	                            				+ " values(" + order + ", " + isbn + ", " + userID + ", 'Just shipped!');");
    	                        			statement.execute("update book"
    	                        					+ " set checked_out = 1"
    	                        					+ " where isbn = " + isbn);
    	                				}
    	                				//HANDLE PUBLISHER BALANCE CHANGE
    	                				//get publisher 
    	                				publisherSet = statement.executeQuery("select *"
    	                						+ " from publisher"
    	                						+ " where publisher_name = '" + publisher_name + "'");
    	                				
    	                				if (publisherSet.next()){
	        	                			double publisherBalance = (publisherSet.getDouble("bank_amount") + price*((double)publisher_percent/100));
	        	                			int threshold = publisherSet.getInt("publisher_threshold");
	        	                			
	        	                			System.out.println("New balance for " + publisher_name + ": $" + publisherBalance);
	        	                			
	        	                			statement.execute("update publisher"
	        	                					+ " set bank_amount = " + publisherBalance
	        	                					+ " where publisher_name = '"+ publisher_name + "'");
	    	                				/////////////////////////////////////
	        	                			
	        	                			//EMAIL PUBLISHER CHECK
	        	                			
	        	                			int lastMonthSold = 0;        	                			
	        	                			//get lastmonthsold
	        	                			storeSet = statement.executeQuery("select *"
	        	                					+ " from store_info");
	        	                			if (storeSet.next()){
	            	                			lastMonthSold = storeSet.getInt("lastmonthsold");
	        	                			} else {
	        	                				System.out.println("error finding store.");
	        	                				System.exit(1);
	        	                			}
	        	                			
	        	                			//get number of books not sold in store
	        	                			resultSet = statement.executeQuery("select count(*)"
	        	                						+ " from book"
	        	                						+ " where checked_out = 0");
	        	                			if (resultSet.next()){
	        	                				if (resultSet.getInt("count") < threshold){
	        	                					System.out.println("\nEmail sent to " + publisher_name + ", telling them "
	        	                							+ "to purchase how many books were sold last month: " + lastMonthSold);
	        	                				}
	        	                			}
    	                				}

        	                			
        	                			
    	                			} else {
    	                				System.out.println("Book not found! (might already be checked out)");
    	                			}
    	                			
        	                	}
        	                	//////////////CHECK YOUR ORDER////////////////
        	                	else if (in.equals("4")){
        	                		ResultSet bookSet;
        	                		String title = "", location = "";
        	                		System.out.print("\nEnter your order number: ");
    	                			int order = input.nextInt();
    	                			input.nextLine(); //clear input

    	                			in = "-1"; //set as val to enter loop again after
    	                			
    	                			//Find order
    	                			resultSet = statement.executeQuery("select *"
    	                					+ " from checkout"
    	                					+ " where order_number = "+ order + " and userid = " + userID);
    	                			if (resultSet.next()){
    	                				location = resultSet.getString("location");
    	                				bookSet = statement.executeQuery("select *"
        	                					+ " from book"
        	                					+ " where isbn = "+ resultSet.getInt("isbn"));
    	                				if (bookSet.next()){
	    	                				title = bookSet.getString("book_name");
	    	                				System.out.println("\nBook title: " + title
	    	                						+ "\nCurrent location: " + location);
    	                				} else {
    	                					System.out.println("Book not found!");
    	                				}
    	                			} else {
    	                				System.out.println("Order not found!");
    	                			}
        	                	} 
        	                	//////////////LOGOUT/////////////////
        	                	else if (in.equals("5")){
        	                		user = ""; //Clear user
        	                		in = "4"; //Value contained in 2nd loop but not in 1st loop to return	
        	                	}
        	                	//////////////EXIT/////////////
        	                	else if (in.equals("q")){ 
        	                		System.out.println("Exiting...");
        	                		System.exit(0);
        	                	} 
        	                	
        	                	
        	        		} //END OF USER LOOP 2
        					
        				} else{
        					System.out.println("User not found!");
        				}
        					
        				
        			} 
        			///////////////REGISTER USER/////////////
        			else if (in.equals("2")){
        				String address = "";
        				int userid = 1;
        				ResultSet resultSet;
        				
        				in = "-1"; //set to something to enter loop again
        				
        				System.out.println("Enter new username: ");
        				user = input.nextLine();
        				
        				resultSet = statement.executeQuery("select *"
                				+ " from registered_user"
                				+ " where username = '" + user + "'");
        				
        				if (resultSet.next()){ //Found a user with that name
        					System.out.println("Username already exists!");
        					continue;
        				}
        				System.out.println("Enter address for user in the form: "
        						+ "\nStreet address, postal code, city, province"
        						+ "\nEx: 222 Mint Avenue, K3TH1V, Ottawa, ON");
        				System.out.print("Enter here: ");
        				address = input.nextLine();
        				
        				resultSet = statement.executeQuery("select max(userid)"
        						+ " from registered_user");
        				
        				if (resultSet.next()){ //there is a max
        					userid = resultSet.getInt("max") + 1; //get highest userid and add 1 to it
        				} else {
        					userid = 1; //start at 1
        				}
        				
                		//add the user
                		statement.execute("insert into registered_user"
                				+ " values('" + user + "', " + userid + ", '" + address + "');");
                		System.out.println("User added!");
                		
        			}
        			
        			if (in.equals("1")){      			
        				
        			}
                	else if (in.equals("q")){ //exit
                		System.out.println("Exiting...");
                		System.exit(0);
                	} 
        		} //END OF FIRST USER LOOP
 	
        		
        	} 
        	
        	
        	
        	//----------------OWNER-----------------
        	else if (in.equals("2")){ 

        		in = "-1"; //Set as not one of the options
        		while (!in.equals("1") && !in.equals("2") && !in.equals("3") 
        				&& !in.equals("4") && !in.equals("5") && !in.equals("q")) {
                	
        			//Get user choice
        			System.out.print("\nEnter '1' to add a book\n"
                			+ "Enter '2' to remove a book\n"
                			+ "Enter '3' to check the store report\n"
                			+ "Enter '4' to modify the total expenditures value\n"
                			+ "Enter '5' to modify the number of books sold last month\n"
                			+ "Enter 'q' to quit: ");
                	in = input.nextLine();
                	in = in.toLowerCase();
                	
                	//Note this is in the do-while 
                	///////////////////////ADD A BOOK////////////////////
                	if (in.equals("1")){ 
                		//Create necessary variables (ALL EXCEPT checked_out)
                		String name = "", author = "", genre = "", publisher_name = "";
                		int isbn = 0, num_pages = -1, publisher_percent = -1;
                		double price = -1;
                		ResultSet resultSet;
                		
                		in = "-1"; //set to something to enter loop again -> Done before any continues
                		
                		//handle ISBN
                		System.out.println("Enter ISBN of book being added: ");
                		isbn = input.nextInt();
                		resultSet = statement.executeQuery("select *"
                				+ " from book"
                				+ " where isbn = " + isbn);
                		//return if isbn exists
                		if (resultSet.next()){ //book exists
                			System.out.println("Book with isbn " + isbn + " already exists.");
                			input.nextLine(); //clear scanner input
                			continue;
                		}
                		
                		//Handle publisher
                		System.out.println("Enter publisher name (make sure it exists): ");
                		input.nextLine(); //clear scanner input
                		publisher_name = input.nextLine();
                		resultSet = statement.executeQuery("select *"
                				+ " from publisher"
                				+ " where publisher_name = '" + publisher_name + "'");
                		//return if publisher doesn't exists
                		if (!resultSet.next()){ //publisher doesn't exist
                			System.out.println("Publisher: " + publisher_name + " doesn't exist.");
                			continue;
                		}
                		
                		//Handle name
                		System.out.println("Enter book name: ");
                		name = input.nextLine();
                		
                		//Handle author
                		System.out.println("Enter author's (full) name (Ex: Michael Smith): ");
                		author = input.nextLine();
                		
                		//Handle genre
                		System.out.println("Enter genre (starts with a capital letter!): ");
                		genre = input.nextLine();
                		
                		//Handle num pages
                		while (num_pages > 9999 || num_pages < 1){
                			System.out.println("Enter number of pages (integer from 1 to 9999): ");
                			num_pages = input.nextInt();
                		}
                		
                		//Handle price
                		while (price > 99999 || price < 0){
	                		System.out.println("Enter price (double from $0 to $99999): ");
	                		input.nextLine(); //clear scanner input
	                		price = input.nextDouble();
                		}
                		
                		//Handle publisher percent
                		while (publisher_percent > 100 || publisher_percent < 0){
                			System.out.println("Enter percent publisher gets (integer from 0 to 100): ");
                			input.nextLine(); //clear scanner input
                			publisher_percent = input.nextInt();
                		}
                		
                		//add the book
                		statement.execute("insert into book"
                				+ " values('" + name + "', '" + author + "', " + isbn + ", '" 
                				+ genre + "', '" + publisher_name + "', " + num_pages + ", " 
                				+ price + ", 0, " + publisher_percent + ");");
                		System.out.println("Book added!");
                		
                		input.nextLine(); //clear scanner input
                		
                	}
                	///////////////////////REMOVE A BOOK////////////////////
                	else if (in.equals("2")){                 		
                		int isbn = 0;
                		System.out.println("Enter ISBN of book to remove (regardless of whether it is checked out or not): ");
                		isbn = input.nextInt();
                		input.nextLine(); //clear input
                		
                		//make sure it exists
                		ResultSet bookSet = statement.executeQuery("select *"
                				+ " from book"
                				+ " where isbn = " + isbn);
                		if (bookSet.next()){ //book exists
                			if (bookSet.getString("checked_out").equals("0")){
                    			statement.execute("delete from book"
                    					+ " where isbn = " + isbn);
                    			System.out.println("Book removed.");
                			} else {
                				System.out.println("Book has already been checked out and can't be removed!");
                			}
                		} else {
                			System.out.println("Book does not exist!");
                		}
                		
                		
                		
                		System.out.println("\nPress enter to return.");
                    	input.nextLine();
                		in = "-1"; //set to something to enter loop again
                	}
                	/////////////CHECK STORE REPORT///////////////
                	else if (in.equals("3")){      		
                		//////////////SALES V.S. EXPENDITURES//////////////
                		double sales = 0, expenditures = 0, profit = 0;
                		
                		System.out.println("\nSALES V.S. EXPENDITURES");
                		
                		//get sales of books
                		ResultSet bookSet = statement.executeQuery("select sum(price)"
                			+ " from book"
                			+ " where checked_out = 1");
                		if (bookSet.next()){
                			sales = bookSet.getDouble("sum");
                		}
    
                		System.out.println("Total sales (before splitting with publisher): $" + sales);
                		
                		//get expenditures
                		ResultSet storeSet = statement.executeQuery("select expenditures"
                    		+ " from store_info");
                		if (storeSet.next()){
                			expenditures = storeSet.getDouble("expenditures");
                		}
                    		System.out.println("Total expenditures: $" + expenditures);
                    		
                    	//calculate profit
                    	profit = sales - expenditures;
                    	if (profit >= 0) {
                    		System.out.println("Your company has a profit of: " + profit);
                    	} else {
                    		System.out.println("Your company has a loss of: $" + profit);
                    	}
                    	
                    	System.out.println("\nPress enter to return.");
                    	input.nextLine();
                    	
                    	
                    	////////////////////SALES PER GENRE/////////////////
                    	
                    	System.out.println("SALES PER GENRE");
                    	bookSet = statement.executeQuery("select genre, checked_out, sum(price)"
                    			+ " from book"
                    			+ " group by genre, checked_out"
                    			+ " having checked_out = 1");
                    	while (bookSet.next()){
                    		System.out.printf("Genre: %-51s", bookSet.getString("genre"));
                    		System.out.printf("Sales: $%7.2f\n", bookSet.getDouble("sum"));
                    	}
                    	
                    	System.out.println("\nPress enter to continue with report.");
                    	input.nextLine();
                    	
                    	////////////////////SALES PER AUTHOR/////////////////
                    	
                    	System.out.println("SALES PER AUTHOR");
                    	bookSet = statement.executeQuery("select book_author, checked_out, sum(price)"
                    			+ " from book"
                    			+ " group by book_author, checked_out"
                    			+ " having checked_out = 1");
                    	while (bookSet.next()){
                    		System.out.printf("Author: %-50s", bookSet.getString("book_author"));
                    		System.out.printf("Sales: $%7.2f\n", bookSet.getDouble("sum"));
                    	}
                    	
                    	System.out.println("\nPress enter to return.");
                    	input.nextLine();
                		in = "-1"; //set to something to enter loop again
                	}
                	
                	//////////////////MODIFY EXPENDITURES/////////////////
                	else if (in.equals("4")){ 
                		double tempExpenditures = 0;
                		System.out.println("Enter new total expenditures value, or a negative number to return: ");
                		tempExpenditures = input.nextDouble();
                		if (tempExpenditures >= 0){ //user entered double to change
                			
                			System.out.println("You changed the expenditures to " + tempExpenditures + "!");
                			statement.execute("update store_info"
                					+ " set expenditures = " + tempExpenditures);
                		} else { //user exited
                			System.out.println("Expenditures value not changed, returning.");
                		}
                		in = "-1"; //set to something to enter loop again
                		input.nextLine(); //clear scanner input   		
                	}
                	
                	///////////////////MODIFY NUMBOOKS SOLD//////////////////
                	else if (in.equals("5")){ 
                		int tempSold = 0;
                		System.out.println("Enter number of books sold last month, or a negative number to return: ");
                		tempSold = input.nextInt();
                		if (tempSold >= 0){ //user entered integer
                			System.out.println("You changed the number of books sold last month to " + tempSold + "!");
                			statement.execute("update store_info"
                					+ " set lastmonthsold = " + tempSold);
                		} else { //user exited
                			System.out.println("books sold value not changed, returning.");
                		}
                		in = "-1"; //set to something to enter loop again
                		input.nextLine(); //clear scanner input
                		
                		in = "-1"; //set to something to enter loop again
                	}
                	else if (in.equals("q")){ //exit
                		System.out.println("Exiting...");
                		System.exit(0);
                	} 
            	}  //End of while-loop
        	}///////////End of Owner section/////////////////
        	
        	
            ResultSet resultSet = statement.executeQuery("select *"
			+ " from book"); //NOTE: it complained without the spaces at the front

        
            //Add all current prereqs onto stack
            while (resultSet.next()){
            	//	System.out.println(resultSet.getString("book_name"));
            }
 
        } catch (Exception sqle) {
            System.out.println("Exception: " + sqle);
        }
        input.close();
    }
    
}