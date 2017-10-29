package de.tse.energysim.energy.source;

import de.tse.energysim.energy.Energy;

public class SecureEnergySourceWrapper implements SecureEnergySource {

    private final Energy energy;

    public SecureEnergySourceWrapper(final Energy energy) {
        this.energy = energy;
    }

    @Override public Energy generate(final long value) {
        if (value > energy.getAmount()) {
            return energy.detach(energy.getAmount());
        }
        return energy.detach(value);
    }
}
