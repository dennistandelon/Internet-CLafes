package view.component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.User;
import util.StageManager;
import view.auth.LoginPage;
import view.auth.RegisterPage;
import view.pages.BookPC;
import view.pages.MakeReport;
import view.pages.ManageJob;
import view.pages.ManagePC;
import view.pages.ManageTransactionHeader;
import view.pages.ViewAllPC;
import view.pages.ViewAllReport;
import view.pages.ViewAllStaff;
import view.pages.ViewCsTransactionHistory;
import view.pages.ViewPCBooked;
import view.pages.ViewTechnicianJob;

public class Navbar extends MenuBar{

	/*
	 * This class used to generate Navbar component for the pages,
	 * depend on the user role
	 * */
	
	private Menu menus; 
	private User user; // User that currently logged in
	
	public Navbar() {
		this.user = StageManager.getInstance().getUser();
		setComponent();
	}
	
	//Method to Define Navbar menu component
	public void setComponent() {
		menus = new Menu("Menu");
		
		// Check if user already authenticated
		if(user != null) {
			
			// Set Navbar menu item list for admin
			if(user.getUserRole().equals("Admin")) {
				setAdminNav();
				
			} else {
				
				// Add View All PC Menu for all user except Admin
				this.menus.getItems().add(new MenuItem("View All PC") {
					{
						setOnAction(e->{
							StageManager.getInstance().setPage(new ViewAllPC());
						});
					}
				});
				
				// Set Navbar menu item list by User Role
				if(this.user.getUserRole().equals("Operator")) {
					setOperatorNav();
				} else if(user.getUserRole().equals("Computer Technician")) {
					setTechnicianNav();
				} else {				
					setCustomerNav();
				}
			}
			
			/*
			 * Add Logout menu for all logged in Users, 
			 * user will be redirected to Login page
			 * */
			this.menus.getItems().add(new MenuItem("Logout") {
				{
					setOnAction(e->{
						StageManager.getInstance().setUser(null);
						StageManager.getInstance().setPage(new LoginPage());
					});
				}
			});
		} else {			
			setAuthNavbar();
		}
		this.getMenus().add(menus);
	}
	
	
	/*
	 * Method to set Navbar menu component 
	 * for unauthorized user
	 * */
	public void setAuthNavbar() {
		MenuItem login = new MenuItem("Login");
		MenuItem register = new MenuItem("Register");
		
		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StageManager.getInstance().setPage(new LoginPage());
			}
		});
		

		register.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StageManager.getInstance().setPage(new RegisterPage());
			}
		});

		this.menus.getItems().addAll(login,register);
	}
	
	/*
	 * Method to set Navbar menu items component,
	 * for user with role Customer
	 * */
	public void setCustomerNav() {
		MenuItem trHistory = new MenuItem("Transaction History");
		MenuItem bookPc = new MenuItem("Book PC");
		MenuItem report = new MenuItem("Make Report");
		
		trHistory.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StageManager.getInstance().setPage(new ViewCsTransactionHistory());
			}
		});
		

		bookPc.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StageManager.getInstance().setPage(new BookPC());
			}
		});
		

		report.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StageManager.getInstance().setPage(new MakeReport());
			}
		});
		
		
		this.menus.getItems().addAll(trHistory, bookPc, report);
	}

	/*
	 * Method to set Navbar menu items component,
	 * for user with role Computer Technician
	 * */
	public void setTechnicianNav() {
		MenuItem jobview = new MenuItem("View Job");
		
		jobview.setOnAction(e->{
			StageManager.getInstance().setPage(new ViewTechnicianJob());
		});
		
		this.menus.getItems().add(jobview);
	}

	/*
	 * Method to set Navbar menu items component,
	 * for user with role Operator
	 * */
	public void setOperatorNav() {
		MenuItem bookPc = new MenuItem("View Booking");
		MenuItem report = new MenuItem("Make Report");
		
		bookPc.setOnAction(e->{
			StageManager.getInstance().setPage(new ViewPCBooked());
		});
		
		report.setOnAction(e->{
			StageManager.getInstance().setPage(new MakeReport());
		});
		
		this.menus.getItems().addAll(bookPc,report);
	}
	
	/*
	 * Method to set Navbar menu items component,
	 * for user with role Admin
	 * */
	public void setAdminNav() {
		MenuItem trHistory = new MenuItem("View All Transaction");
		MenuItem job = new MenuItem("Manage Job");
		MenuItem pc = new MenuItem("Manage PC");
		MenuItem report = new MenuItem("View All Report");
		MenuItem staff = new MenuItem("Manage Staff");
		
		trHistory.setOnAction(e->{
			StageManager.getInstance().setPage(new ManageTransactionHeader());
		});
		
		job.setOnAction(e->{
			StageManager.getInstance().setPage(new ManageJob());
		});
		
		pc.setOnAction(e->{
			StageManager.getInstance().setPage(new ManagePC());
		});
		
		report.setOnAction(e->{
			StageManager.getInstance().setPage(new ViewAllReport());
		});
		
		staff.setOnAction(e->{
			StageManager.getInstance().setPage(new ViewAllStaff());
		});
		
		this.menus.getItems().addAll(staff,job,pc,trHistory,report);
	}

}
