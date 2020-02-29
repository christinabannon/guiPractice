package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import storage.Vehicle;
import storage.VehicleCSVInput;
import javafx.stage.Stage;

public class CSVFileChooser extends Application {
//	private Desktop desktop = Desktop.getDesktop();
	
	@Override
	public void start(final Stage stage) {
		stage.setTitle("File Chooser");
		
		final FileChooser fileChooser = new FileChooser();
		
		final Button openButton = new Button("Open a CSV");
		
		openButton.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));           
						File file = fileChooser.showOpenDialog(stage);
						if (file != null) {
							openFile(file);
						}
					}
				});
		
		final GridPane inputGridPane = new GridPane();
		
		GridPane.setConstraints(openButton, 0, 0);
		inputGridPane.setHgap(6);
		inputGridPane.setVgap(6);
		inputGridPane.getChildren().addAll(openButton);
		
		final Pane rootGroup = new VBox(12);
		rootGroup.getChildren().addAll(inputGridPane);
		rootGroup.setPadding(new Insets(20, 30, 20, 30));
		
		stage.setScene(new Scene(rootGroup));
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	// make these show up in a window
    private void openFile(File file) {
    	
		try {
			VehicleCSVInput vehicleCSVInput = new VehicleCSVInput(file);
			ArrayList<Vehicle> vehicles = vehicleCSVInput.processCSV(); 
			for (Vehicle vehicle : vehicles) {
				System.out.println("Year : " + vehicle.getYear());
				System.out.println("Make : " + vehicle.getMake());
				System.out.println("Model : " + vehicle.getModel());
				System.out.println("MSRP : " + vehicle.getMSRP());
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		/* if you wanted to open the file
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                CSVFileChooser.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        } */
    }
	

}




