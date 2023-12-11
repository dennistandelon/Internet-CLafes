package view.pages;

import java.sql.Date;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TransactionDetail;
import view.Page;

public class ViewCsTransactionHistory extends Page{

	private TableView<TransactionDetail> details;
	private TableColumn<TransactionDetail, String> pcCol, nameCol;
	private TableColumn<TransactionDetail, Integer> idCol;
	private TableColumn<TransactionDetail, Date> dateCol;
	
	public ViewCsTransactionHistory() {
		this.title = "Internet Clafes - Customer Transaction History";
	}

	@Override
	protected void init() {
		
		details = new TableView<TransactionDetail>();

		idCol = new TableColumn<TransactionDetail,Integer>("Transaction ID");
		idCol.setCellValueFactory(new PropertyValueFactory<TransactionDetail,Integer>("TransactionID"));
		
		pcCol = new TableColumn<TransactionDetail,String>("PC ID");
		pcCol.setCellValueFactory(new PropertyValueFactory<TransactionDetail,String>("PC_ID"));
		
		nameCol = new TableColumn<TransactionDetail,String>("Customer Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<TransactionDetail,String>("CustomerName"));
		
		dateCol = new TableColumn<TransactionDetail,Date>("Booked Time");
		dateCol.setCellValueFactory(new PropertyValueFactory<TransactionDetail,Date>("BookedTime"));
	}

	@Override
	protected void setLayout() {
		details.getColumns().add(idCol);
		details.getColumns().add(pcCol);
		details.getColumns().add(nameCol);
		details.getColumns().add(dateCol);
		
		details.getItems().addAll(TransactionDetail.GetUserTransactionDetail(currentUser.getUserID()));
		
		mainFrame.setCenter(details);
	}

	@Override
	protected void setStyle() {
		idCol.prefWidthProperty().bind(details.widthProperty().multiply(0.25));
        pcCol.prefWidthProperty().bind(details.widthProperty().multiply(0.25));
        nameCol.prefWidthProperty().bind(details.widthProperty().multiply(0.25));
        dateCol.prefWidthProperty().bind(details.widthProperty().multiply(0.25));

	}

	@Override
	protected void setAction() {
		// TODO Auto-generated method stub

	}


}
