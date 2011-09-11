package javavdl.impl;

import javax.faces.view.ViewDeclarationLanguage;
import javax.faces.view.ViewDeclarationLanguageFactory;

public class JavaViewDeclarationLanguageFactory extends ViewDeclarationLanguageFactory {

	private ViewDeclarationLanguageFactory wrapped;

	public JavaViewDeclarationLanguageFactory(ViewDeclarationLanguageFactory wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ViewDeclarationLanguage getViewDeclarationLanguage(String viewId) {

		ViewDeclarationLanguage parentVDL = getWrapped().getViewDeclarationLanguage(viewId);

		return new JavaViewDeclarationLanguage(parentVDL);
	}

	@Override
	public ViewDeclarationLanguageFactory getWrapped() {
		return wrapped;
	}

}
