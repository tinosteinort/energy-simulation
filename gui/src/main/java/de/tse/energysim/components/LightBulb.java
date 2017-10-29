package de.tse.energysim.components;

import de.tse.energysim.EnergySpecification;
import de.tse.energysim.energy.EnergyRequestable;
import de.tse.energysim.energystore.EnergyStore;
import de.tse.energysim.energystore.SecureEnergyStore;
import de.tse.energysim.energystore.storebuffer.CollectingStoreBuffer;
import de.tse.energysim.energystore.storebuffer.StoreBuffer;
import de.tse.energysim.pulse.PulseControl;
import de.tse.energysim.pulse.PulseableComponent;

public class LightBulb implements PulseableComponent {

    private final EnergySpecification specification;
    private final EnergyStore store;
    private final StoreBuffer storeBuffer;

    public LightBulb(final EnergySpecification specification) {
        this.specification = specification;
        this.store = new SecureEnergyStore(specification.getStoreCapacity());
        this.storeBuffer = new CollectingStoreBuffer(store);
    }

    @Override public void onPulse(final EnergyRequestable source, final PulseControl pulseControl) {

        storeBuffer.request(source, specification.getRequestingEnergyAmount(), energy -> {

            System.out.println("Lightbulb glow with " + energy + " internal storage: " + store);
        });

        pulseControl.continuePulse();
    }
}
