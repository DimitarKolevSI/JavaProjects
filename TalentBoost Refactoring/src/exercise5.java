public int depositSum(User user, int sum) {
	if (user.hasAdditionalDeposit()) {
		sum += user.hasAdditionalDeposit();
	}
	
	bank.deposit(user, sum);
}

//Коментар:
//Според мен тази функция също е ненужна и това може да се имплементира директно в класът bank.
//Няма смисъл заради един if да се пише нов метод, който е външен за класа тъй като това може да повлия и на модификаторите за достъп
