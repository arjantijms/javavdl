package resources.javavdl.pages;

import java.io.IOException;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlBody;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import javavdl.view.Page;

public class Intro implements Page {

	@Override
	public void buildView(FacesContext context, UIViewRoot root) throws IOException {

		ELContext elContext = context.getELContext();
		ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
		
		List<UIComponent> rootChildren = root.getChildren();
		
		UIOutput output = new UIOutput();
		output.setValue("<html xmlns=\"http://www.w3.org/1999/xhtml\">");				
		rootChildren.add(output);
		
		HtmlBody body = new HtmlBody();
		rootChildren.add(body);
		
		HtmlForm form = new HtmlForm();
		body.getChildren().add(form);
		
		ValueExpression messageProperty = expressionFactory.createValueExpression(elContext, "#{myBean.message}", String.class);
		
		HtmlOutputText message = new HtmlOutputText();
		message.setValueExpression("value", messageProperty);
		form.getChildren().add(message);

		MethodExpression helloMethod = expressionFactory.createMethodExpression(elContext, "#{myBean.action}", Void.class, new Class[0]);
		
		HtmlCommandButton hiCommand = new HtmlCommandButton();
		hiCommand.setActionExpression(helloMethod);
		hiCommand.setValue("Say hello");
		form.getChildren().add(hiCommand);
		
		MethodExpression navigateMethod = expressionFactory.createMethodExpression(elContext, "#{myBean.navigate}", String.class, new Class[0]);
		
		HtmlCommandButton navigateCommand = new HtmlCommandButton();
		navigateCommand.setActionExpression(navigateMethod);
		navigateCommand.setValue("Do navigation");
		form.getChildren().add(navigateCommand);
				
		output = new UIOutput();
		output.setValue("</html>");
		rootChildren.add(output);
	}
	
}
