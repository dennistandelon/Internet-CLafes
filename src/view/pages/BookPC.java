package view.pages;

import java.sql.Date;

import controller.PCBookController;
import controller.PCController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.Page;

public class BookPC extends Page{	
	
	private Label date_lbl, pc_lbl, error_lbl;
	private DatePicker date_dp;
	private TextField pc_tf;
	private VBox vb;
	
	private GridPane gp;
	
	private Button submit_btn;
	
	public BookPC() {
		this.title = "Internet Clafes - Make Booking";
	}
	
	@Override
	protected void init() {
		this.vb = new VBox();
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
		
		this.vb.getChildren().add(gp);
		this.vb.getChildren().add(submit_btn);
		this.vb.getChildren().add(error_lbl);
		
		mainFrame.setCenter(vb);
	}

	@Override
	protected void setStyle() {
		VBox.setMargin(submit_btn, new Insets(20));
		
		gp.setAlignment(Pos.CENTER);
		vb.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(20);
		
		
		pc_lbl.setFont(Font.font(null,FontWeight.BOLD,16));
		date_lbl.setFont(Font.font(null,FontWeight.BOLD,16));
		pc_tf.setMinSize(200, 32);
		pc_tf.setFont(Font.font(16));
		date_dp.setMinSize(200, 32);
		
		submit_btn.setCursor(Cursor.HAND);
		
		error_lbl.setStyle("-fx-text-fill: red;" + "-fx-font-weight:bold;");
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
