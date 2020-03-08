package common;

import javax.swing.*;

public class Start {
    private void showUI(UICommon uiCommon) {
        SwingUtilities.invokeLater(() -> {
            JFrame frameMain = new JFrame();
            frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameMain.setResizable(false);
            frameMain.add(uiCommon);
            frameMain.pack();
            frameMain.setVisible(true);
            frameMain.setLocationRelativeTo(null);
        });
    }

    public static void main(String[] args) {
        Start start = new Start();
        CommonController commonController = new CommonController();
        start.showUI(new UICommon(commonController));
    }
}
