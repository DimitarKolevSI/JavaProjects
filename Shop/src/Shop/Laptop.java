package Shop;

import java.util.Objects;

public class Laptop extends LaptopAndPcCategory{
    private double MonitorSize;
    private boolean TouchScreen;

    public Laptop(){
        super();
        MonitorSize = 0.0;
        TouchScreen = false;
    }

    public Laptop(LaptopAndPcCategory old, double monitorSize, boolean touchScreen){
        super(old);
        MonitorSize = monitorSize;
        TouchScreen = touchScreen;

    }

    public Laptop(Laptop old){
        super(old);
        setMonitorSize(old.getMonitorSize());
        setTouchScreen(old.isTouchScreen());
    }

    public Laptop(Product old, String brand, String processor, int HD, int RAM, String motherBoard, String videoCard, double monitorSize, boolean touchScreen) {
        super(old, brand, processor, HD, RAM, motherBoard, videoCard);
        MonitorSize = monitorSize;
        TouchScreen = touchScreen;
    }

    public Laptop(int Amount, double Price, double Rating, int NumberOfRatings, String brand, String processor, int HD, int RAM, String motherBoard, String videoCard, double monitorSize, boolean touchScreen) {
        super(Amount, Price, Rating, NumberOfRatings, brand, processor, HD, RAM, motherBoard, videoCard);
        MonitorSize = monitorSize;
        TouchScreen = touchScreen;
    }

    public double getMonitorSize() {
        return MonitorSize;
    }

    private void setMonitorSize(double monitorSize) {
        MonitorSize = monitorSize;
    }

    public boolean isTouchScreen() {
        return TouchScreen;
    }

    private void setTouchScreen(boolean touchScreen) {
        TouchScreen = touchScreen;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", MonitorSize=" + MonitorSize +
                ", TouchScreen=" + TouchScreen ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
