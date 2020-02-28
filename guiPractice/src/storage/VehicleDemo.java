package storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import sorter.SortVehicles;
import vehicleReportOutput.TxtVehicleReport;

public class VehicleDemo {
	public static void main(String [] args) {
		
		File file = new File("/Users/christinabannon/FileFolder/Vehicles.csv");
		ArrayList<Vehicle> vehicles = readCSVFile(file);
		vehicles = sortList(vehicles);
		
		String pathName = getFilePath(file);
		System.out.println(pathName);
		TxtVehicleReport txtReport = 
				new TxtVehicleReport(vehicles, pathName, "V1Report.txt");
	}
	
	
	public static String getFilePath(File file) {
		String absolutePath = file.getAbsolutePath();
		String fileName = file.getName();
		String pathName = 
				absolutePath.substring(0, (absolutePath.length() - fileName.length()));
		return pathName; 
	}
	
	public static ArrayList <Vehicle> readCSVFile(File file) {
		ArrayList<Vehicle> vehicles = null;
		
		try {
			VehicleCSVInput vehicleCSVInput = new VehicleCSVInput(file);
			vehicles = vehicleCSVInput.processCSV(); 
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return vehicles; 
	}
	
	public static ArrayList<Vehicle> sortList(ArrayList<Vehicle> vehicles) {
		SortVehicles sortVehicles = new SortVehicles();
		sortVehicles.sort(vehicles);
		return vehicles;
	}
}
