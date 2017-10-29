package de.tse.energysim.energy;

import de.tse.energysim.energy.source.SecureEnergySourceWrapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SecureEnergySourceWrapperTest {

    @Test public void detach() {

        Energy energy = Energy.of(15);
        SecureEnergySourceWrapper wrapper = new SecureEnergySourceWrapper(energy);

        Energy detachedEnergy = wrapper.generate(12);

        assertEquals(12, detachedEnergy.getAmount());
        assertEquals(3, energy.getAmount());
    }

    @Test public void secureDetach() {

        Energy energy = Energy.of(15);
        SecureEnergySourceWrapper wrapper = new SecureEnergySourceWrapper(energy);

        Energy detachedEnergy = wrapper.generate(20);

        assertEquals(15, detachedEnergy.getAmount());
        assertEquals(0, energy.getAmount());
    }
}
