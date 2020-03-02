package application;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import storage.Vehicle;
import storage.VehicleCSVInput;

public class MainView extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(final Stage primaryStage) {

		Label instructionLabel = new Label("Select a Report Type to Create");

		// create combo box
		ObservableList<String> options = 
				FXCollections.observableArrayList(
						"Vehicle Cost Report",
						"Vehicle Sales Report"
						);

		final ComboBox<String> reportTypeComboBox = new ComboBox<String>(options);

		Button okButton = new Button("OK");
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//createReportWindow(event);
				final FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));           
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file != null) {
					openFile(file);
				}
			}
		});

		Button closeButton = new Button("Close");

		HBox hBox = new HBox(5, okButton, closeButton);
		hBox.setAlignment(Pos.BOTTOM_RIGHT);

		BorderPane border = new BorderPane();
		border.setPadding(new Insets(5));

		border.setTop(instructionLabel);
		border.setCenter(reportTypeComboBox);
		border.setBottom(hBox); 

		BorderPane.setAlignment(instructionLabel, Pos.CENTER);
		BorderPane.setAlignment(reportTypeComboBox, Pos.CENTER);

		Scene scene = new Scene(border, 400, 200);

		primaryStage.setTitle("New folder");
		primaryStage.setScene(scene);
		primaryStage.show();

		// stage 1 what do you want to do?
		//// create report

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



