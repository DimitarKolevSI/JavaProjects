//Wrong
/*class Book {
	public String getName() { };
	public String getISBN() { };
}

class User {
	private Book book;
	public String getBookName() { return book.getName(); }
	public String getBookISBN() { return book.getISBN(); }
}

class UsersView {
	public void printUser(User u) {
		printUser(u);
		printBookName(u.getBookName());
		printBookISBN(u.getBookISBN());
	}
}*/

//Коментар:
//Прекалено много навръзване на функции и излишното ѝм извикване.
//Също така имаме рекурсия без дънно, което ще ни създаде главоболия
//И в последния клас клас имаме методи, които не са декларирани

class Book {
	public String getName() { /* ... */ };
	public String getISBN() { /* ... */ };
}

class User {
	private Book book;
	public void printUser(){
		System.out.println(book.getName());
		System.out.println(book.getISBN());
	}
}