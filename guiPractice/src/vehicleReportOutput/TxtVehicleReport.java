package vehicleReportOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.Date;
import java.text.DecimalFormat;
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
	
	private String cashFormatMSRP(Vehicle vehicle) {
		DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
		String msrp = "MSRP: $" + decimalFormat.format(vehicle.getMSRP());
		return msrp;
	}
	
	private String cashFormatListPrice(Vehicle vehicle) {
		BigDecimal listPriceBD = vehicle.getMSRP().multiply( new BigDecimal(1.07));
		DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
		String listPrice = "List Price: $" + decimalFormat.format(listPriceBD);
		return listPrice;
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
			sb.append(String.format(" %s %50s %n", "--Vehicle Report--", "Date: " + getFormattedDate() ));
			String make = null;
			String model = null; 
			String msrp = null;
			String listPrice = null;
			for (int year = 1997; year < 2000; year++) {
				sb.append(String.format(" %d %n", year ));
				for (int numCars = 0; numCars < vehicles.size(); numCars++) {
					make = vehicles.get(numCars).getMake();
					model = vehicles.get(numCars).getModel();
					msrp = cashFormatMSRP(vehicles.get(numCars));
					listPrice = cashFormatListPrice(vehicles.get(numCars));
					
					sb.append(String.format("%10s %-15s %-15s %-30s %-15s %n",vehicles.get(numCars).getYear(),
							make, model, msrp, listPrice));
				}
				sb.append(String.format("%n"));
			}
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
