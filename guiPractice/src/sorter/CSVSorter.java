package sorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import storage.Vehicle;
public class CSVSorter {

	
	public ArrayList<Vehicle> order(ArrayList<Vehicle> vehicles) {
		
		Collections.sort(vehicles, new Comparator() {
			public int compare(Object vehicle1, Object vehicle2) {
				
				Integer year1 = ((Vehicle)vehicle1).getYear();
				Integer year2 = ((Vehicle)vehicle2).getYear();
				
				int winner = year1.compareTo(year2);

				if (winner == 0) {
					String model1 = ((Vehicle)vehicle1).getModel();
					String model2 = ((Vehicle)vehicle2).getModel();
					winner = model1.compareTo(model2);
				}
				return winner; 
			}});
		return vehicles;
		}
	
}

