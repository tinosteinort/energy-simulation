package de.tse.energysim;

public class EnergySpecification {

    private final long storeCapacity;
    private final long requestingEnergyAmount;

    public EnergySpecification(final long storeCapacity, final long requestingEnergyAmount) {
        this.storeCapacity = storeCapacity;
        this.requestingEnergyAmount = requestingEnergyAmount;
    }

    public long getStoreCapacity() {
        return storeCapacity;
    }
    public long getRequestingEnergyAmount() {
        return requestingEnergyAmount;
    }
}
