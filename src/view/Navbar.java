package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import model.User;

public class Navbar extends MenuBar{

	private User user;
	
	public Navbar(User user) {
		this.user = user;
	}
	
	public void setComponent() {
		
		if(this.user.getUserRole().equals("Operator")) {
			setOperatorNav();
		} else if(user.getUserRole().equals("Computer Technician")) {
			setTechnicianNav();
		} else if(user.getUserRole().equals("Admin")) {
			setAdminNav();
		} else {
			setCustomerNav();
		}
	
	}
	
	public void setCustomerNav() {
		Menu trHistory = new Menu("Transaction History");
		Menu bookPc = new Menu("Book PC");
		Menu report = new Menu("Make Report");
		
		this.getMenus().add(trHistory);
		this.getMenus().add(bookPc);
		this.getMenus().add(report);
	}
	
	public void setTechnicianNav() {
		Menu jobview = new Menu("View Job");
		
		this.getMenus().add(jobview);
	}
	
	public void setOperatorNav() {
		Menu bookPc = new Menu("View Booking");
		Menu report = new Menu("Make Report");
		
		this.getMenus().add(bookPc);
		this.getMenus().add(report);
	}
	
	public void setAdminNav() {
		Menu trHistory = new Menu("View All Transaction");
		Menu job = new Menu("Manage Job");
		Menu pc = new Menu("Manage PC");
		Menu report = new Menu("View All Report");
		Menu staff = new Menu("Manage Staff");
		
		this.getMenus().add(staff);
		this.getMenus().add(pc);
		this.getMenus().add(job);
		this.getMenus().add(trHistory);
		this.getMenus().add(report);
	}

}
