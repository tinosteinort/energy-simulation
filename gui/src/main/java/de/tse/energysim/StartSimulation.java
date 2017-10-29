package de.tse.energysim;

import de.tse.energysim.components.Computer;
import de.tse.energysim.components.Engine;
import de.tse.energysim.components.GravitationControl;
import de.tse.energysim.components.LightBulb;
import de.tse.energysim.energy.EnergyFactory;
import de.tse.energysim.energystore.EnergyStore;
import de.tse.energysim.energystore.SecureEnergyStore;

import java.time.Duration;

public class StartSimulation {

    public static void main(String[] args) {

        new StartSimulation().run();
    }

    public void run() {

        final EnergyStore energyStore = new SecureEnergyStore(3000);
        energyStore.charge(EnergyFactory.create(3000));

        final SpaceShip ship = new SpaceShip(
                Duration.ofMillis(250),
                Duration.ofSeconds(5),
                energyStore, 300);


        ship.addComponent(new LightBulb(new EnergySpecification(20, 5)));
        ship.addComponent(new Computer(new EnergySpecification(50, 50)));
        ship.addComponent(new GravitationControl(new EnergySpecification(100, 100)));
        ship.addComponent(new Engine(new EnergySpecification(100, 100)));

        ship.run();
    }
}
