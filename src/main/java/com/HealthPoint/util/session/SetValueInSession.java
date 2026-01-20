package com.HealthPoint.util.session;

import javax.servlet.http.HttpSession;

public class SetValueInSession {
	public void setValueInSession(HttpSession session, String name, String email, String userName) {
		 //Set value in session attributes
        session.setAttribute("name", name);
        session.setAttribute("email", email);
        session.setAttribute("userName", userName);	 
	}

}
