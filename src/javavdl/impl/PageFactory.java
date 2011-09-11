package javavdl.impl;

import static java.util.Locale.ENGLISH;
import javavdl.view.Page;

import javax.faces.FacesException;

public class PageFactory {

	public static final String BASE_PATH = "resources.javavdl.pages.";
	
	public static Page findPage(String uri) {
		
		try {
		
			String className = uri;
			if (uri.startsWith("/")) {
				className = uri.substring(1);
			}
			
			// Remove suffix
			className = className.substring(0, className.lastIndexOf('.'));
			String path = "";
			if (className.contains("/")) {
				String classNamePure = className.substring(className.lastIndexOf('/') + 1);
				path = className.substring(0, className.lastIndexOf('/')).replace('/', '.') + ".";
				className = classNamePure;
			}
			
			String capitalizedFirstLeter = className.substring(0, 1).toUpperCase(ENGLISH);
			if (className.length() > 1) {
				className = capitalizedFirstLeter + className.substring(1);
			} else {
				className = capitalizedFirstLeter;
			}
			
			return (Page) Class.forName(BASE_PATH + path + className, false, Thread.currentThread().getContextClassLoader()).newInstance();
		}
		catch (Exception e) {
			 throw new FacesException(e);
		}		
	}
	
	public static boolean pageExists(String uri) {
		try {
			findPage(uri);
			return true;
		} catch (Exception e) {
			return false;
		}		
	}
	
	public static String redirectOutcome(Class<?> targetClass) {
		String fullPath = targetClass.getName();
		if (fullPath.startsWith(BASE_PATH)) {
			String path = fullPath.substring(BASE_PATH.length()).replace('.', '/');
			return path + "?faces-redirect=true";
		}
		
		throw new IllegalArgumentException(String.format("%s is not in path %s", targetClass, BASE_PATH));
	}
	
}
