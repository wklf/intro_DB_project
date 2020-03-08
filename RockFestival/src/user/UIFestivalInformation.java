package user;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import common.Band;
import common.Concert;

public class UIFestivalInformation extends JPanel {

	private ButtonListener buttonListener = new ButtonListener();
	private FestivalUIListener uiListener;
	private ButtonListener btnListener = new ButtonListener();
	private JPanel pnlInfo = new JPanel();
	private JPanel pnlSchedule = new JPanel();
	private Dimension dim = new Dimension(400, 500);

	// Schedule components
	private JLabel lblTitleSchedule = new JLabel("Schedule");
	private JButton btnThursday = new JButton("Thurs");
	private JButton btnFriday = new JButton("Fri");
	private JButton btnSaturday = new JButton("Sat");
	private JButton btnAllDays = new JButton("Week");
	private DefaultListModel<Concert> listModelSchedule = new DefaultListModel<Concert>();
	private JList<Concert> listSchedule = new JList<Concert>(listModelSchedule);

	// Information components 
	private JLabel lblTitleInfo = new JLabel("Information");
	private JTextArea areaInfo = new JTextArea();

	public UIFestivalInformation() {
		setup();
	}

	private void setup() {
		GridBagConstraints gbc = new GridBagConstraints();
		this.setBackground(Color.WHITE);		
		this.setLayout(new GridBagLayout());
		gbc.insets = new Insets(4,4,4,4);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(pnlSchedule, gbc);

		gbc.gridx++;
		this.add(pnlInfo, gbc);

		setupSchedule();
		setupInfo();

		// Listener for JList containing concerts
		listSchedule.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent l) {
				if(!l.getValueIsAdjusting()) {
					Concert c = (Concert) listSchedule.getSelectedValue();
					if(c != null) {
						String txt = "(" +
								c.getBand().getCountry() + ")\n" +
								c.getBand().getInfo() + "\n\nMembers:\n";
						for(String s:c.getBand().getMembers()) {
							txt += s + "\n";
						}
						areaInfo.setText(txt);
					}
				}
			}
		});
	}

	private void setupSchedule() {
		pnlSchedule.setBackground(Color.BLACK);
		pnlSchedule.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		btnThursday.addActionListener(btnListener);
		btnFriday.addActionListener(btnListener);
		btnSaturday.addActionListener(btnListener);
		btnAllDays.addActionListener(btnListener);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(4,4,4,4);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.weighty = 0.15;
		lblTitleSchedule.setForeground(Color.YELLOW);
		pnlSchedule.add(lblTitleSchedule, gbc);

		gbc.weightx = 0.25;
		gbc.gridy++;
		gbc.gridwidth = 1;
		pnlSchedule.add(btnThursday, gbc);
		gbc.gridx++;
		gbc.gridwidth = 1;
		pnlSchedule.add(btnFriday, gbc);
		gbc.gridx++;
		gbc.gridwidth = 1;
		pnlSchedule.add(btnSaturday, gbc);
		gbc.gridx++;
		gbc.gridwidth = 1;
		pnlSchedule.add(btnAllDays, gbc);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.weighty = 0.7;
		pnlSchedule.add(listSchedule, gbc);
		listSchedule.setPreferredSize(dim);
		listSchedule.setBackground(Color.BLACK);
		listSchedule.setForeground(Color.PINK);
	}

	private void setupInfo() {
		pnlInfo.setBackground(Color.BLACK);
		pnlInfo.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(4,4,4,4);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.3;
		gbc.gridwidth = 3;
		lblTitleInfo.setForeground(Color.RED);
		pnlInfo.add(lblTitleInfo, gbc);

		gbc.gridx--;
		gbc.gridy++;
		gbc.gridwidth = 3;
		gbc.weighty = 0.7;
		pnlInfo.add(areaInfo, gbc);
		areaInfo.setPreferredSize(dim);
		areaInfo.setBackground(Color.BLACK);
		areaInfo.setForeground(Color.green);
		areaInfo.setLineWrap(true);
		areaInfo.setWrapStyleWord(true);
	}

	protected void addUIListener(FestivalUIListener ui) {
		this.uiListener = ui;
	}

	protected void addToListModel(ArrayList<Concert> inputArray) {
		listModelSchedule.clear();
		for(Concert i:inputArray) {
			listModelSchedule.addElement(i);
		}
	}

	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnThursday) {uiListener.btnThursday();}		
			if(e.getSource() == btnFriday) {uiListener.btnFriday();}
			if(e.getSource() == btnSaturday) {uiListener.btnSaturday();}
			if(e.getSource() == btnAllDays) {uiListener.btnAllDays();}
		}
	}	
}
