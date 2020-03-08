package common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UICommon extends JPanel implements ActionListener {
    private CommonController controller;
    private JButton btnPublic = new JButton("Public");
    private JButton btnOffice = new JButton("Office");

    public UICommon(CommonController controller) {
        this.controller = controller;
        initializeComponents();
    }

    private void initializeComponents() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = (new Insets(4, 4, 4, 4));

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(btnPublic, gbc);

        gbc.gridy++;
        this.add(btnOffice, gbc);
        btnPublic.addActionListener(this);
        btnOffice.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPublic) {
            controller.startPublic();
        } if (e.getSource() == btnOffice) {
            controller.startOffice();
        }
    }
}
