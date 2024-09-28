package calculator;

import calculator.model.CalculatorModel;
import calculator.model.Engine;
import calculator.view.Listener;
import calculator.view.Window;
import calculator.controller.CalculatorController;
import calculator.controller.Controller;

import javax.swing.SwingUtilities;

public class Main {
    private static CalculatorController controller;

    public static void main(String [] args) {
        Window view = new Window();
        view.subscribeToListener(new Listener(view));
        CalculatorModel model = new Engine();
        controller = new Controller(view, model);
		SwingUtilities.invokeLater(() -> view.setVisible(true));
    }

    public static CalculatorController getController() {
        return controller;
    }
}
