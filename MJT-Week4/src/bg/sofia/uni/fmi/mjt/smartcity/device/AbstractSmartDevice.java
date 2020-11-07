package bg.sofia.uni.fmi.mjt.smartcity.device;

import bg.sofia.uni.fmi.mjt.smartcity.enums.DeviceType;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractSmartDevice implements SmartDevice {
    protected final String name;
    protected final double powerConsumption;
    protected final LocalDateTime installationDate;
    protected DeviceType type;
    protected String id;

    public AbstractSmartDevice(String name, double powerConsumption, LocalDateTime installationDate) {
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.installationDate = installationDate;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPowerConsumption() {
        return this.powerConsumption;
    }

    @Override
    public LocalDateTime getInstallationDateTime() {
        return this.installationDate;
    }

    @Override
    public DeviceType getType() {
        return this.type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractSmartDevice that = (AbstractSmartDevice) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
