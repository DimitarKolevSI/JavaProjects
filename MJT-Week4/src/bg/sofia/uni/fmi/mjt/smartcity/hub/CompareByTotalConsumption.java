package bg.sofia.uni.fmi.mjt.smartcity.hub;

import bg.sofia.uni.fmi.mjt.smartcity.device.SmartDevice;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;

public class CompareByTotalConsumption implements Comparator<SmartDevice> {
    @Override
    public int compare(SmartDevice o1, SmartDevice o2) {
        LocalDateTime today = LocalDateTime.of(2020,11,4,11,00);
        long firstDuration = Duration.between(o1.getInstallationDateTime(),today).toHours();
        long secondDuration = Duration.between(o2.getInstallationDateTime(),today).toHours();
        return (int)(firstDuration * o1.getPowerConsumption() - secondDuration * o2.getPowerConsumption());
    }
}
