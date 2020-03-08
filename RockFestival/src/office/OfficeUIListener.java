package office;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface OfficeUIListener {
	
	public void btnStartUI(String txt);
	
	public void btnAddConcert(Timestamp start, Timestamp stop, String stage, String band);
	
	public void btnAddContact(String pno, String bandName);
	
	public void btnBookBand(String bandName, String country, String bandInfo, ArrayList<String> memberNames, ArrayList<String> memberEmails);
	
	public void btnDisplayInCharge();
	
	public void btnDisplayContacts();

}
