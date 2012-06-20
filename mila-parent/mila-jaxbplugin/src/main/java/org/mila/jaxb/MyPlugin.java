package org.mila.jaxb;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JMethod;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;

public class MyPlugin extends Plugin {

    @Override
    public String getOptionName() {
	return "Xmyplugin";
    }

    @Override
    public String getUsage() {
	return "  -Xmyplugin      :  do some mila specific fixes";
    }

    @Override
    public boolean run(Outline model, Options arg1, ErrorHandler arg2)
	    throws SAXException {
	try {
	    for (ClassOutline co : model.getClasses()) {
		for (JMethod method : co.ref.methods()) {
		    int transientCount = 0;
		    ArrayList<JAnnotationUse> toRemove = new ArrayList<JAnnotationUse>();
		    for (JAnnotationUse anno : method.annotations()) {
			if (anno.getAnnotationClass().toString()
				.contains("Transient")) {
			    transientCount++;
			    toRemove.add(anno);
			}
		    }
		    if (transientCount <= 1)
			continue;

		    Field f = method.getClass().getDeclaredField("annotations");
		    f.setAccessible(true);

		    @SuppressWarnings("unchecked")
		    Collection<JAnnotationUse> annos = (Collection<JAnnotationUse>) f
			    .get(method);
		    annos.removeAll(toRemove);

		}

	    }
	} catch (SecurityException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (NoSuchFieldException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IllegalArgumentException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return true;
    }
}
