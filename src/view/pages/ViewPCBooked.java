package view.pages;

import java.sql.Date;
import java.util.Vector;

import controller.PCBookController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.PCBook;
import view.Page;

public class ViewPCBooked extends Page {

	// Table view component for displaying Booked PC List
	private TableView<PCBook> pcs;
	private TableColumn<PCBook, String> idCol, bookidCol, dateCol, userCol;
	
	// Component for Cancel Book, Finish Book, and Assign User to new PC
	private Label finish_lbl, cancel_lbl, update_lbl, pc_lbl, update_error_lbl;
	private DatePicker finish_dp, cancel_dp;
	private TextField update_tf;
	private Button finish_btn, cancel_btn, update_btn;
	private VBox left_vb, right_vb, center_vb;
	private HBox hb;
	
	// add blank space between component for style
	private Region filler1, filler2; 
	
	public ViewPCBooked() {
		this.title = "Internet Clafes - View Booked PC";
	}

	@Override
	protected void init() {
		finish_lbl = new Label("Select Booking Date to Finish");
		cancel_lbl = new Label("Select Booking Date to Cancel");
		update_lbl = new Label("Assign User to new PC, Selected Book Id: -");
		pc_lbl = new Label("New PC ID");
		update_error_lbl = new Label();
		
		finish_dp = new DatePicker();
		cancel_dp = new DatePicker();
		update_tf = new TextField();
		
		finish_btn = new Button("Finish");
		cancel_btn = new Button("Cancel");
		update_btn = new Button("Assign");
		
		filler1 = new Region();
		filler2 = new Region();
		hb = new HBox();
		left_vb  = new VBox();
		center_vb = new VBox();
		right_vb = new VBox();
		
		pcs = new TableView<PCBook>();

		bookidCol = new TableColumn<PCBook,String>("Book ID");
		bookidCol.setCellValueFactory(new PropertyValueFactory<PCBook,String>("BookID"));
		
		idCol = new TableColumn<PCBook,String>("PC ID");
		idCol.setCellValueFactory(new PropertyValueFactory<PCBook,String>("PC_ID"));
		
		dateCol = new TableColumn<PCBook,String>("Booked Date");
		dateCol.setCellValueFactory(new PropertyValueFactory<PCBook,String>("BookedDate"));
		
		userCol = new TableColumn<PCBook,String>("User");
		userCol.setCellValueFactory(new PropertyValueFactory<PCBook,String>("UserID"));
	}

	@Override
	protected void setLayout() {
		
		pcs.getColumns().add(bookidCol);
		pcs.getColumns().add(idCol);
		pcs.getColumns().add(dateCol);
		pcs.getColumns().add(userCol);

		pcs.getItems().addAll(PCBookController.GetAllPCBookedData());

		left_vb.getChildren().add(finish_lbl);
		left_vb.getChildren().add(finish_dp);
		left_vb.getChildren().add(finish_btn);
		
		center_vb.getChildren().add(cancel_lbl);
		center_vb.getChildren().add(cancel_dp);
		center_vb.getChildren().add(cancel_btn);

		right_vb.getChildren().add(update_lbl);
		right_vb.getChildren().add(pc_lbl);
		right_vb.getChildren().add(update_tf);
		right_vb.getChildren().add(update_btn);
		right_vb.getChildren().add(update_error_lbl);

		hb.getChildren().addAll(left_vb,filler1,center_vb,filler2,right_vb);
		
		mainFrame.setCenter(pcs);
		mainFrame.setBottom(hb);
	}

	@Override
	protected void setStyle() {

		idCol.prefWidthProperty().bind(pcs.widthProperty().multiply(0.25));
        bookidCol.prefWidthProperty().bind(pcs.widthProperty().multiply(0.25));
        userCol.prefWidthProperty().bind(pcs.widthProperty().multiply(0.25));
        dateCol.prefWidthProperty().bind(pcs.widthProperty().multiply(0.25));

        HBox.setHgrow(filler1, Priority.ALWAYS);
        HBox.setHgrow(filler2, Priority.ALWAYS);

        HBox.setMargin(left_vb, new Insets(20));
        HBox.setMargin(center_vb, new Insets(20));
        HBox.setMargin(right_vb, new Insets(20));
        
        pcs.setCursor(Cursor.HAND);
        finish_btn.setCursor(Cursor.HAND);
        cancel_btn.setCursor(Cursor.HAND);
        update_btn.setCursor(Cursor.HAND);
        
        hb.setAlignment(Pos.CENTER);
        update_error_lbl.setStyle("-fx-text-fill: red;" + "-fx-font-weight:bold;");
	}

	@Override
	protected void setAction() {
		
		/*
		 * Setup update description label when the items in Table view being selected
		 * */
		pcs.setOnMouseClicked(e->{
			int index = pcs.getSelectionModel().getSelectedIndex();
			if(index < 0) {
				return;
			}
			update_lbl.setText("Assign User to new PC, Selected Book Id: " + pcs.getItems().get(index).getBookID());
		});
		
		update_btn.setOnAction(e->{
			int index = pcs.getSelectionModel().getSelectedIndex();
			if(index < 0) {
				update_error_lbl.setText("Select a item");
				return;
			}

			PCBook bookdata = pcs.getItems().get(index);			
			update_error_lbl.setText(PCBookController.AssignUserToNewPC(bookdata.getBookID(), update_tf.getText()));				

			refreshTable();
		});
		
		finish_btn.setOnAction(e->{
			Date date = Date.valueOf(this.finish_dp.getValue());
			Vector<PCBook> PCBooks = PCBookController.GetPCBookedByDate(date);
			PCBookController.FinishBook(PCBooks);
			
			refreshTable();
		});
		
		cancel_btn.setOnAction(e->{
			Date date = Date.valueOf(this.cancel_dp.getValue());
			for (PCBook p : PCBookController.GetPCBookedByDate(date)) {
				PCBookController.DeleteBookData(p.getBookID());
			}
			refreshTable();
		});

	}
	
	
	/*
	 * Additional method for refreshing table view data
	 * */
	private void refreshTable() {
		pcs.getItems().clear();
		pcs.getItems().addAll(PCBookController.GetAllPCBookedData());
	}

}
