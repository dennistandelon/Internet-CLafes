package util;

import javafx.stage.Stage;
import model.User;
import view.Page;

public class StageManager {
	
	/*
	 * Kelas ini berfungsi untuk mengatur instance Stage yang ada di aplikasi.
	 * Di buat dengan Pattern Singleton sehingga hanya ada satu global instance Stage dalam aplikasi.
	 * */

	private static StageManager globalInstance;
	private Stage primaryStage;
	private User currentUser;
	
	private StageManager() {
		this.currentUser = null;
		this.primaryStage = new Stage();
	}
	
	public static StageManager getInstance() {
		if(globalInstance == null) {
			globalInstance = new StageManager();
		}
		
		return globalInstance;
	}

	public void setPage(Page page) {
		this.primaryStage.setScene(page.getScene());
		this.primaryStage.setTitle(page.title);
		
		if(!this.primaryStage.isShowing()) {
			this.primaryStage.show();
		}
	}
	
	public void setUser(User user) {
		this.currentUser = user;
	}
	
	public User getUser() {
		return this.currentUser;
	}
}
