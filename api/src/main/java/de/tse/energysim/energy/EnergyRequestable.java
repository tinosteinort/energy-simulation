package de.tse.energysim.energy;

public interface EnergyRequestable {

    void request(long amount, EnergyCallable code);
}
