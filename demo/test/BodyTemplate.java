package test;

import java.io.IOException;
import java.util.List;

import javavdl.view.Page;

import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlBody;
import javax.faces.context.FacesContext;

public abstract class BodyTemplate implements Page {

	@Override
	public void buildView(FacesContext context, UIViewRoot root) throws IOException {
		
		List<UIComponent> rootChildren = root.getChildren();
		
		UIOutput output = new UIOutput();
		output.setValue("<html xmlns=\"http://www.w3.org/1999/xhtml\">");				
		rootChildren.add(output);
		
		HtmlBody body = new HtmlBody();
		rootChildren.add(body);
		
		buildBody(context, body);
				
		output = new UIOutput();
		output.setValue("</html>");
		rootChildren.add(output);
	}
	
	public abstract void buildBody(FacesContext context, HtmlBody body);
}
