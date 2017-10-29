package de.tse.energysim.energy;

public class EnergyFactory {

    public static Energy create(final long amount) {
        return Energy.of(amount);
    }
}
