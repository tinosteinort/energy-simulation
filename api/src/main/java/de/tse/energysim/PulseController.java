package de.tse.energysim;

import de.tse.energysim.pulse.PulseableComponent;

public interface PulseController extends Runnable {

    void addComponent(PulseableComponent energyComponent);
}
