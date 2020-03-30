public String getBookName(Book book) {
	if (book.getName() == null || book.getName().length() == 0) {
		return "<Unknown>";
	}
	return book.getName();
}

//Коментар:
//Отново няма нужда според мен от този метод, тъй като се предполага, че методът book.getName() връща като резултат низ,
//а и getBookName не прави нещо по-различно