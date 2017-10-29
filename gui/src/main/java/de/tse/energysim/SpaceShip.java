package de.tse.energysim;

import de.tse.energysim.energy.Energy;
import de.tse.energysim.energystore.EnergyStore;
import de.tse.energysim.pulse.ComponentPulseListener;
import de.tse.energysim.pulse.Pulse;
import de.tse.energysim.pulse.PulseListener;
import de.tse.energysim.pulse.PulseableComponent;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SpaceShip implements PulseController {

    private final Duration delay;
    private final Duration running;

    private final EnergyStore energyStore;
    private final long maxPulseCapacity;

    private long start;

    private final List<PulseListener> pulseListeners = new ArrayList<>();

    public SpaceShip(final Duration delay, Duration running, final EnergyStore energyStore,
            final long maxPulseCapacity) {
        this.delay = delay;
        this.running = running;
        this.energyStore = energyStore;
        this.maxPulseCapacity = maxPulseCapacity;
    }

    @Override public void run() {

        start = System.currentTimeMillis();

        while (shouldRun()) {

            pulse(calculatePulseCapacity());
            System.out.println(energyStore);
            System.out.println("----------");

            delay();
        }
    }

    public void pulse(final long energyAmount) {

        final Energy energy = energyStore.generate(energyAmount);
        final Pulse pulse = new Pulse(energy, pulseListeners);
        pulse.execute();
        energyStore.charge(pulse.getEnergy());
    }

    @Override  public void addComponent(final PulseableComponent energyComponent) {
        pulseListeners.add(new ComponentPulseListener(energyComponent));
    }

    private boolean shouldRun() {
        return System.currentTimeMillis() - start < running.toMillis();
    }

    private long calculatePulseCapacity() {
        return Math.min(maxPulseCapacity, energyStore.getCapacity());
    }

    private void delay() {
        try {
            Thread.sleep(delay.toMillis());
        }
        catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
