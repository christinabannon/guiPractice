package storage;
import java.math.BigDecimal;


/*Note: for java fx need to uncheck X thing
 * need to include user made java fx library
 * need to create a new java fx project
 * 
 * 
 * overarching goals
 *  - read from few different types of files
 *  - store data from a few different types of files
 *  - store data in different orders than the order given?
 *  - maybe parallel array - 1 to hold input order, another to map to appropriate thing
 *  - generate reports sorted on what he wanted
 *  - offer options for different types of sorts
 */
public class Vehicle {
	
	private int year;
	private String make;
	private String model;
	private BigDecimal msrp;
	
	public Vehicle(int year, String make, String model, BigDecimal msrp) {
		this.year = year; 
		this.make = cleanMake(make);//make.substring(0,1).toUpperCase() + make.substring(1).toLowerCase();
		this.model = model.substring(0,1).toUpperCase() + model.substring(1).toLowerCase();//cleanModel(model);
		this.msrp = msrp; 
	}

	private String cleanMake(String make) {
		String cleanMake = make.trim().replaceAll("[^a-zA-Z]", "");
		return cleanMake.substring(0,1).toUpperCase() + cleanMake.substring(1).toLowerCase();
	}
	
	private String cleanModel(String model) {
		String cleanModel = model.trim().replaceAll("^[a-zA-Z0-9|-]", "");
		return cleanModel.substring(0,1).toUpperCase() + cleanModel.substring(1).toLowerCase();
	}

	public int getYear() {
		return year; 
	}
	
	public String getMake() {
		return make; 
	}
	
	public String getModel() {
		return model; 
	}
	
	public BigDecimal getMSRP() {
		return msrp;
	}
}
