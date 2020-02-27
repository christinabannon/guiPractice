package vehicleReportOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Date;
import java.text.SimpleDateFormat;

import storage.Vehicle;



/*
 * 
 * 
 * 
 */
public class TxtVehicleReport {
	
	ArrayList <Vehicle> vehicles = new ArrayList<>();  
	File txtReport = null;
	FileWriter fileWriter = null;
	BufferedWriter bufferedWriter = null; 
	
	public TxtVehicleReport(ArrayList<Vehicle> vehicles, String pathName, String fileName) {
		txtReport = new File(pathName, fileName);
		this.vehicles = vehicles;
		
		try {
			fileWriter = new FileWriter(txtReport);
//			bufferedWriter = new BufferedWriter();
		} catch (IOException E) {
			E.printStackTrace();
		}
		
		writeReport();
	}
	
	private String getFormattedDate() {
		Date date = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter.format(date);
		
	}
	// methods for what to write to file
	private void writeReport() {
		
		StringBuilder sb = new StringBuilder(); 
		try {
			/*
			for (Vehicle vehicle : vehicles) {
				stringBuilder.append
			}
			*/
			
			// maybe v2 figure out how to determine max possible line length
			// to align right
			sb.append(String.format("%-15s %50s", "--Vehicle Report--", "Date: " + getFormattedDate() ));
//		    sb.append(String.format("%s %50s%n", "A", "Monthly Report"));
//		    sb.append(String.format("%s %48s%n", "A", "Report Name"));
//		    sb.append(String.format("%s %n", "A"));
//		    sb.append(String.format("%s %-20s %-20s %-20s%n", "A", "Category", "Quantity", "Price"));
//		    sb.append(String.format("%s %-20s %-20s %-20s%n", "A", 
//		    		"--------------", "--------------", "--------------"));
//		    sb.append(String.format("%s %-20s %-20s %-20s%n", "B", "Paper", 100, "$200"));
		    fileWriter.write(sb.toString());
		    
			fileWriter.close();
		} catch (Exception E) {
			E.printStackTrace();
		}
		
	}

}
