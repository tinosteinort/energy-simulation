package de.tse.energysim.pulse;

import de.tse.energysim.energy.Energy;

public class ComponentPulseListener implements PulseListener {

    private final PulseableComponent energyComponent;

    public ComponentPulseListener(final PulseableComponent energyComponent) {
        this.energyComponent = energyComponent;
    }

    public void onPulse(final Pulse pulse, final PulseControl pulseControl) {

        energyComponent.onPulse((amount, code) -> {
            final long guaranteedAmount = calculateGuaranteedAmount(pulse.getEnergy(), amount);
            final Energy energy = pulse.getEnergy().detach(guaranteedAmount);
            code.call(energy);
        }, pulseControl);
    }

    private long calculateGuaranteedAmount(final Energy energy, final long requestedAmount) {
        if (requestedAmount > energy.getAmount()) {
            return energy.getAmount();
        }
        return requestedAmount;
    }
}
