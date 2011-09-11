package resources.javavdl.pages.test;

import javax.faces.component.html.HtmlBody;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import test.BodyTemplate;

public class OtherPage extends BodyTemplate {

	@Override
	public void buildBody(FacesContext context, HtmlBody body) {
	
		HtmlOutputText output = new HtmlOutputText();
		output.setValue("This is a new page.");
		body.getChildren().add(output);
		
	}
}
