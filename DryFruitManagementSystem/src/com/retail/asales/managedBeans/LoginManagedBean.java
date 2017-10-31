package com.retail.asales.managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.retail.asales.entity.User;
import com.retail.asales.service.LoginService;
import com.retail.asales.util.SessionUtility;
import com.retail.asales.util.Util;

@ManagedBean
@SessionScoped
public class LoginManagedBean {

	private User user;

	public LoginManagedBean() {
		user = new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String loginUserAction() {
		LoginService confirmLogin = new LoginService();
		User user = confirmLogin.confirmLogin(getUser().getUserId(), getUser().getPassword());
		if (user == null) {
			addMessage("Userid and password combination is incorrect", FacesMessage.SEVERITY_ERROR);
			return "";
		} else {
			setUser(user);
			HttpSession session = Util.getSession();
			session.setAttribute("user", user);
			SessionUtility.setUser(user);
			SessionUtility.setUserLoggedIn(true);
			return "/addItem" + "?faces-redirect=true";
		}
	}

	public String logout() {
		System.out.println("Logged Out");
		HttpSession session = Util.getSession();
		if (session != null) {
			session.invalidate();
		}
		SessionUtility.setUser(null);
		SessionUtility.setUserLoggedIn(false);
		addMessage("Logged  Out Successfully.", FacesMessage.SEVERITY_INFO);
		System.out.println("Logged Out");
		return "/login.xhtml" + "?faces-redirect=true";
	}

	public void logouts() {

	}

	public void addMessage(String summary, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, null);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

}
