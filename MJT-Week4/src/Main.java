import bg.sofia.uni.fmi.mjt.smartcity.device.SmartDevice;
import bg.sofia.uni.fmi.mjt.smartcity.device.SmartTrafficLight;
import bg.sofia.uni.fmi.mjt.smartcity.enums.DeviceType;
import bg.sofia.uni.fmi.mjt.smartcity.hub.DeviceAlreadyRegisteredException;
import bg.sofia.uni.fmi.mjt.smartcity.hub.DeviceNotFoundException;
import bg.sofia.uni.fmi.mjt.smartcity.hub.SmartCityHub;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String... args) throws DeviceAlreadyRegisteredException, DeviceNotFoundException {
        SmartDevice smartDevice = new SmartTrafficLight("First",5,
                LocalDateTime.of(2020,11,07,10,06));
        SmartDevice smartDevice1 = new SmartTrafficLight("Second",6,
                LocalDateTime.of(2020,11,07,10,06));
        SmartDevice smartDevice2 = new SmartTrafficLight("Third",8,
                LocalDateTime.of(2020,11,07,10,06));
        SmartDevice smartDevice3 = new SmartTrafficLight("Fourth",10,
                LocalDateTime.of(2020,11,07,10,06));
        SmartDevice smartDevice4 = new SmartTrafficLight("Fifth",1,
                LocalDateTime.of(2020,11,07,10,06));
        SmartCityHub hub = new SmartCityHub();
        hub.register(smartDevice);
        hub.register(smartDevice1);
        hub.register(smartDevice2);
        hub.register(smartDevice3);
        hub.register(smartDevice4);
        List<SmartDevice> devices = new ArrayList<>(hub.getFirstNDevicesByRegistration(3));
        List<String> keys = new ArrayList<>(hub.getTopNDevicesByPowerConsumption(3));
        System.out.println("The Traffic lights are " + hub.getDeviceQuantityPerType(DeviceType.CAMERA));
        for(SmartDevice device: devices){
            System.out.println(device.getName());
        }
        for(String key: keys){
            System.out.println(key);
        }
        System.out.println(hub.getDeviceQuantityPerType(DeviceType.TRAFFIC_LIGHT));
    }
}
