package view.pages;

import java.sql.Date;

import controller.ReportController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Report;
import view.Page;

public class ViewAllReport extends Page {

	private TableView<Report> reports;
	private TableColumn<Report, Integer> idCol;
	private TableColumn<Report, String> roleCol,pcCol, noteCol;
	private TableColumn<Report, Date> dateCol;
	
	public ViewAllReport() {
		this.title = "Internet Clafes - View All Report";
	}
	
	@Override
	protected void init() {
		
		reports =  new TableView<Report>();
		
		idCol = new TableColumn<Report, Integer>("Id");
		idCol.setCellValueFactory(new PropertyValueFactory<Report, Integer>("Report_ID"));
		
		roleCol = new TableColumn<Report, String>("User Role");
		roleCol.setCellValueFactory(new PropertyValueFactory<Report, String>("UserRole"));
		
		pcCol = new TableColumn<Report, String>("PC ID");
		pcCol.setCellValueFactory(new PropertyValueFactory<Report, String>("PC_ID"));
		
		noteCol = new TableColumn<Report, String>("Report Note");
		noteCol.setCellValueFactory(new PropertyValueFactory<Report, String>("ReportNote"));
		
		dateCol = new TableColumn<Report, Date>("Report Date");
		dateCol.setCellValueFactory(new PropertyValueFactory<Report, Date>("ReportDate"));
		
	}

	@Override
	protected void setLayout() {
		
		reports.getColumns().add(idCol);
		reports.getColumns().add(pcCol);
		reports.getColumns().add(noteCol);
		reports.getColumns().add(dateCol);

		reports.getItems().addAll(ReportController.GetAllReportData());
		
		mainFrame.setCenter(reports);
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
