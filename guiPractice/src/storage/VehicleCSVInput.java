package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.math.BigDecimal;
import java.util.ArrayList;

/*
 * accept a file
 * save the lines as Vehicles
 * --- sort some how by 2 fields
 * return the Vehicles[]
 * 
 *  
 */

public class VehicleCSVInput {
	BufferedReader bufferedReader = null;

	public VehicleCSVInput(File csvFile) throws FileNotFoundException {
		bufferedReader = new BufferedReader(new FileReader(csvFile));
	}

	public ArrayList<Vehicle> processCSV() throws IOException {
		String currentLine;
		ArrayList<Vehicle> vehicles = new ArrayList<>(); 
		while ((currentLine = bufferedReader.readLine()) != null) {
			Vehicle vehicle = stringToVehicle(currentLine);
			if ( vehicle != null ) {
				vehicles.add(vehicle);
			}
		}	
		if (bufferedReader != null) {
			bufferedReader.close();
		}
		return vehicles; 
	}


	private Vehicle stringToVehicle(String line) {
		String [] csvLine = line.split(",");
		Vehicle vehicle = null; 
		try {
			// this will need to change with the gui
			if (csvLine.length == 4) {
			int year = Integer.parseInt(csvLine[0]);
			String make = csvLine[1];
			String model = csvLine[2];
			BigDecimal msrp = new BigDecimal(csvLine[3]);
			vehicle = new Vehicle(year, make, model, msrp);
			}
		} catch (NumberFormatException e) {
			vehicle = null; 
		}
		return vehicle;		
	}
}
