package de.tse.energysim.energystore.storebuffer;

import de.tse.energysim.energy.EnergyCallable;
import de.tse.energysim.energy.EnergyRequestable;

public interface StoreBuffer {

    void request(EnergyRequestable energyRequestable, long amount, EnergyCallable code);
}
