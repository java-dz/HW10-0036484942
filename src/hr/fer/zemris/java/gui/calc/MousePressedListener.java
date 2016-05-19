package hr.fer.zemris.java.gui.calc;

import java.awt.event.MouseEvent;

/**
 * This interface is a functional interface that extends the
 * {@linkplain java.awt.event.MouseListener}, setting all methods to default,
 * except the {@link #mousePressed(MouseEvent)} method.
 *
 * @author Mario Bobic
 */
@FunctionalInterface
public interface MousePressedListener extends java.awt.event.MouseListener {

	/**
	 * {@inheritDoc}
	 * <p>
	 * Does nothing.
	 */
	@Override
	default void mouseClicked(MouseEvent e) {
	}

	@Override
	void mousePressed(MouseEvent e);

	/**
	 * {@inheritDoc}
	 * <p>
	 * Does nothing.
	 */
	@Override
	default void mouseReleased(MouseEvent e) {
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Does nothing.
	 */
	@Override
	default void mouseEntered(MouseEvent e) {
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Does nothing.
	 */
	@Override
	default void mouseExited(MouseEvent e) {
	}

}
