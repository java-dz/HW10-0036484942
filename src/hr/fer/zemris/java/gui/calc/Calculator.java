package hr.fer.zemris.java.gui.calc;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import hr.fer.zemris.java.gui.calc.components.*;
import hr.fer.zemris.java.gui.calc.components.functions.*;
import hr.fer.zemris.java.gui.calc.components.methods.*;
import hr.fer.zemris.java.gui.calc.components.numbers.*;
import hr.fer.zemris.java.gui.calc.components.operations.*;
import hr.fer.zemris.java.gui.layouts.CalcLayout;

/**
 * This class is a {@linkplain JFrame} which demonstrates the usage of
 * {@linkplain Display} with bond to the {@linkplain AbstractButton} class. It
 * creates numbers, functions, operations and methods to be used within the
 * Calculator.
 * <p>
 * The frame is positioned in the middle and it takes up one third of the screen
 * width and one half of the screen height.
 *
 * @author Mario Bobic
 */
public class Calculator extends JFrame {
    /** Serialization UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs and initializes this frame with GUI components.
     */
    public Calculator() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculator");

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen.width / 3, screen.height / 2);

        initGUI();

        setLocationRelativeTo(null);
    }

    /**
     * Initializes the GUI by creating a panel with the {@linkplain CalcLayout}
     * manager and adding numbers, functions, operations and methods.
     */
    private void initGUI() {
        JPanel p = new JPanel(new CalcLayout(3));

        Display display = new Display();
        p.add(display, "1,1");

        addNumbers(p, display);
        addFunctions(p, display);
        addOperations(p, display);
        addMethods(p, display);

        getContentPane().add(p);
    }

    /**
     * Adds decimal numbers to the panel <tt>p</tt>, referencing them to the
     * specified <tt>display</tt>.
     *
     * @param p panel where the numbers will be added
     * @param display display to be referenced to the buttons
     */
    private static void addNumbers(JPanel p, Display display) {
        p.add(new NumberButton(display, 7), "2,3");
        p.add(new NumberButton(display, 8), "2,4");
        p.add(new NumberButton(display, 9), "2,5");
        p.add(new NumberButton(display, 4), "3,3");
        p.add(new NumberButton(display, 5), "3,4");
        p.add(new NumberButton(display, 6), "3,5");
        p.add(new NumberButton(display, 1), "4,3");
        p.add(new NumberButton(display, 2), "4,4");
        p.add(new NumberButton(display, 3), "4,5");
        p.add(new NumberButton(display, 0), "5,3");
    }

    /**
     * Adds functions to the panel <tt>p</tt>, referencing them to the specified
     * <tt>display</tt>.
     *
     * @param p panel where the functions will be added
     * @param display display to be referenced to the buttons
     */
    private static void addFunctions(JPanel p, Display display) {
        p.add(new FunctionButtonInverse(display), "2,1");
        p.add(new FunctionButtonLog(display), "3,1");
        p.add(new FunctionButtonLn(display), "4,1");
        p.add(new FunctionButtonSin(display), "2,2");
        p.add(new FunctionButtonCos(display), "3,2");
        p.add(new FunctionButtonTan(display), "4,2");
        p.add(new FunctionButtonCtg(display), "5,2");
        p.add(new FunctionButtonNegate(display), "5,4");
    }

    /**
     * Adds operations to the panel <tt>p</tt>, referencing them to the
     * specified <tt>display</tt>.
     *
     * @param p panel where the operations will be added
     * @param display display to be referenced to the buttons
     */
    private static void addOperations(JPanel p, Display display) {
        p.add(new OperationButtonPower(display), "5,1");
        p.add(new OperationButtonDot(display), "5,5");
        p.add(new OperationButtonEqual(display), "1,6");
        p.add(new OperationButtonDiv(display), "2,6");
        p.add(new OperationButtonMul(display), "3,6");
        p.add(new OperationButtonSub(display), "4,6");
        p.add(new OperationButtonAdd(display), "5,6");
    }

    /**
     * Adds methods to the panel <tt>p</tt>, referencing them to the specified
     * <tt>display</tt>.
     *
     * @param p panel where the methods will be added
     * @param display display to be referenced to the buttons
     */
    private static void addMethods(JPanel p, Display display) {
        p.add(new MethodButtonClr(display), "1,7");
        p.add(new MethodButtonRes(display), "2,7");
        p.add(new MethodButtonPush(display), "3,7");
        p.add(new MethodButtonPop(display), "4,7");
        p.add(new MethodButtonInv(display), "5,7");
    }

    /**
     * Program entry point.
     *
     * @param args not used in this program
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculator().setVisible(true);
        });
    }

}
