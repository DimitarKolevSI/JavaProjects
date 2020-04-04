package Vehicles;

public abstract class Vehicle {
    protected int Weight;
    protected int Distance;
    protected int Amortisation;
    public Vehicle(){
        Weight = 0;
        Distance = 0;
        Amortisation = 0;
    }
    public Vehicle(int weight){
        Weight = weight;
        Distance = 0;
        Amortisation = 0;
    }

    public abstract void travel(int travelDistance);

    public int getWeight(){
        return Weight;
    }

    public int getDistance(){
        return Distance;
    }

    public int getAmortisation(){
        return Amortisation;
    }
}
