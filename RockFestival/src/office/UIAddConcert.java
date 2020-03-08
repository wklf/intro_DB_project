package office;

import javax.swing.*;
import javax.swing.text.DateFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class UIAddConcert extends JPanel{


	private JTextField tfBandName = new JTextField("Band name");
	private JTextField tfStageName = new JTextField("Stage name");
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnSave = new JButton("Save");
	private ButtonListener btnListener = new ButtonListener(); 
	private OfficeUIListener uiListener;

	private SpinnerDateModel modelStart = new SpinnerDateModel();
	private SpinnerDateModel modelStop = new SpinnerDateModel();
	private JSpinner spinnerStartTime = new JSpinner(modelStart);
	private JSpinner spinnerStopTime = new JSpinner(modelStop);

	private void setupSpinners() {
		Calendar calStart = Calendar.getInstance();
		calStart.set(2019, 05, 20, 12, 00, 00);

		JSpinner.DateEditor dateEditorStart = new JSpinner.DateEditor(spinnerStartTime, "yyyy:MM:dd:HH:mm");
		JSpinner.DateEditor dateEditorStop = new JSpinner.DateEditor(spinnerStopTime, "yyyy:MM:dd:HH:mm");

		spinnerStartTime.setEditor(dateEditorStart);
		spinnerStopTime.setEditor(dateEditorStop);
		spinnerStartTime.setValue(calStart.getTime());
		spinnerStopTime.setValue(calStart.getTime());
	}


	public UIAddConcert() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(4,4,4,4);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		add(tfBandName, gbc);

		gbc.gridy++;
		add(tfStageName, gbc);

		gbc.gridy++;
		gbc.gridwidth = 1;
		add(spinnerStartTime, gbc);

		gbc.gridx++;
		add(spinnerStopTime, gbc);

		gbc.gridy++;
		gbc.gridx = 0;
		add(btnSave, gbc);

		gbc.gridx++;
		add(btnCancel, gbc);

		btnSave.addActionListener(btnListener);
		btnCancel.addActionListener(btnListener);

		setupSpinners();
	}

	protected void addUIListener(OfficeUIListener ui) {
		this.uiListener = ui;
	}

	private void closeFrame() {
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		frame.dispose();
	}

	protected Timestamp getTimestamp(JSpinner spinner) {
		Timestamp time;
		Calendar cal = Calendar.getInstance();
		Date date = (Date) spinner.getValue();
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		time = new Timestamp(cal.getTimeInMillis());
		return time;
	}

	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnSave) {
				uiListener.btnAddConcert(getTimestamp(spinnerStartTime), getTimestamp(spinnerStopTime),
						tfStageName.getText(), tfBandName.getText());
				closeFrame();
			}		
			if(e.getSource() == btnCancel) {
				closeFrame();
			}
		}
	}	
}
