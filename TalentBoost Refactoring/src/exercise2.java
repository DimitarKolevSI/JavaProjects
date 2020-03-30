int total = cart.getTotal();

//Коментар:
//Във cart.getTotal() се предполага, че се смята цялата цена с отстъпката следователно долния if-else е ненужен

if (hasDiscount()) {
	return total * 0.5;
} else {
	return total;
}
