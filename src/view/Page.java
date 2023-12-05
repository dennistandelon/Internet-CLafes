package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import model.User;
import util.Environment;

public abstract class Page {

	/*
	 * This abstract class is the basic template 
	 * for all Scene used in this application
	 * */
	
	public String title;
	protected Scene scene;
	protected User currentUser;
	
	protected abstract void init(); // Used for initialize Component
	protected abstract void setLayout(); // Used for layouting Component
	protected abstract void setStyle(); // Used for Styling Component
	protected abstract void setAction(); // Used to set several action for component
	
	public Page() {
		this.scene = new Scene(new Group(),Environment.SCREEN_WIDTH,Environment.SCREEN_HEIGHT);
		init();
		setLayout();
		setStyle();
		setAction();
	}

	public Scene getScene() {
		return this.scene;
	}
	
	public void setUser(User user) {
		this.currentUser = user;
	}
}
