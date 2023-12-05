package view.pages;

import java.sql.Date;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.TransactionDetail;
import model.User;
import view.Page;
import view.component.Navbar;

public class ViewCsTransactionHistory extends Page{

	private TableView<TransactionDetail> details;
	private TableColumn<TransactionDetail, String> pcCol, nameCol;
	private TableColumn<TransactionDetail, Integer> idCol;
	private TableColumn<TransactionDetail, Date> dateCol;
	
	private BorderPane bp;
	
	
	public ViewCsTransactionHistory() {
		this.title = "Internet Clafes - Customer Transaction History";
	}
	
	public void setUser(User user) {
		currentUser = user;
		
		Navbar navbar = new Navbar(currentUser);
		
		bp.setTop(navbar);
		
		details.getItems().addAll(TransactionDetail.GetUserTransactionDetail(currentUser.getUserID()));
	}

	@Override
	protected void init() {
		
		details = new TableView<TransactionDetail>();
		bp = new BorderPane();

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
		
		bp.setCenter(details);
		

		this.scene.setRoot(bp);
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setAction() {
		// TODO Auto-generated method stub

	}


}
