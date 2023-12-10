package util;

import javafx.stage.Stage;
import model.User;
import view.Page;

public class StageManager {
	
	/*
	 * This class used for maintain Stage instance in the application.
	 * Used Singleton pattern so there is only one global Stage instance used in this Application.
	 * */

	private static StageManager globalInstance; // global instance
	private Stage primaryStage;
	private User currentUser;
	
	private StageManager() {
		this.currentUser = null;
		this.primaryStage = new Stage();
	}
	
	// Method to access the StageManager global instance
	public static StageManager getInstance() {
		if(globalInstance == null) {
			globalInstance = new StageManager();
		}
		
		return globalInstance;
	}

	/*
	 * Method to set scene into Stage
	 * @params page, Concrete class that inherit abstract Page class 
	 * */
	public void setPage(Page page) {
		this.primaryStage.setScene(page.getScene());
		this.primaryStage.setTitle(page.title);
		
		if(!this.primaryStage.isShowing()) {
			this.primaryStage.show();
		}
	}
	
	// Getter and Setter
	public void setUser(User user) {
		this.currentUser = user;
	}
	
	public User getUser() {
		return this.currentUser;
	}
}
