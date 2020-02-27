package storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import vehicleReportOutput.TxtVehicleReport;

public class VehicleDemo {
	public static void main(String [] args) {
		
		File file = new File("/Users/christinabannon/FileFolder/Vehicles.csv");
		ArrayList<Vehicle> vehicles = null;
		try {
			VehicleCSVInput vehicleCSVInput = new VehicleCSVInput(file);
			vehicles = vehicleCSVInput.processCSV(); 
			for (Vehicle vehicle : vehicles) {
				System.out.println("Year : " + vehicle.getYear());
				System.out.println("Make : " + vehicle.getMake());
				System.out.println("Model : " + vehicle.getModel());
				System.out.println("MSRP : " + vehicle.getMSRP());
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		String pathName = getFilePath(file);
		System.out.println(pathName);
		TxtVehicleReport txtReport = 
				new TxtVehicleReport(vehicles, pathName, "fileName.txt");
	}
	
	public static String getFilePath(File file) {

		String absolutePath = file.getAbsolutePath();
		String fileName = file.getName();
		String pathName = 
				absolutePath.substring(0, (absolutePath.length() - fileName.length()));
		
		return pathName; 
	}

}
