package de.tse.energysim.energy.source;

import de.tse.energysim.energy.Energy;

public interface EnergySource {

    /**
     * May throw an exception if value is bigger than capacity
     */
    Energy generate(long value);
}
