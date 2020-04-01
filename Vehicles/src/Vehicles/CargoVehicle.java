package Vehicles;

public class CargoVehicle extends Vehicle {
    private int CurrentWeight;
    private int MaximumWeight;

    public CargoVehicle(int Weight, int maximumWeight){
        super(Weight);
        CurrentWeight = 0;
        MaximumWeight = maximumWeight;
    }

    public boolean loadCargo(int goods){
        if(goods + CurrentWeight <= MaximumWeight){
            CurrentWeight += goods;
            return true;
        }
        else
            return false;
    }

    public boolean voidCargo(int goods){
        int newWeight = CurrentWeight - goods;
        if(newWeight > 0 && goods > 0){
            CurrentWeight = newWeight;
            return true;
        }
        else
            return false;
    }

    public int getCargo(){
        return CurrentWeight;
    }

    @Override
    public void travel(int travelDistance) {
        Distance += travelDistance;
        Amortisation += travelDistance*(Weight + CurrentWeight);
    }
}
