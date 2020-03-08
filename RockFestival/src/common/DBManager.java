fpackage common;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DBManager {

    //add db access credentials before running
    private String ip = "";
    private String dbName = "";
    private String user = "";
    private String password = ""; 
    private String port = "";
    private String url = "jdbc:postgresql://" + ip + ":" + port + "/" + dbName;

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Connection to the PostgreSQL server failed.");
        }
        return conn;
    }

    public void bookBand(String bandName, String country, String bandInfo, ArrayList<String> memberNames, ArrayList<String> memberEmails) {
        String query = "INSERT INTO band (name, country, info) VALUES (?, ?, ?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, bandName);
            pstmt.setString(2, country);
            pstmt.setString(3, bandInfo);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Book band error");
            System.out.println(ex.getMessage());
        }
        addBandMembers(memberNames, memberEmails);
        addToPlaysIn(bandName, memberEmails);
    }

    public void addBandMembers(ArrayList<String> memberNames, ArrayList<String> memberEmails) {
        String query = "INSERT INTO Member (email, name) VALUES (?, ?) ON CONFLICT DO NOTHING";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(query);

            for (int i = 0; i < memberNames.size(); i++) {
                pstmt.setString(1, memberEmails.get(i));
                pstmt.setString(2, memberNames.get(i));
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("add band member error");
            System.out.println(ex.getMessage());
        }
    }

    public void addToPlaysIn(String bandName, ArrayList<String> memberEmails) {
        String query = "INSERT INTO Plays_in VALUES (?, ?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(query);

            for (String email : memberEmails) {
                pstmt.setString(1, bandName);
                pstmt.setString(2, email);
                pstmt.addBatch();

                System.out.println("addToPlays_in loopar");
            }

            pstmt.executeBatch();
            conn.close();
        } catch (SQLException e) {
            System.out.println("add plays_in error");
            System.out.println(e.getMessage());
        }
    }

	public void addContact(String pno, String bandName) {
		String query = "UPDATE band SET contact_person = ? WHERE name = ?";
		try{Connection conn = connect();
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, pno);
		pstmt.setString(2, bandName);
		pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("add contact error");
			System.out.println(ex.getMessage());
		}
	}

	public void addConcert(Timestamp start, Timestamp stop, String stage, String band) {
		String query = "INSERT into concert VALUES(?, ?, ?, ?)";
		try{Connection conn = connect();
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setTimestamp(1, start); 
		pstmt.setTimestamp(2, stop); 
		pstmt.setString(3, stage); 
		pstmt.setString(4, band); 
		pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("add concert error");
			System.out.println(ex.getMessage());
		}
	}

	public String getContactCount(){
		String result = "";
		String query = "" + 
				"SELECT staff.pno, staff.name, sum(b.count) " + 
				"FROM staff JOIN " +
				"(select band.name, c.count, band.contact_person " +
				"FROM band join (select band_name, count(member_email) as count " +
				"from plays_in group by band_name) as c " +
				"on band.name = c.band_name) as b " +
				"on staff.pno = b.contact_person group by staff.pno";

		try{Connection conn = connect();
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();

		while(rs.next()) {
			result += rs.getString(2);
			result += " (" + rs.getString(1) + ") ";
			result += " is contact for: : " + rs.getInt(3) + " members\n";
		}
		conn.close();
		} catch (SQLException ex) {
			System.out.println("get contact responsibilities error");
			System.out.println(ex.getMessage());
		}
		return result;
	}

	public String getInCharge() {
		String result = "";
		String query = "SELECT in_charge.stage_name, in_charge.start_time,"
				+ " in_charge.stop_time, in_charge.staff_pno, staff.name FROM"
				+ " in_charge LEFT JOIN staff on in_charge.staff_pno = staff.pno"; 

		try{Connection conn = connect();
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();

		while(rs.next()) {
			result += rs.getString(1) + " ";
			result += rs.getString(2) + " ";
			result += rs.getString(5) + " (";
			result += rs.getString(4) + ") \n";
		}
		conn.close();
		} catch (SQLException ex) {
			System.out.println("get contact responsibilities error");
			System.out.println(ex.getMessage());
		}
		return result;
	}

	public ArrayList<Concert> getAllConcerts() {
		String query = "SELECT start_time, stop_time, stage_name, band_name FROM concert";
		ArrayList<Concert> resultArray = new ArrayList<Concert>();

		try{Connection conn = connect();
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Concert concert = new Concert(new Band(rs.getString(4)), rs.getTimestamp(1),
					rs.getTimestamp(2), rs.getString(3));
			resultArray.add(concert);
		}
		conn.close();
		} catch (SQLException ex) {
			System.out.println("get all concert errors");
			System.out.println(ex.getMessage());
		}

		for(Concert x:resultArray) {
			Band b = getBand(x.getBand().getName());
			x.setBand(b);
		}

		System.out.println(resultArray);
		return resultArray;
	}

	public ArrayList<Concert> getConcerts(Timestamp start, Timestamp stop) {
		String query = "SELECT start_time, stop_time, stage_name, band_name FROM concert WHERE start_time " +
				"BETWEEN ? AND ?";
		ArrayList<Concert> resultArray = new ArrayList<Concert>();

		try{Connection conn = connect();
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setTimestamp(1, start);
		pstmt.setTimestamp(2, stop);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Concert concert = new Concert(new Band(rs.getString(4)), rs.getTimestamp(1),
					rs.getTimestamp(2), rs.getString(3));
			resultArray.add(concert);
		}
		conn.close();
		} catch (SQLException ex) {
			System.out.println("get all concert errors");
			System.out.println(ex.getMessage());
		}

		for(Concert x:resultArray) {
			Band b = getBand(x.getBand().getName());
			x.setBand(b);
		}
		return resultArray;
	}

	public Band getBand(String bandName) {
		String query = "SELECT name, country, info, contact_person FROM band WHERE name = ?";
		Band band = new Band(bandName);
		try{Connection conn = connect();
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, bandName);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		band = new Band(rs.getString(1));
		band.setCountry(rs.getString(2));
		band.setInfo(rs.getString(3));
		band.setContactPerson(rs.getString(4));
		conn.close();
		} catch (SQLException ex) {
			System.out.println("get all concert errors");
			System.out.println(ex.getMessage());
		}
		band.setMembers(getMembers(bandName));
		return band;
	}

	private ArrayList<String> getMembers(String bandName) {
		ArrayList<String> resultArray = new ArrayList<String>();
		String query = "SELECT member.name FROM plays_in "
				+ "JOIN member on plays_in.member_email = member.email "
				+ "WHERE plays_in.band_name = ?";

		try{Connection conn = connect();
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, bandName);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			resultArray.add(rs.getString(1));
		}
		conn.close();
		} catch (SQLException ex) {
			System.out.println("get members error");
			System.out.println(ex.getMessage());
		}
		return resultArray;
	}
}



