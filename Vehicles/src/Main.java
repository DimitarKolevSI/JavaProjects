import Vehicles.CargoVehicle;
import Vehicles.Gender;
import Vehicles.PassengerVehicle;
import Vehicles.Vehicle;

public class Main {
    public static int pickPassengersWithVehicles(Vehicle[] vehicles, int vehiclesCount,int passengers){
        for(Vehicle vehicle: vehicles){
            if(vehicle instanceof PassengerVehicle){
                while(true){
                    int pickedUpPassengers = ((PassengerVehicle) vehicle).pickPassengers(passengers);
                    if(pickedUpPassengers == 0) break;
                    passengers -= pickedUpPassengers;
                    if(passengers == 0)
                        return 0;
                }
            }
        }
        return passengers;
    }


    public static void main(String[] args){
//        PassengerVehicle p = new PassengerVehicle(500, 20);
//        PassengerVehicle p1 = new PassengerVehicle(600,30);
//        CargoVehicle c = new CargoVehicle(600,400);
//        CargoVehicle c1 = new CargoVehicle(700,500);
//        Vehicle[] vehicles = new Vehicle[4];
//        vehicles[0] = c;
//        vehicles[1] = c1;
//        vehicles[2] = c;
//        vehicles[3] = c1;
//        System.out.println(pickPassengersWithVehicles(vehicles, 4,10));


        if(Gender.Male == Gender.valueOf("Male")) System.out.println("True");
        else System.out.println("False");

    }
}
