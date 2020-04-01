package Vehicles;

public class PassengerVehicle extends Vehicle{
    private int CurrentPassengers;
    private int MaximumPassengers;

    public PassengerVehicle(int weight, int maximumPassengers){
        super(weight);
        CurrentPassengers = 0;
        MaximumPassengers = maximumPassengers;
    }

    public int pickPassengers(int passengers){
        if(passengers >= 0){
            if(passengers + CurrentPassengers <= MaximumPassengers){
                CurrentPassengers += passengers;
                return passengers;
            }
            else{
                int diffrence = MaximumPassengers - CurrentPassengers;
                CurrentPassengers = MaximumPassengers;
                return diffrence;
            }
        }
        else
            return -1;
    }

    public int dropPassengers(int passengers){
        if(passengers >= 0){
            int droppedPassengers;
            if(passengers >= CurrentPassengers){
                droppedPassengers = CurrentPassengers;
                CurrentPassengers = 0;
            }
            else{
                CurrentPassengers -= passengers;
                droppedPassengers = passengers;
            }
            return droppedPassengers;
        }
        else
            return -1;
    }

    public int getPassengers(){
        return CurrentPassengers;
    }

    @Override
    public void travel(int travelDistance) {
        Amortisation += travelDistance*(Weight + CurrentPassengers*70);
    }
}
