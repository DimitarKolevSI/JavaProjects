package bg.sofia.uni.fmi.mjt.smartcity.device;

import bg.sofia.uni.fmi.mjt.smartcity.enums.DeviceType;

import java.time.LocalDateTime;

public class SmartTrafficLight extends AbstractSmartDevice {
    private static long deviceCounter = 0L;

    public SmartTrafficLight(String name, double powerConsumption, LocalDateTime installationDate) {
        super(name, powerConsumption, installationDate);
        this.type = DeviceType.TRAFFIC_LIGHT;
        this.id = generateId();
    }

    private String generateId() {
        return String.format("%s-%s-%d", this.type.getShortName(), this.name, deviceCounter++);
    }
}
