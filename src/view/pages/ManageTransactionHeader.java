package view.pages;

import java.sql.Date;

import controller.TransactionController;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.TransactionDetail;
import model.TransactionHeader;
import view.Page;

public class ManageTransactionHeader extends Page {

	private TableView<TransactionHeader> ths;
	private TableColumn<TransactionHeader, Integer> hidCol, sidCol;
	private TableColumn<TransactionHeader, String> sfCol;
	private TableColumn<TransactionHeader, Date> dateCol;
	
	private TableView<TransactionDetail> dtl;
	private TableColumn<TransactionDetail, Integer> didCol;
	private TableColumn<TransactionDetail, String> pidCol, cnCol;
	private TableColumn<TransactionDetail, Date> bookCol;
	
	private Label dtl_lbl;
	private VBox dtlBox;
	
	public ManageTransactionHeader() {
		this.title = "Internet Clafes - All Transaction";
	}

	@Override
	protected void init() {
		ths = new TableView<TransactionHeader>();
		dtl = new TableView<TransactionDetail>();
		
		hidCol = new TableColumn<TransactionHeader, Integer>("Transaction ID");
		hidCol.setCellValueFactory(new PropertyValueFactory<>("TransactionID"));
		
		sidCol = new TableColumn<TransactionHeader, Integer>("Staff ID");
		sidCol.setCellValueFactory(new PropertyValueFactory<>("StaffID"));

		sfCol = new TableColumn<TransactionHeader, String>("Staff Name");
		sfCol.setCellValueFactory(new PropertyValueFactory<>("StaffName"));
		
		dateCol = new TableColumn<TransactionHeader, Date>("Transaction Date");
		dateCol.setCellValueFactory(new PropertyValueFactory<>("TransactionDate"));
		
		didCol = new TableColumn<TransactionDetail, Integer>("Transaction ID");
		didCol.setCellValueFactory(new PropertyValueFactory<>("TransactionID"));
		
		pidCol = new TableColumn<TransactionDetail, String>("PC ID");
		pidCol.setCellValueFactory(new PropertyValueFactory<>("PC_ID"));
		
		cnCol = new TableColumn<TransactionDetail, String>("Customer Name");
		cnCol.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
		
		bookCol = new TableColumn<TransactionDetail, Date>("Booked Time");
		bookCol.setCellValueFactory(new PropertyValueFactory<>("BookedTime"));
		
		dtl_lbl = new Label();
		dtlBox = new VBox();
	}

	@Override
	protected void setLayout() {
		
		ths.getColumns().add(hidCol);
		ths.getColumns().add(sidCol);
		ths.getColumns().add(sfCol);
		ths.getColumns().add(dateCol);
		
		ths.getItems().addAll(TransactionController.GetAllTransactionHeaderData());
		
		dtl.getColumns().add(didCol);
		dtl.getColumns().add(pidCol);
		dtl.getColumns().add(cnCol);
		dtl.getColumns().add(bookCol);
		
		dtlBox.getChildren().add(dtl_lbl);
		dtlBox.getChildren().add(dtl);
		
		mainFrame.setCenter(ths);
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setAction() {
		ths.setOnMouseClicked(e->{
			int trid = ths.getItems().get(ths.getSelectionModel().getSelectedIndex()).getTransactionID();
			dtl.getItems().addAll(TransactionController.GetAllTransactionDetail(trid));
			dtl_lbl.setText("Selected Transaction ID: " + trid + ", here is the details:");
			mainFrame.setBottom(dtlBox);
		});

	}

}
