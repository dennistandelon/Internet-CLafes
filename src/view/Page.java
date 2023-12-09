package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import model.User;
import util.Environment;
import util.StageManager;
import view.component.Navbar;

public abstract class Page {

	/*
	 * This abstract class is the basic template 
	 * for all Scene used in this application
	 * */
	
	public String title;
	protected User currentUser; // Authorized user
	protected Scene scene;
	protected BorderPane mainFrame;
	
	protected abstract void init(); // Used for initialize Component
	protected abstract void setLayout(); // Used for layouting Component
	protected abstract void setStyle(); // Used for Styling Component
	protected abstract void setAction(); // Used to set several action for component
	
	public Page() {
		this.currentUser = StageManager.getInstance().getUser();
		this.masterLayout();
		
		init();
		setLayout();
		setStyle();
		setAction();
	}

	public Scene getScene() {
		return this.scene;
	}
	
	/*
	 * Setup the main layout for the scene
	 * */
	public void masterLayout() {
		this.mainFrame = new BorderPane();
		this.mainFrame.setTop(new Navbar());
		
		this.scene = new Scene(new Group(),Environment.SCREEN_WIDTH,Environment.SCREEN_HEIGHT);
		this.scene.setRoot(this.mainFrame);
	}
}
