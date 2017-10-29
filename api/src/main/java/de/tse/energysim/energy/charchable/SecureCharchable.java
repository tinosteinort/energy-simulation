package de.tse.energysim.energy.charchable;

import de.tse.energysim.energy.Energy;

public interface SecureCharchable extends Charchable {

    /**
     * Charges only to max capacity
     */
    void charge(Energy energy);
}
