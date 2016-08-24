package dbr;

import murex.test.rita.books.model.BOOK;
import sun.reflect.annotation.AnnotationType;

import javax.inject.Qualifier;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import static java.lang.annotation.ElementType.*;

/**
 * Created by RCPopescu on 8/23/2016.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, PARAMETER })
public @interface ClassBinding {
    Class<?> value() ;
}
