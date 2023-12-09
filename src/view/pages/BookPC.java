package view.pages;

import java.sql.Date;

import controller.PCBookController;
import controller.PCController;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import view.Page;

public class BookPC extends Page{	
	
	private Label date_lbl, pc_lbl, error_lbl;
	private DatePicker date_dp;
	private TextField pc_tf;
	
	private GridPane gp;
	
	private Button submit_btn;
	
	public BookPC() {
		this.title = "Internet Clafes - Make Booking";
	}
	
	@Override
	protected void init() {
		this.date_lbl = new Label("Book Date");
		this.pc_lbl = new Label("PC ID");
		this.pc_tf = new TextField();
		this.date_dp = new DatePicker();
		this.submit_btn = new Button("Book");
		this.error_lbl = new Label();
		
		this.gp = new GridPane();
	}

	@Override
	protected void setLayout() {
		this.gp.add(date_lbl, 0, 0);
		this.gp.add(date_dp, 1, 0);
		this.gp.add(pc_lbl, 0, 1);
		this.gp.add(pc_tf, 1, 1);
		this.gp.add(submit_btn, 0, 2);
		this.gp.add(error_lbl, 0, 3);
		
		mainFrame.setCenter(gp);
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setAction() {
		this.submit_btn.setOnMouseClicked(e->{
			String PcID = this.pc_tf.getText();
			Date BookedDate = Date.valueOf(this.date_dp.getValue());
			int UserID = currentUser.getUserID();
			
			if(PCController.GetPCDetail(PcID) == null) {
				error_lbl.setText("PC not found!");
			} else if(PCBookController.GetPCBookedData(PcID, BookedDate) != null) {
				error_lbl.setText("PC is not available in selected day!");
			} else {
				error_lbl.setText(PCBookController.AddNewBook(PcID, UserID, BookedDate));
			}
		});
		
	}


}
