package test;

import static javavdl.impl.PageFactory.redirectOutcome;

import javax.faces.bean.ManagedBean;

import resources.javavdl.pages.test.OtherPage;


@ManagedBean
public class MyBean {

	private String message;

	public void action() {
		message = "Hi!";
	}
	
	public String navigate() {
		return redirectOutcome(OtherPage.class);
	}
	
	public String getMessage() {
		return message;
	}
	
}
