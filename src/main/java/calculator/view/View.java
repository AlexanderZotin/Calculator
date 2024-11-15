package calculator.view;

import javax.swing.JButton;

import lombok.NonNull;

public interface View {
    void setMainDisplayText(String text);
    void setSecondDisplayText(String text);
    void setVisible(boolean visible);
    void subscribeToListener(@NonNull Listener listener);
    
    String getMainDisplayText();
    String getSecondDisplayText();
    JButton getGoButton();
    JButton getArrowButton();
    JButton getCleanButton();
    JButton getSquareRootButton();
    JButton getPointButton();
}
