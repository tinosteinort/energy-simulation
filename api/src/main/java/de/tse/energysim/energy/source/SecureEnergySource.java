package de.tse.energysim.energy.source;

import de.tse.energysim.energy.Energy;

public interface SecureEnergySource extends EnergySource {

    Energy generate(long value);
}
