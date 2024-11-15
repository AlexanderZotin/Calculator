package calculator.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lombok.Getter;
import lombok.NonNull;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

public class Window extends JFrame implements View {
    private JPanel centralPanel;
    private JPanel panelForActionButtons;
    private JPanel panelForLabels;
    private final JLabel mainDisplay = new JLabel("0");
    private final JLabel secondDisplay = new JLabel();
    private final @Getter JButton goButton = new JButton("=");
    private JButton[][] buttons;

    public Window() {
        setTitle("Калькулятор. ©А.А Зотин, 2024");
        JPanel windowContent = new JPanel(new BorderLayout());
        centralPanel = new JPanel(new GridLayout(5, 3));
        panelForActionButtons = new JPanel(new GridLayout(5, 1));
        panelForLabels = new JPanel(new BorderLayout());

        createButtons();
        createDisplays();

        windowContent.add("North", panelForLabels);
        windowContent.add("Center", centralPanel);
        windowContent.add("East", panelForActionButtons);
        windowContent.add("South", goButton);

        setContentPane(windowContent);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    private void createButtons() {
        String[][] items = {
                {"←", "CE", ("√")},
                {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "00", "."},
                {"^", "+", "-", "*", "/"}
        };
        buttons = new JButton[3][];
        Dimension sizeOfButtons = new Dimension(80, 50);
        for(int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton[items[i].length];
            for(int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton(items[i][j]);
                buttons[i][j].setPreferredSize(sizeOfButtons);
                if(i == 2) panelForActionButtons.add(buttons[i][j]);
                else centralPanel.add(buttons[i][j]);
            }
        }
    }

    private void createDisplays() {
        mainDisplay.setFont(new Font(null, Font.PLAIN, 30));
        secondDisplay.setFont(new Font(null, Font.PLAIN, 15));
        secondDisplay.setBackground(new Color(35, 35, 35));
        panelForLabels.add("Center", mainDisplay);
        panelForLabels.add("North", secondDisplay);
    }

    @Override
    public void setMainDisplayText(String text) {
        mainDisplay.setText(text);
    }

    @Override
    public String getMainDisplayText() {
        return mainDisplay.getText();
    }

    @Override
    public void setSecondDisplayText(String text) {
        secondDisplay.setText(text);
    }

    @Override
    public String getSecondDisplayText() {
        return secondDisplay.getText();
    }

    @Override
    public void subscribeToListener(@NonNull Listener listener) {
        goButton.addActionListener(listener);
        for (JButton[] currentArray : buttons) {
            for (JButton current : currentArray) {
                current.addActionListener(listener);
            }
        }
    }
    
    public JButton getArrowButton() {
        return buttons[0][0];
    }

    public JButton getCleanButton() {
        return buttons[0][1];
    }

    public JButton getSquareRootButton() {
        return buttons[0][2];
    }

    public JButton getPointButton() {
        return buttons[1][11];
    }
    
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(true);
        if(visible) setLocationRelativeTo(null);
    }
}
