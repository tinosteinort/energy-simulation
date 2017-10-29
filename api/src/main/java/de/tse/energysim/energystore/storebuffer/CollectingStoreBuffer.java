package de.tse.energysim.energystore.storebuffer;

import de.tse.energysim.energy.EnergyCallable;
import de.tse.energysim.energy.Energy;
import de.tse.energysim.energy.EnergyRequestable;
import de.tse.energysim.energystore.EnergyStore;

/**
 * First fill the EnergyStore completely, than request Energy from EnergyStore and execute Code
 */
public class CollectingStoreBuffer implements StoreBuffer {

    private final EnergyStore energyStore;

    public CollectingStoreBuffer(final EnergyStore energyStore) {
        this.energyStore = energyStore;
    }

    @Override public void request(final EnergyRequestable energyRequestable, final long amount, final EnergyCallable code) {
        if (energyStore.isFull()) {
            generateAndExecute(amount, code);
        }
        else {
            energyRequestable.request(amount, energy -> {
                final boolean noNewEnergy = energy.isEmpty();
                energyStore.charge(energy);
                if (energyStore.isFull() || noNewEnergy) {
                    generateAndExecute(amount, code);
                }
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
