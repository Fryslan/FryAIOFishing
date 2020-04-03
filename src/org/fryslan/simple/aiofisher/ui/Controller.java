package org.fryslan.simple.aiofisher.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import org.fryslan.simple.aiofisher.FryAIOFisher;
import org.fryslan.simple.aiofisher.data.Fish;
import org.fryslan.simple.aiofisher.data.Location;

import javax.swing.*;

public class Controller {

	@FXML
	private ChoiceBox<Fish> fish_choicebox;

	@FXML
	private CheckBox banking_checkbox;

	@FXML
	private Button start_button;

	@FXML
	private ChoiceBox<Location> location_choicebox;

	@FXML
	void initialize() {
		System.out.println("Initialize");
		location_choicebox.getItems().setAll(Location.values());
		location_choicebox.setValue(Location.POWERFISH);
		location_choicebox.selectionModelProperty().addListener((observable, oldValue, newValue) -> {
			fish_choicebox.getItems().clear();
			fish_choicebox.getItems().addAll(newValue.getSelectedItem().getFish());
			FryAIOFisher.location = newValue.getSelectedItem();
		});

		fish_choicebox.setValue(Fish.SHRIMP);
		fish_choicebox.getItems().setAll(Fish.values());
		fish_choicebox.selectionModelProperty().addListener((observable, oldValue, newValue) -> FryAIOFisher.fish = newValue.getSelectedItem());

		banking_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> FryAIOFisher.banking = newValue);

		start_button.setOnAction(event -> {
					FryAIOFisher.loaded = true;
					System.out.println("Button Clicked");
					UserInterface.stage.close();
				}
		);
	}

}
