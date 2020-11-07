package bg.sofia.uni.fmi.mjt.smartcity.device;

import bg.sofia.uni.fmi.mjt.smartcity.enums.DeviceType;

import java.time.LocalDateTime;

public class SmartLamp extends AbstractSmartDevice {
    private static long deviceCounter = 0;

    public SmartLamp(String name, double powerConsumption, LocalDateTime installationDate) {
        super(name, powerConsumption, installationDate);
        this.type = DeviceType.LAMP;
        this.id = generateId();
    }

    private String generateId() {
        return String.format("%s-%s-%d", this.type.getShortName(), this.name, deviceCounter++);
    }
}
