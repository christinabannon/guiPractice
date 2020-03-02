package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import storage.Vehicle;
import storage.VehicleCSVInput;
import javafx.stage.Stage;

public class CSVFileChooser extends Application {
//	private Desktop desktop = Desktop.getDesktop();
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(final Stage primaryStage) {
		
		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);		
		root.setPadding(new Insets(5));
		
		/*
		RowConstraints rowConstraints = new RowConstraints();
		rowConstraints.setVgrow(Priority.NEVER);
		
		root.getRowConstraints().addAll(rowConstraints);
		*/
		Label instructions = new Label("Choose a report type from the "
				+ "dropdown to begin a report");
		
		
		// create combo box
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Create Vehicle Cost Report",
			        "Create Vehicle Sales Report"
			    );
		
		final ComboBox<String> reportTypeComboBox = new ComboBox<String>(options);
		
		// stage 1 what do you want to do?
		//// create report
		Button reportTypeChosen = new Button();
		reportTypeChosen.setText("Open a New Window");
		reportTypeChosen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				createReportWindow(event);
			}
		});
		
		//root.
		root.getChildren().add(reportTypeChosen);
		root.getChildren().add(reportTypeComboBox);
		
		Scene primaryScene = new Scene(root, 450, 250);
		primaryStage.setTitle("Dealership Admin");
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}
	
	private void createReportWindow(ActionEvent event) {
		// stage 2 what kind of report?
		//// csv
		// second one
		Stage secondaryStage = new Stage();
		secondaryStage.setTitle("Create Report");
		Button openCSVButton = createOpenCSVButton(secondaryStage);	
		final GridPane inputGridPane = new GridPane();		
		GridPane.setConstraints(openCSVButton, 0, 0);
		inputGridPane.setHgap(6);
		inputGridPane.setVgap(6);
		inputGridPane.getChildren().addAll(openCSVButton);		
		final Pane rootGroup = new VBox(12);
		rootGroup.getChildren().addAll(inputGridPane);
		rootGroup.setPadding(new Insets(20, 30, 20, 30));		
		secondaryStage.setScene(new Scene(rootGroup));
		secondaryStage.show();
	}
	
	private Button createOpenCSVButton(Stage stage) {
		final FileChooser fileChooser = new FileChooser();
		final Button openCSVButton = new Button("Open a CSV");
		openCSVButton.setOnAction(
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
		return openCSVButton;
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




