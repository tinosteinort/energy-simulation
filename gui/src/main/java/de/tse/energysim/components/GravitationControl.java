package de.tse.energysim.components;

import de.tse.energysim.EnergySpecification;
import de.tse.energysim.energy.EnergyRequestable;
import de.tse.energysim.energystore.EnergyStore;
import de.tse.energysim.energystore.SecureEnergyStore;
import de.tse.energysim.energystore.storebuffer.FastStoreBuffer;
import de.tse.energysim.energystore.storebuffer.StoreBuffer;
import de.tse.energysim.pulse.PulseControl;
import de.tse.energysim.pulse.PulseableComponent;

public class GravitationControl implements PulseableComponent {

    private final EnergySpecification specification;
    private final EnergyStore store;
    private final StoreBuffer storeBuffer;

    public GravitationControl(final EnergySpecification specification) {
        this.specification = specification;
        this.store = new SecureEnergyStore(specification.getStoreCapacity());
        this.storeBuffer = new FastStoreBuffer(store);
    }

    @Override public void onPulse(final EnergyRequestable source, final PulseControl pulseControl) {

        storeBuffer.request(source, specification.getRequestingEnergyAmount(), energy -> {

            System.out.println("Gravitation Control works with " + energy + " internal storage: " + store);
        });

        pulseControl.continuePulse();
    }
}
