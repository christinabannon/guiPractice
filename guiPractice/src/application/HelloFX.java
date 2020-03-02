package application;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import storage.Vehicle;
import storage.VehicleCSVInput;

public class HelloFX extends Application {

    @Override
    public void start(Stage primaryStage) {
 
    	Label instructions = new Label();
    	instructions.setWrapText(true);
    	instructions.setText("To start a report: \n"
    			+ "\t1. Make the following selections \n"
    			+ "\t2. Click the Next Step button.");

		Label reportTypeLabel = new Label("Select a Report Type: ");
		ObservableList<String> reportTypeOptions = 
				FXCollections.observableArrayList(
						"Vehicle Cost Report",
						"Vehicle Sales Report"
						);
		final ComboBox<String> reportTypeComboBox = 
				new ComboBox<String>(reportTypeOptions);
		
		// might be hard to relay this info to the next thing
		Label inputFileTypeLabel = new Label("Select Input File Type: ");
		ObservableList<String> inputFileTypeOptions = 
				FXCollections.observableArrayList(
						".csv",
						".tsv",
						".xlsx"
						);
		final ComboBox<String> inputFileTypeComboBox = 
				new ComboBox<String>(inputFileTypeOptions);
		
		Label inputFileLabel = new Label("Select Input File: ");
		Button browseButton = new Button("Browse");
		browseButton.setOnAction(new EventHandler<ActionEvent>() {
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
		
		Label outputFileTypeLabel = new Label("Select Output File Type: ");
		ObservableList<String> outputFileTypeOptions = 
				FXCollections.observableArrayList(
						".txt",
						".docx",
						".pdf"
						);

		final ComboBox<String> outputFileTypeComboBox = 
				new ComboBox<String>(outputFileTypeOptions);
		
		Button createReportButton = new Button("Create Report");
		Button closeButton = new Button("Close");
		
		reportTypeComboBox.setMaxWidth(Double.MAX_VALUE);
		inputFileTypeComboBox.setMaxWidth(Double.MAX_VALUE);
		outputFileTypeComboBox.setMaxWidth(Double.MAX_VALUE);
		createReportButton.setMaxWidth(Double.MAX_VALUE);
		closeButton.setMaxWidth(Double.MAX_VALUE);
		
    	double standardSpacing = 15;	
		HBox hBox = new HBox(standardSpacing, createReportButton, closeButton);
		hBox.setMaxWidth(Double.MAX_VALUE);
		
    	GridPane gridPane = new GridPane();

    	gridPane.setGridLinesVisible(true);
    	gridPane.setPrefSize(400,300);
    	gridPane.setHgap(standardSpacing);
    	gridPane.setVgap(standardSpacing);
    	gridPane.setPadding(new Insets(standardSpacing));
    	
    	ColumnConstraints quarterCol = new ColumnConstraints();
    	quarterCol.setPercentWidth(25);
    	
    	ColumnConstraints createCol = new ColumnConstraints();
    	createCol.setPercentWidth(33);
    	
    	ColumnConstraints closeCol = new ColumnConstraints();
    	closeCol.setPercentWidth(17);
    	
    	gridPane.getColumnConstraints().addAll(quarterCol, quarterCol, createCol, closeCol);

//    	gridPane.add(instructions, 0, 0, 2, 1); // col, row, col-to, row-to
//    	gridPane.add(reportTypeLabel, 0, 1);
//    	gridPane.add(reportTypeComboBox, 1, 1);
//    	gridPane.add(inputFileTypeLabel, 0, 2);
//    	gridPane.add(inputFileTypeComboBox, 1, 2);
//    	gridPane.add(outputFileTypeLabel, 0, 3);
//    	gridPane.add(outputFileTypeComboBox, 1, 3);
//    	gridPane.add(hBox, 1, 4);
    	
    	gridPane.add(instructions,           0, 0, 3, 1); // col, row, col-to, row-to
    	gridPane.add(reportTypeLabel,        0, 1, 2, 1); // 
    	gridPane.add(reportTypeComboBox,     2, 1, 2, 1);
    	gridPane.add(inputFileTypeLabel,     0, 2, 2, 1);
    	gridPane.add(inputFileTypeComboBox,  2, 2, 2, 1);
    	gridPane.add(outputFileTypeLabel,    0, 3, 2, 1);
    	gridPane.add(outputFileTypeComboBox, 2, 3, 2, 1);
    	gridPane.add(createReportButton,     2, 4);
    	gridPane.add(closeButton,            3, 4);
    	gridPane.setAlignment(Pos.CENTER);
		
/*
		BorderPane border = new BorderPane();
		border.setPadding(new Insets(5));

		
		border.setTop(instructionLabel);
		border.setCenter(reportTypeComboBox);
		border.setBottom(hBox); 

		BorderPane.setAlignment(instructionLabel, Pos.CENTER);
		BorderPane.setAlignment(reportTypeComboBox, Pos.CENTER);
*/
		Scene scene = new Scene(gridPane, 400, 300);

		primaryStage.setTitle("New folder");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
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
		
	}

}
	