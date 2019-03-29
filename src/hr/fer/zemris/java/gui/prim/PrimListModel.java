package hr.fer.zemris.java.gui.prim;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * This class implements the {@linkplain ListModel} interface and represents
 * a list model containing prime numbers.
 * <p>
 * Any change to the contents or length of the data model will be reported to
 * all of the <tt>ListDataListeners</tt>.
 *
 * @author Mario Bobic
 */
public class PrimListModel implements ListModel<Integer> {

    /** Cached value of the last generated prime. */
    private int last = 1;
    /** List of generated prime numbers. */
    private List<Integer> primes = new ArrayList<>();
    /** List of list data listeners that will be alerted on events. */
    private List<ListDataListener> listeners = new ArrayList<>();

    /**
     * Constructs an instance of {@code PrimListModel} with a single value in
     * the list of primes: 1.
     */
    public PrimListModel() {
        primes.add(last);
    }

    /**
     * Returns true if the specified <tt>number</tt> is a prime number.
     * This method considers the first prime number to be 2.
     *
     * @param number number that is tested for prime
     * @return true if number is prime
     */
    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        // lowest value for checking for prime numbers
        int numberSqrt = (int) Math.sqrt((double) number);
        for (int i = 2; i <= numberSqrt; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds the next prime number to the list and notifies all registered
     * listener of the event.
     */
    public void next() {
        int i = last + 1;

        while (!isPrime(i)) {
            i++;
        }

        add(i);
    }

    /**
     * Adds the specified <tt>prime</tt> to the <tt>primes</tt> list, updates
     * the <tt>last</tt> generated prime number and creates a
     * {@linkplain ListDataEvent}, notifying the <tt>listeners</tt>.
     *
     * @param prime prime number to be added
     */
    private void add(int prime) {
        primes.add(prime);
        last = prime;

        int index = getSize() - 1;
        ListDataEvent event = new ListDataEvent(
            this, ListDataEvent.INTERVAL_ADDED, index, index
        );

        for (ListDataListener l : listeners) {
            l.intervalAdded(event);
        }
    }

    @Override
    public int getSize() {
        return primes.size();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= getSize()</tt>)
     */
    @Override
    public Integer getElementAt(int index) {
        return primes.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listeners = new ArrayList<>(listeners);
        listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners = new ArrayList<>(listeners);
        listeners.remove(l);
    }

}
