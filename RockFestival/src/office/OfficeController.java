package office;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.*;

import common.DBManager;

public class OfficeController {
	
	private DBManager dbManager = new DBManager();
	private UICallbackImplementation uiOfficeCallback = new UICallbackImplementation();

	public OfficeController() {
		UIOfficeMain ui = new UIOfficeMain();
		showUIOfficeMain(ui);
		ui.addUIListener(uiOfficeCallback);
	}

	private class UICallbackImplementation implements OfficeUIListener {
		@Override
		public void btnStartUI(String txt) {
			if(txt == "startAddConcertUI") {
				showUIAddConcert(new UIAddConcert());
			}
			if(txt == "startAddContactUI") {
				showUIAddContact(new UIAddContact());
			}
			if(txt == "startBookBandUI") {
				showUIBookBand(new UIBookBand());
			}
		}

		@Override
		public void btnAddConcert(Timestamp start, Timestamp stop, String stage, String band) {
			addConcert(start,  stop,  stage,  band);
		}

		@Override
		public void btnAddContact(String pno, String bandName) {
			addContact(pno, bandName);
		}

		@Override
		public void btnBookBand(String bandName, String country, String bandInfo, ArrayList<String> bandMembers, ArrayList<String> bandMemberEmails) {
			bookBand(bandName, country, bandInfo, bandMembers, bandMemberEmails);
		}

		@Override
		public void btnDisplayInCharge() {
			displayInCharge();

		}

		@Override
		public void btnDisplayContacts() {
			displayContacts();
		}
	}

	private void showUIOfficeMain(UIOfficeMain uiOfficeMain) {
		SwingUtilities.invokeLater(() -> {
			JFrame officeFrame = new JFrame();
			officeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			officeFrame.setResizable(false);
			officeFrame.add(uiOfficeMain);
			officeFrame.pack();
			officeFrame.setVisible(true);
			officeFrame.setLocationRelativeTo(null);
		});
	}

	protected void showUIBookBand(UIBookBand uiBookBand) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame();
			uiBookBand.addUIListener(uiOfficeCallback);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setResizable(false);
			frame.add(uiBookBand);
			frame.pack();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		});
	}

	protected void showUIAddConcert(UIAddConcert uiAddConcert) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame();
			uiAddConcert.addUIListener(uiOfficeCallback);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setResizable(false);
			frame.add(uiAddConcert);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}

	protected void showUIAddContact(UIAddContact uiAddContact) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame();
			uiAddContact.addUIListener(uiOfficeCallback);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setResizable(false);
			frame.add(uiAddContact);
			frame.pack();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		});
	}

	protected void displayInCharge() {
		String txt = dbManager.getInCharge();
		JOptionPane.showMessageDialog(null, txt);
	}

	protected void displayContacts() {
		String txt = dbManager.getContactCount();
		JOptionPane.showMessageDialog(null, txt);
	}

	protected void addContact(String pno, String bandName) {
		dbManager.addContact(pno, bandName);
	}

	protected void addConcert(Timestamp start, Timestamp stop, String stage, String band) {
		dbManager.addConcert(start, stop, stage, band);
	}

	protected void bookBand(String bandName, String country, String bandInfo, ArrayList<String> memberNames, ArrayList<String> memberEmails) {
 	    dbManager.bookBand(bandName, country, bandInfo, memberNames, memberEmails);
    }
}
