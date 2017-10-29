package de.tse.energysim;

import de.tse.energysim.energy.Energy;
import de.tse.energysim.energystore.SecureEnergyStore;
import de.tse.energysim.pulse.ComponentPulseListener;
import de.tse.energysim.pulse.Pulse;
import de.tse.energysim.pulse.PulseListener;
import de.tse.energysim.pulse.PulseableComponent;

import java.util.ArrayList;
import java.util.List;

public class EnergyDependentPulseController implements PulseController {

    private final SecureEnergyStore energyStore;

    private final int maxPulseCapacity;

    private final List<PulseListener> pulseListeners = new ArrayList<>();

    public EnergyDependentPulseController(final SecureEnergyStore energyStore, final int maxPulseCapacity) {
        this.energyStore = energyStore;
        this.maxPulseCapacity = maxPulseCapacity;
    }

    @Override public void run() {

        while (energyStore.getCapacity() > 0) {

            pulse(calculatePulseCapacity());
            System.out.println(energyStore);
        }
    }

    private void pulse(final long energyAmount) {

        final Energy energy = energyStore.generate(energyAmount);
        final Pulse pulse = new Pulse(energy, pulseListeners);
        pulse.execute();
        energyStore.charge(pulse.getEnergy());
    }

    private long calculatePulseCapacity() {
        return Math.min(maxPulseCapacity, energyStore.getCapacity());
    }

    @Override public void addComponent(final PulseableComponent energyComponent) {
        pulseListeners.add(new ComponentPulseListener(energyComponent));
    }
}
