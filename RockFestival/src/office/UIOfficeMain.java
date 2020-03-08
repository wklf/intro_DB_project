package office;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIOfficeMain extends JPanel implements ActionListener {
	private JButton btnBookBand = new JButton("Book band");
	private JButton btnAssignContact = new JButton("Assign contact");
	private JButton btnScheduleConcert = new JButton("Schedule concert");
	private JButton btnListSecurity = new JButton("List security personnel");
	private JButton btnStaffOverview = new JButton("Staff overview");
	private OfficeUIListener uiListener;

	public UIOfficeMain() {
		initializeComponents();
	}

	private void initializeComponents() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(4,4,4,4);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		btnBookBand.addActionListener(this);
		this.add(btnBookBand, gbc);

		gbc.gridy++;
		btnAssignContact.addActionListener(this);
		this.add(btnAssignContact, gbc);

		gbc.gridy++;
		btnScheduleConcert.addActionListener(this);
		this.add(btnScheduleConcert, gbc);

		gbc.gridy++;
		btnListSecurity.addActionListener(this);
		this.add(btnListSecurity, gbc);

		gbc.gridy++;
		btnStaffOverview.addActionListener(this);
		this.add(btnStaffOverview, gbc);
	}

	protected void addUIListener(OfficeUIListener ui) {
		this.uiListener = ui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBookBand) {
			uiListener.btnStartUI("startBookBandUI");
		} if (e.getSource() == btnAssignContact) {
			uiListener.btnStartUI("startAddContactUI");
		} if (e.getSource() == btnScheduleConcert) {
			uiListener.btnStartUI("startAddConcertUI");
		} if (e.getSource() == btnListSecurity) {
			uiListener.btnDisplayInCharge();
		} if (e.getSource() == btnStaffOverview) {
			uiListener.btnDisplayContacts();
		}
	}
}
