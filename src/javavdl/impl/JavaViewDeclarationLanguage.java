package javavdl.impl;

import static java.lang.Boolean.TRUE;
import static javax.faces.application.StateManager.IS_BUILDING_INITIAL_STATE;

import java.io.IOException;
import java.util.Map;

import javavdl.view.Page;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PostAddToViewEvent;
import javax.faces.view.ViewDeclarationLanguage;
import javax.faces.view.ViewMetadata;

import com.sun.faces.context.StateContext;

public class JavaViewDeclarationLanguage extends ViewDeclarationLanguageWrapper {

	ViewDeclarationLanguage wrapped;
	
	public JavaViewDeclarationLanguage(ViewDeclarationLanguage wrapped) {
		this.wrapped = wrapped;
	}
	
	@Override
	public void buildView(FacesContext context, UIViewRoot root) throws IOException {
		
		if (PageFactory.pageExists(root.getViewId())) {
			buildViewFromJava(context, root);
		} else {				
			super.buildView(context, root);
		}
	}
	
	private void buildViewFromJava(FacesContext context, UIViewRoot root) throws IOException {
		
		Map<Object, Object> contextAttributes = context.getAttributes();
		
		if (contextAttributes.containsKey(root)) {
            StateContext stateCtx = StateContext.getStateContext(context);
            // Disable events from being intercepted by the StateContext by
            // virtue of re-applying the handlers. 
            try {
                stateCtx.setTrackViewModifications(false);
                // testBuild(context, root);
            } finally {
                stateCtx.setTrackViewModifications(true);
            }
            return;
        }

		root.setViewId(root.getViewId());

        // Populate UIViewRoot
        try {
        	contextAttributes.put(IS_BUILDING_INITIAL_STATE, TRUE);
        	
        	Page page = PageFactory.findPage(root.getViewId());
        	page.buildView(context, root);
        	
        	Utils.assignIDRecursively(context, root);
        	StateContext.getStateContext(context).startTrackViewModifications();		            
        } finally {
        	contextAttributes.remove(IS_BUILDING_INITIAL_STATE);
        }
        
		context.getApplication().publishEvent(context, PostAddToViewEvent.class, UIViewRoot.class, root);
        Utils.markInitialStateRecursively(context, root);
        contextAttributes.put(root, TRUE);		        
	}

	@Override
	public ViewMetadata getViewMetadata(FacesContext context, String viewId) {
		if (PageFactory.pageExists(viewId)) {
			return new ViewMetadataImpl(viewId);
		}
		
		return super.getViewMetadata(context, viewId);
	}

	@Override
	public boolean viewExists(FacesContext context, String viewId) {

		if (PageFactory.pageExists(viewId)) {
			return true;
		}

		return super.viewExists(context, viewId);
	}
	
	@Override
	public ViewDeclarationLanguage getWrapped() {
		return wrapped;
	}
	
}
