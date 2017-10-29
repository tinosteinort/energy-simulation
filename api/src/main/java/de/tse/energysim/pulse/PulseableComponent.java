package de.tse.energysim.pulse;

import de.tse.energysim.energy.EnergyRequestable;

public interface PulseableComponent {

    void onPulse(EnergyRequestable source, PulseControl pulseControl);
}
