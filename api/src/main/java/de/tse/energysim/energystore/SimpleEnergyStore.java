package de.tse.energysim.energystore;

import de.tse.energysim.energy.Energy;

/**
 * Speicher für Energy.
 * <ol>
 *     <li>Wirft eine Exception wenn es überladen wird</li>
 *     <li>kann aufgeladen werden</li>
 * </ol>
 */
public class SimpleEnergyStore implements EnergyStore {

    private final long maxCapacity;
    private final Energy energy;

    public SimpleEnergyStore(final long maxCapacity) {
        this.energy = Energy.empty();
        this.maxCapacity = maxCapacity;
    }

    @Override public Energy generate(final long value) {
        return energy.detach(value);
    }

    @Override public void charge(final Energy energy) {
        if (this.energy.getAmount() + energy.getAmount() > maxCapacity) {
            throw new IllegalArgumentException("Could not store more energy. Max capacity of " + maxCapacity + " reached");
        }
        this.energy.include(energy);
    }

    @Override public boolean isFull() {
        return getCapacity() == getMaxCapacity();
    }

    public long getMaxCapacity() {
        return maxCapacity;
    }
    public long getCapacity() {
        return energy.getAmount();
    }

    @Override public String toString() {
        return "[EnergyStore, capacity: " + energy.getAmount() + "/" + maxCapacity + "]";
    }
}
