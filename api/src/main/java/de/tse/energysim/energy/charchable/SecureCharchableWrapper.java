package de.tse.energysim.energy.charchable;

import de.tse.energysim.energy.Energy;

public class SecureCharchableWrapper implements SecureCharchable {

    private final Energy energy;
    private final long maxCapacity;

    public SecureCharchableWrapper(final Energy energy, final long maxCapacity) {
        this.energy = energy;
        this.maxCapacity = maxCapacity;
    }

    @Override public void charge(final Energy energy) {
        final long additionalEnergy;
        if (this.energy.getAmount() + energy.getAmount() > maxCapacity) {
            additionalEnergy = maxCapacity - this.energy.getAmount();
        }
        else {
            additionalEnergy = energy.getAmount();
        }
        this.energy.include(energy.detach(additionalEnergy));
    }
}
