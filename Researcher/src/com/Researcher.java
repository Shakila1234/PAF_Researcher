package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Researcher {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/project?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertResearcher(String rName, String pName, String rDate, String rDes) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into research(`rId`, `rName`, `pName`, `rDate`,`rDes`)" + " values ( ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, rName);
			preparedStmt.setString(3, pName);
			preparedStmt.setString(4, rDate);
			preparedStmt.setString(5, rDes);

			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newResearchers = readResearcher();
			output = "{\"status\":\"success\", \"data\": \"" +
					newResearchers + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the researcher.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readResearcher() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Research Name</th>"
					+"<th>Product Name</th><th>Date</th>"
					+ "<th>Description</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from research";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String rId = Integer.toString(rs.getInt("rId"));
				String rName = rs.getString("rName");
				String pName = rs.getString("pName");
				String rDate = rs.getString("rDate");
				String rDes = rs.getString("rDes");

				output += "<tr><td><input id='hidResearcherIDUpdate' name='hidResearcherIDUpdate' type='hidden' value='" + rId + "'>"
						+ rName + "</td>";
				output += "<td>" + pName + "</td>";
				output += "<td>" + rDate + "</td>";
				output += "<td>" + rDes + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-researcherid='" + rId + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-researcherid='" + rId + "'>" +"</td></tr>";

			}
			con.close();
			
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Researchers.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateResearcher(String rId, String rName, String pName, String rDate, String rDes) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE research SET rName=?,pName=?,rDate=?,rDes=?" + "WHERE rId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, rName);
			preparedStmt.setString(2, pName);
			preparedStmt.setString(3, rDate);
			preparedStmt.setString(4, rDes);
			preparedStmt.setInt(5, Integer.parseInt(rId));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newResearchers = readResearcher();
			output = "{\"status\":\"success\", \"data\": \"" +
					newResearchers + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the researcher.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteResearcher(String rId) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from research where rId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(rId));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newResearchers = readResearcher();
			output = "{\"status\":\"success\", \"data\": \"" +
					newResearchers + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the researcher.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

}