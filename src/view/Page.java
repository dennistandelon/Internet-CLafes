package view;

import javafx.scene.Scene;

public abstract class Page {

	/*
	 * This abstract class is the basic template 
	 * for all Scene used in this application
	 * */
	
	public String title;
	protected Scene scene;
	protected abstract void init(); // Used for initialize Component
	protected abstract void setLayout(); // Used for layouting Component
	protected abstract void setStyle(); // Used for Styling Component
	protected abstract void setAction(); // Used to set several action for component
	
	public Page() {
		init();
		setLayout();
		setStyle();
		setAction();
	}

	public Scene getScene() {
		return this.scene;
	}
}
