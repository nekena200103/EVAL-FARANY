package annotation;

import annotation.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
public @interface Annotationdebase {
	
	public boolean isprimarykey();
	public String nomtable();
	public String nomdelacolonne();
    public boolean reconnaissance();

	

}
