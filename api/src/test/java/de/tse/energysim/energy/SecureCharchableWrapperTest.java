package de.tse.energysim.energy;

import de.tse.energysim.energy.charchable.SecureCharchableWrapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SecureCharchableWrapperTest {

    @Test public void charge() {

        Energy energy = Energy.of(0);
        SecureCharchableWrapper wrapper = new SecureCharchableWrapper(energy, 10);

        Energy newEnergy = Energy.of(8);
        wrapper.charge(newEnergy);

        assertEquals(8, energy.getAmount());
        assertEquals(0, newEnergy.getAmount());
    }

    @Test public void secureCharge() {

        Energy energy = Energy.of(0);
        SecureCharchableWrapper wrapper = new SecureCharchableWrapper(energy, 10);

        Energy newEnergy = Energy.of(15);
        wrapper.charge(newEnergy);

        assertEquals(10, energy.getAmount());
        assertEquals(5, newEnergy.getAmount());
    }
}
