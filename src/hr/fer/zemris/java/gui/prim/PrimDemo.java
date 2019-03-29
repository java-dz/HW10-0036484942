package hr.fer.zemris.java.gui.prim;

import java.awt.*;
import javax.swing.*;

/**
 * This class is a {@linkplain JFrame} which demonstrates the usage of
 * {@linkplain PrimListModel}. It creates two <tt>JList</tt> objects with a
 * single <tt>model</tt>, both with the same width and height, and a button that
 * generates next primes.
 * <p>
 * The frame is positioned in the middle and it takes up one third of the screen
 * width and one half of the screen height.
 *
 * @author Mario Bobic
 */
public class PrimDemo extends JFrame {
    /** Serialization UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs and initializes this frame with GUI components.
     */
    public PrimDemo() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("PrimDemo");

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen.width / 3, screen.height / 2);

        initGUI();

        setLocationRelativeTo(null);
    }

    /**
     * Initializes the GUI by adding the <tt>BorderLayout</tt> manager to the
     * content pane and adds two identical JLists to it.
     */
    private void initGUI() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        PrimListModel model = new PrimListModel();
        JList<Integer> list1 = new JList<>(model);
        JList<Integer> list2 = new JList<>(model);

        JButton nextBtn = new JButton("Next");
        nextBtn.addActionListener((e) -> {
            model.next();
        });

        JPanel center = new JPanel(new GridLayout(1, 0));
        JPanel bottom = new JPanel(new BorderLayout());

        center.add(new JScrollPane(list1));
        center.add(new JScrollPane(list2));

        bottom.add(nextBtn, BorderLayout.CENTER);

        cp.add(center, BorderLayout.CENTER);
        cp.add(bottom, BorderLayout.PAGE_END);
    }

    /**
     * Program entry point.
     *
     * @param args not used in this program
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PrimDemo().setVisible(true);
        });
    }
}
