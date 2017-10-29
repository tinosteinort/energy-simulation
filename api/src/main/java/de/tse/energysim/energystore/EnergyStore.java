package de.tse.energysim.energystore;

import de.tse.energysim.energy.charchable.Charchable;
import de.tse.energysim.energy.source.EnergySource;

public interface EnergyStore extends EnergySource, Charchable {

    long getCapacity();

    long getMaxCapacity();

    boolean isFull();
}
