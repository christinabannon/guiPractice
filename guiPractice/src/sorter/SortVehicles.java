package sorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import storage.Vehicle;
public class SortVehicles {

	
	public ArrayList<Vehicle> sort(ArrayList<Vehicle> vehicles) {
		
		Collections.sort(vehicles, new Comparator<Vehicle>() {
			public int compare(Vehicle vehicle1, Vehicle vehicle2) {
				
				Integer year1 = vehicle1.getYear();
				Integer year2 = vehicle2.getYear();		
				
				int winner = year1.compareTo(year2);
				
				if (winner == 0) {
					String make1 = vehicle1.getMake();
					String make2 = vehicle2.getMake();
					winner = make1.compareTo(make2);
				}
				
				return winner; 
			}});
		return vehicles;
		}
}