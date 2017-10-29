package de.tse.energysim.energystore;

import de.tse.energysim.energy.Energy;
import de.tse.energysim.energy.charchable.SecureCharchable;
import de.tse.energysim.energy.charchable.SecureCharchableWrapper;
import de.tse.energysim.energy.source.SecureEnergySource;
import de.tse.energysim.energy.source.SecureEnergySourceWrapper;

/**
 * Speicher für Energy. Kann nicht überladen werden
 * <ol>
 *     <li>hat eine Obergrenze über die nicht hinaus aufgeladen werden kann</li>
 *     <li>kann aufgeladen werden</li>
 * </ol>
 */
public class SecureEnergyStore implements EnergyStore, SecureEnergySource, SecureCharchable {

    private final Energy energy;
    private final long maxCapacity;
    private final SecureCharchableWrapper chargeWrapper;
    private final SecureEnergySourceWrapper sourceWrapper;

    public SecureEnergyStore(final long maxCapacity) {
        this.energy = Energy.empty();
        this.maxCapacity = maxCapacity;
        this.chargeWrapper = new SecureCharchableWrapper(energy, maxCapacity);
        this.sourceWrapper = new SecureEnergySourceWrapper(energy);
    }

    @Override public Energy generate(final long value) {
        return sourceWrapper.generate(value);
    }

    @Override public void charge(final Energy energy) {
        chargeWrapper.charge(energy);
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
