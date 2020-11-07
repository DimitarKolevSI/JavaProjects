package bg.sofia.uni.fmi.mjt.smartcity.hub;

import bg.sofia.uni.fmi.mjt.smartcity.device.SmartDevice;
import bg.sofia.uni.fmi.mjt.smartcity.enums.DeviceType;

import java.util.*;

public class SmartCityHub {
    private Map<String, SmartDevice> smartDevices;

    public SmartCityHub() {
        smartDevices = new LinkedHashMap<>();
    }

    /**
     * Adds a @device to the SmartCityHub.
     *
     * @throws IllegalArgumentException         in case @device is null.
     * @throws DeviceAlreadyRegisteredException in case the @device is already registered.
     */
    public void register(SmartDevice device) throws DeviceAlreadyRegisteredException {
        if (device == null) {
            throw new IllegalArgumentException();
        } else if (smartDevices.containsKey(device.getId())) {
            throw new DeviceAlreadyRegisteredException("This device is already registered");
        } else {
            smartDevices.put(device.getId(), device);
        }
    }

    /**
     * Removes the @device from the SmartCityHub.
     *
     * @throws IllegalArgumentException in case null is passed.
     * @throws DeviceNotFoundException  in case the @device is not found.
     */
    public void unregister(SmartDevice device) throws DeviceNotFoundException {
        if (device == null) {
            throw new IllegalArgumentException();
        } else if (!smartDevices.containsKey(device.getId())) {
            throw new DeviceNotFoundException("This device is not in the system");
        } else {
            smartDevices.remove(device.getId());
        }
    }

    /**
     * Returns a SmartDevice with an ID @id.
     *
     * @throws IllegalArgumentException in case @id is null.
     * @throws DeviceNotFoundException  in case device with ID @id is not found.
     */
    public SmartDevice getDeviceById(String id) throws DeviceNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException();
        } else if (!smartDevices.containsKey(id)) {
            throw new DeviceNotFoundException("There is no device in the system with this id");
        } else {
            return smartDevices.get(id);
        }
    }

    /**
     * Returns the total number of devices with type @type registered in SmartCityHub.
     *
     * @throws IllegalArgumentException in case @type is null.
     */
    public int getDeviceQuantityPerType(DeviceType type) {
        if (type == null) {
            throw new IllegalArgumentException();
        } else {
            int counter = 0;
            for (String key : smartDevices.keySet()) {
                if (key.startsWith(type.getShortName())) {
                    counter++;
                }
            }
            return counter;
        }

    }

    /**
     * Returns a collection of IDs of the top @n devices which consumed
     * the most power from the time of their installation until now.
     * <p>
     * The total power consumption of a device is calculated by the hours elapsed
     * between the two LocalDateTime-s: the installation time and the current time (now)
     * multiplied by the stated nominal hourly power consumption of the device.
     * <p>
     * If @n exceeds the total number of devices, return all devices available sorted by the given criterion.
     *
     * @throws IllegalArgumentException in case @n is a negative number.
     */
    public Collection<String> getTopNDevicesByPowerConsumption(int n) {

        List<SmartDevice> devices = new ArrayList<>(smartDevices.values());
        Collections.sort(devices, new CompareByTotalConsumption());
        List<String> keys = new ArrayList<>();
        if (n >= devices.size()) {
            for (SmartDevice sd : devices) {
                keys.add(sd.getId());
            }
        } else {
            for (SmartDevice sd : devices.subList(0, n)) {
                keys.add(sd.getId());
            }
        }
        return keys;
    }

    /**
     * Returns a collection of the first @n registered devices, i.e the first @n that were added
     * in the SmartCityHub (registration != installation).
     * <p>
     * If @n exceeds the total number of devices, return all devices available sorted by the given criterion.
     *
     * @throws IllegalArgumentException in case @n is a negative number.
     */
    public Collection<SmartDevice> getFirstNDevicesByRegistration(int n) {
        List<SmartDevice> devices = new ArrayList<>(smartDevices.values());
        if (n >= devices.size()) {
            return devices;
        } else {
            return devices.subList(0, n);
        }
    }
}
