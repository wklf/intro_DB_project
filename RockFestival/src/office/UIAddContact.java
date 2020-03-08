package office;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIAddContact extends JPanel{

	private JTextField tfPNumber = new JTextField("Social security number of contact");
	private JTextField tfBandName = new JTextField("Band name");
	private JButton btnCancel = new JButton("Cancel");
    private JButton btnSave = new JButton("Save");
    private ButtonListener btnListener = new ButtonListener(); 
    private OfficeUIListener uiListener;
    
    public UIAddContact() {
    	this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(4,4,4,4);
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
	    add(tfPNumber, gbc);
	    
	    gbc.gridy++;
	    add(tfBandName, gbc);
	    
	    gbc.gridy++;
	    gbc.gridwidth = 1;
	    add(btnSave, gbc);
	    
	    gbc.gridx++;
	    add(btnCancel, gbc);
	    
	    btnSave.addActionListener(btnListener);
	    btnCancel.addActionListener(btnListener);
    }

    protected void addUIListener(OfficeUIListener ui) {
		this.uiListener = ui;
	}

    private void closeFrame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    private class ButtonListener implements ActionListener{

        @Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnSave) {
				System.out.println("Saved pressed");
				uiListener.btnAddContact(tfPNumber.getText().toString(), tfBandName.getText().toString());
				closeFrame();
				}		
			if(e.getSource() == btnCancel) {
				closeFrame();
				}
		}
	}	
}
