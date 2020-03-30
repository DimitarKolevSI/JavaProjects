public void calculateCartTotal(Cart cart) {
	int discountPercent = cart.getDiscount();
	double discountAmount = (discountPercent / 100) * cart.getTotal();

	return cart.total - discountAmount;
}

//Коментар:
//Този метод е ненужен, тъй като можем просто да си сметнем тотала в метода cart.getTotal()