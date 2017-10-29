package de.tse.energysim.energy.charchable;

import de.tse.energysim.energy.Energy;

public interface Charchable {

    /**
     * May throws an Exception if max capacity is reached.
     */
    void charge(Energy energy);
}
