package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) {
    	GridPane root = new GridPane();
    	root.setHgap(8); // gaps between rows
    	root.setVgap(8); // gaps between columns
    	//root.setPadding(new Insets(5));
    	

        ColumnConstraints colConstraint = new ColumnConstraints();
        colConstraint.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().addAll(colConstraint, colConstraint, 
        		colConstraint, colConstraint);

    	
        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setVgrow(Priority.ALWAYS);
        root.getRowConstraints().addAll(rowConstraint, rowConstraint, rowConstraint);
        
        Label instructionLabel = new Label("Select a Report Type and press OK to continue");
        
		// create combo box
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Create Vehicle Cost Report",
			        "Create Vehicle Sales Report"
			    );
		
		final ComboBox<String> reportTypeComboBox = new ComboBox<String>(options);
        
        Button okButton = new Button("OK");
        Button closeButton = new Button("Close");
       
        GridPane.setHalignment(instructionLabel,  HPos.CENTER);
        GridPane.setHalignment(reportTypeComboBox, HPos.CENTER);
        GridPane.setHalignment(okButton, HPos.RIGHT);
        GridPane.setHalignment(closeButton, HPos.RIGHT);
    
        root.add(instructionLabel, 0, 0);
        root.add(reportTypeComboBox, 0, 1);
        root.addRow(2, okButton, closeButton);
//        root.add(okButton, 1, 2);
//        root.add(closeButton, 2, 2);
        
        Scene scene = new Scene(root, 400, 150);

        stage.setTitle("New folder");
        stage.setScene(scene);
        stage.show();
    	
    }

    public static void main(String[] args) {
        launch();
    }

}