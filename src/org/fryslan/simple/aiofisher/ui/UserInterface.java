package org.fryslan.simple.aiofisher.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class UserInterface extends Application {

	public static Stage stage;


	@Override
	public void start(final Stage stage) {
		try {
			this.stage = stage;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(new URL("https://raw.githubusercontent.com/Fryslan/simple-bot/master/fry-fisher.fxml"));
			loader.setController(new Controller());

			Parent page = (Parent) loader.load();
			Scene scene = new Scene(page);
			scene.getStylesheets().add("https://raw.githubusercontent.com/Fryslan/simple-bot/master/fry-fisher.css");

			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initilize() {
		new JFXPanel();

		Platform.runLater(new Runnable() {
			public void run() {
				Platform.setImplicitExit(false);
				new UserInterface().start(new Stage());
			}
		});
	}
}























//	@Override
//	public void start(Stage stage) throws IOException {
//
//		Controller controller = new Controller();
//
//		FXMLLoader fxmlLoader = new FXMLLoader(new URL("https://raw.githubusercontent.com/Fryslan/simple-bot/master/fry-fisher.fxml"));
//		fxmlLoader.setController(controller);
//		Parent root = fxmlLoader.load();
//
//		root.getStylesheets().add("https://raw.githubusercontent.com/Fryslan/simple-bot/master/fry-fisher.css");
//		Scene scene = new Scene(root);
//
//		Platform.setImplicitExit(false);
//		stage.initStyle(StageStyle.UNDECORATED);
//		stage.setScene(scene);
//		stage.show();
//	}
//
//	public static void endInterface(){
//		Platform.exit();
//	}
//
//	public static void startInterface() {
//		try {
//
//			new JFXPanel();
//
//			Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						new UserInterface().start(new Stage());
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void main(String[] args) {
//		launch();
//	}
//}
