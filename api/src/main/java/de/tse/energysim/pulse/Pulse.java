package de.tse.energysim.pulse;

import de.tse.energysim.energy.Energy;

import java.util.Iterator;
import java.util.List;

public class Pulse {

    private final Energy energy;
    private final Iterator<PulseListener> listeners;

    public Pulse(final Energy energy, final List<PulseListener> listeners) {
        this.energy = energy;
        this.listeners = listeners.iterator();
    }

    public void execute() {
        if (listeners.hasNext()) {
            final PulseListener listener = listeners.next();
            listener.onPulse(this, this::execute);
        }
    }

    public Energy getEnergy() {
        return energy;
    }
}
