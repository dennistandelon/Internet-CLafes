package main;

import javafx.application.Application;
import javafx.stage.Stage;
import util.StageManager;
import view.auth.RegisterPage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		StageManager mainStage = StageManager.getInstance();
		
		mainStage.setPage(new RegisterPage());
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
