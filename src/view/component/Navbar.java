package view.component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.User;
import util.StageManager;
import view.auth.LoginPage;
import view.pages.BookPC;
import view.pages.ViewCsTransactionHistory;

public class Navbar extends MenuBar{

	/*
	 * This class used to generate Navbar component for the pages,
	 * depend on the user role
	 * */
	private Menu menus;
	
	private User user; // User that currently logged in
	
	public Navbar(User user) {
		this.user = user;
		setComponent();
	}
	
	public void setComponent() {
		menus = new Menu("Menu");
		
		if(this.user.getUserRole().equals("Operator")) {
			setOperatorNav();
		} else if(user.getUserRole().equals("Computer Technician")) {
			setTechnicianNav();
		} else if(user.getUserRole().equals("Admin")) {
			setAdminNav();
		} else {
			setCustomerNav();
		}
		
		this.menus.getItems().add(new MenuItem("Logout") {
			{
				setOnAction(e->{
					StageManager.getInstance().setPage(new LoginPage());
				});
			}
		});

		this.getMenus().add(menus);
	}
	
	public void setCustomerNav() {
		MenuItem trHistory = new MenuItem("Transaction History");
		MenuItem bookPc = new MenuItem("Book PC");
		MenuItem report = new MenuItem("Make Report");
		
		trHistory.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StageManager.getInstance().setPage(new ViewCsTransactionHistory(),user);
			}
		});
		

		bookPc.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StageManager.getInstance().setPage(new BookPC(),user);
			}
		});
		

		report.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		
		this.menus.getItems().addAll(trHistory, bookPc, report);
	}
	
	public void setTechnicianNav() {
		MenuItem jobview = new MenuItem("View Job");
		
		jobview.setOnAction(e->{
			
		});
		
		this.menus.getItems().add(jobview);
	}
	
	public void setOperatorNav() {
		MenuItem bookPc = new MenuItem("View Booking");
		MenuItem report = new MenuItem("Make Report");
		
		this.menus.getItems().addAll(bookPc,report);
	}
	
	public void setAdminNav() {
		MenuItem trHistory = new MenuItem("View All Transaction");
		MenuItem job = new MenuItem("Manage Job");
		MenuItem pc = new MenuItem("Manage PC");
		MenuItem report = new MenuItem("View All Report");
		MenuItem staff = new MenuItem("Manage Staff");
		
		this.menus.getItems().addAll(staff,job,pc,trHistory,report);
	}

}
