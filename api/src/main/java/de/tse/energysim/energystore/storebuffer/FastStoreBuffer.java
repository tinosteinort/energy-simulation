package de.tse.energysim.energystore.storebuffer;

import de.tse.energysim.energy.EnergyCallable;
import de.tse.energysim.energy.Energy;
import de.tse.energysim.energy.EnergyRequestable;
import de.tse.energysim.energystore.EnergyStore;

/**
 * Executes the code, immediately if enough Energy is in the EnergyStore.
 * If there is enough Energy in the Store, no Energy from the Pulse is requested
 */
public class FastStoreBuffer implements StoreBuffer {

    private final EnergyStore energyStore;

    public FastStoreBuffer(final EnergyStore energyStore) {
        this.energyStore = energyStore;
    }

    @Override  public void request(final EnergyRequestable energyRequestable, final long amount, final EnergyCallable code) {
        if (energyStore.getCapacity() >= amount) {
            generateAndExecute(amount, code);
        }
        else {
            energyRequestable.request(amount, energy -> {
                energyStore.charge(energy);
                generateAndExecute(amount, code);
            });
        }
    }

    private void generateAndExecute(final long amount, final EnergyCallable code) {
        final Energy energy = energyStore.generate(amount);
        if (energy.getAmount() == amount) {
            code.call(energy);
        }
    }
}
