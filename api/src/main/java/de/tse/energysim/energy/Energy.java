package de.tse.energysim.energy;

public class Energy {

    private long amount;

    private Energy(final long amount) {
        this.amount = amount;
    }

    static Energy of(final long amount) {
        return new Energy(amount);
    }

    public static Energy empty() {
        return Energy.of(0);
    }

    public Energy include(final Energy other) {
        if (other != this) {
            this.amount += other.amount;
            other.amount = 0;
        }
        return this;
    }

    public Energy detach(final long amount) {
        if (this.amount - amount < 0) {
            throw new IllegalStateException("Not enough Energy. Try to access " + amount + " from " + this);
        }
        this.amount -= amount;
        return of(amount);
    }

    public boolean isEmpty() {
        return amount == 0;
    }

    public long getAmount() {
        return amount;
    }

    @Override public String toString() {
        return "[" + amount +"~]";
    }
}
