package other;

/**
 * A counter is in charge of counting.
 */
public class Counter {
    private int value;

    /**
     * Constructor.
     */
    public Counter() {
        this.value = 0;
    }

    /**
     * add number to current count.
     * @param number a number
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * subtract number from current count.
     * @param number a number
     */
    public void decrease(int number) {
        this.value -= number;
    }


    /**
     * @return current value.
     */
    public int getValue() {
        return this.value;
    }
}
