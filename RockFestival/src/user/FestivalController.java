package user;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;

import common.Concert;
import common.DBManager;
import office.OfficeUIListener;
import office.UIBookBand;

public class FestivalController {

	private UICallbackImplementation uiFestivalCallback = new UICallbackImplementation();
	private DBManager dbManager = new DBManager();
	private UIFestivalInformation infoUI;

	public FestivalController(UIFestivalInformation info) {
		this.infoUI = info;
		showUI(info);
	}

	private void showUI(UIFestivalInformation info) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame();
			info.addUIListener(uiFestivalCallback);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setResizable(false);
			frame.add(info);
			frame.pack();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		});
	}

	protected Timestamp getTimestamp(String dateTime) {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = formatter.parse(dateTime);
			cal.setTime(date);
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.SECOND, 0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp time = new Timestamp(cal.getTimeInMillis());
		return time;
	}

	private class UICallbackImplementation implements FestivalUIListener {

		@Override
		public void btnThursday() {
			Timestamp start = getTimestamp("2019-06-20 11:59:00");
			Timestamp stop = getTimestamp("2019-06-21 06:00:00");
			ArrayList<Concert> list = dbManager.getConcerts(start, stop);
			infoUI.addToListModel(list);
		}

		@Override
		public void btnFriday() {
			Timestamp start = getTimestamp("2019-06-21 11:59:00");
			Timestamp stop = getTimestamp("2019-06-22 06:00:00");
			ArrayList<Concert> list = dbManager.getConcerts(start, stop);
			infoUI.addToListModel(list);
		}

		@Override
		public void btnSaturday() {
			Timestamp start = getTimestamp("2019-06-22 11:59:00");
			Timestamp stop = getTimestamp("2019-06-23 06:00:00");
			ArrayList<Concert> list = dbManager.getConcerts(start, stop);
			infoUI.addToListModel(list);
		}

		@Override
		public void btnAllDays() {
			ArrayList<Concert> list = dbManager.getAllConcerts();
			infoUI.addToListModel(list);
		}
	}
}
