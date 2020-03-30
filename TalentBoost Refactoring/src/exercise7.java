import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

//Wrong
class Distances {
	public static double calculateDistance(int x1, int y1, int x2, int y2) {
		return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
	}
}

//Коментар:
//Както казахме на лекцията, ще е много по-добре тези кординати да бъдат подадени като две точки, за да не се обърка реда.
//Някой би си помислил, че са зададени х1,х2,у1,у2 и методът да омармалади нещата.
//Също така е добре координатите да са реални числа

//Right
class Point{
	public double x;
	public double y;
}

class Distance{
	public static double calculateDistance(Point one,Point two){
		return sqrt(pow(two.x - one.x,2) + pow(two.y - one.y,2));
	}
}
