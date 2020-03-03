package application;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import storage.Vehicle;
import storage.VehicleCSVInput;

public class SelectFileWindow extends Application {

	String fileType = null; 
	String filePattern = null; 
	
	public SelectFileWindow(String fileType) {
		this.fileType = fileType;
		this.filePattern = "*."+fileType.toLowerCase();
		
	}
	@Override
	public void start(Stage stage) throws Exception {
		Label inputFileLabel = new Label("Select " + fileType + " Input File: ");
		TextField textField = new TextField("/");
		Button browseButton = new Button("Browse");
		browseButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters()
					.add(new ExtensionFilter(fileType, filePattern));           
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					textField.setText(file.getAbsolutePath().toString());
					
					// Which should take priority? text or browse?
					openFile(file);
				}
			}
		});
    	
		GridPane gridPane = new GridPane();
		ColumnConstraints quarterCol = new ColumnConstraints();
		quarterCol.setPercentWidth(25);
		double standardSpacing = 15;
    	gridPane.setHgap(standardSpacing);
    	gridPane.setVgap(standardSpacing);
    	gridPane.setPadding(new Insets(standardSpacing));
		gridPane.getColumnConstraints().addAll(quarterCol, quarterCol, quarterCol, quarterCol);
		gridPane.add(inputFileLabel, 0, 0, 4, 1);
		gridPane.add(textField,      0, 1, 3, 1);
		gridPane.add(browseButton,   3, 1, 1, 1);
	  	gridPane.setAlignment(Pos.CENTER);
	  	
		Scene scene = new Scene(gridPane, 500, 200);
		stage.setScene(scene);
		stage.show();
	}
	
	/*
	public static void main (String args) {
		launch(args);
	}
	*/
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
		
	}
	

}
