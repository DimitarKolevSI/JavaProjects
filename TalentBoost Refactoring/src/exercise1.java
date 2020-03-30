//Wrong
public void printDetails() {
	String name = this._first + this._last;
	System.out.println(String.format("Name: %s", name));
	System.out.println(String.format("Age: %d", _age));
}

//Коментар
//Няма нужда от променливата name
//Забравено е да се сложи празно място между първото и второто име
//println(String.format()) може лесно да се замести с printf

//Right
public void printDetails(){
	System.out.printf("Name: %s %s%n",this._first,this._second);
	System.out.println("Age: %d",_age)
}