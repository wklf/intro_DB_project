package office;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UIBookBand extends JPanel implements ActionListener {
    private JLabel lblBandName = new JLabel("Band name:");
    private JTextField tfBandName = new JTextField();

    private JLabel lblCountry = new JLabel("Country:");
    private JTextField tfCountry = new JTextField();

    private JLabel lblBandInfo = new JLabel("Band info:");
    private JTextArea taBandInfo = new JTextArea();

    private JLabel lblBandMemeber = new JLabel("Band member name:");
    private JLabel lblBandMemberEmail = new JLabel("Band member email:");
    private JTextField tfBandMemberName = new JTextField();
    private JTextField tfBandMemberEmail = new JTextField();
    private JButton btnSaveBandMember = new JButton("Add member");

    private DefaultListModel<String> lmBandMembers = new DefaultListModel<String>();
    private JList listBandMembers = new JList(lmBandMembers);
    private ArrayList<String> memberNames = new ArrayList<>();
    private ArrayList<String> memberEmails = new ArrayList<>();

    private JPanel bottomPanel = new JPanel();
    private JButton btnCancel = new JButton("Cancel");
    private JButton btnSave = new JButton("Save");
    private OfficeUIListener uiListener;


    public UIBookBand() {
        initializeComponents();
    }

    private void initializeComponents() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(4,4,4,4);
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblBandName, gbc);

        gbc.gridy++;
        tfBandName.setPreferredSize(new Dimension(360, 20));
        this.add(tfBandName, gbc);

        gbc.gridy++;
        this.add(lblCountry, gbc);

        gbc.gridy++;
        tfCountry.setPreferredSize(new Dimension(360, 20));
        this.add(tfCountry, gbc);

        gbc.gridy++;
        this.add(lblBandInfo, gbc);

        gbc.gridy++;
        taBandInfo.setPreferredSize(new Dimension(360, 160));
        taBandInfo.isEditable();
        taBandInfo.setLineWrap(true);
        taBandInfo.setWrapStyleWord(true);
        this.add(taBandInfo, gbc);

        gbc.gridy++;
        this.add(lblBandMemeber, gbc);

        gbc.gridy++;
        gbc.gridwidth = 4;
        this.add(tfBandMemberName, gbc);

        gbc.gridy++;
        this.add(lblBandMemberEmail, gbc);

        gbc.gridy++;
        gbc.gridwidth = 3;
        this.add(tfBandMemberEmail, gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        btnSaveBandMember.addActionListener(this);
        this.add(btnSaveBandMember, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 4;
        gbc.weightx = 1;
        listBandMembers.setPreferredSize(new Dimension(360, 160));
        this.add(listBandMembers, gbc);

        bottomPanel.add(btnCancel);
        bottomPanel.add(btnSave);
        btnCancel.addActionListener(this);
        btnSave.addActionListener(this);

        gbc.gridy++;
        gbc.gridwidth = 4;
        this.add(bottomPanel, gbc);
    }
    
    protected void addUIListener(OfficeUIListener ui) {
 		this.uiListener = ui;
 	}

    private void closeFrame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSaveBandMember) {
            lmBandMembers.addElement(tfBandMemberName.getText());
            memberNames.add(tfBandMemberName.getText());
            memberEmails.add(tfBandMemberEmail.getText());
        } if (e.getSource() == btnCancel) {
            closeFrame();
        } if (e.getSource() == btnSave) {
            System.out.println("Save pressed");
        	uiListener.btnBookBand(tfBandName.getText(),
                    tfCountry.getText(),
                    taBandInfo.getText(),
                    memberNames,
        	        memberEmails);
        	 closeFrame();
        }
    }
}
