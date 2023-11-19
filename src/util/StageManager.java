package util;

import javafx.stage.Stage;
import view.Page;

public class StageManager {
	
	/*
	 * Kelas ini berfungsi untuk mengatur instance Stage yang ada di aplikasi.
	 * Di buat dengan Pattern Singleton sehingga hanya ada satu global instance Stage dalam aplikasi.
	 * */

	private static StageManager globalInstance;
	private Stage primaryStage;
	
	private StageManager() {
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
}
