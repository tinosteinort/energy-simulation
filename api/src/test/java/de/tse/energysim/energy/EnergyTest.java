package de.tse.energysim.energy;

import de.tse.energysim.energystore.SecureEnergyStore;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnergyTest {

    private SecureEnergyStore battery;

    @Before  public void setup() {
        battery = new SecureEnergyStore(100);
        battery.charge(Energy.of(101));
    }

    @Test public void generate() {

        assertEquals(100, battery.getCapacity());

        Energy energy = battery.generate(10);
        assertEquals(10, energy.getAmount());
        assertEquals(90, battery.getCapacity());
    }

    @Test public void detach() {

        assertEquals(100, battery.getCapacity());

        Energy energy = battery.generate(20);
        Energy detachedEnergy = energy.detach(15);

        assertEquals(80, battery.getCapacity());
        assertEquals(5, energy.getAmount());
        assertEquals(15, detachedEnergy.getAmount());
    }

    @Test public void include() {

        assertEquals(100, battery.getCapacity());

        Energy energyOne = battery.generate(20);
        Energy energyTwo = battery.generate(20);

        assertEquals(60, battery.getCapacity());

        energyOne.include(energyTwo);

        assertEquals(60, battery.getCapacity());
        assertEquals(40, energyOne.getAmount());
        assertEquals(0, energyTwo.getAmount());
    }
}
