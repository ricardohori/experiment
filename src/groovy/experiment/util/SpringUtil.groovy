package experiment.util

import org.springframework.context.ApplicationContext

public class SpringUtil {

    public static ApplicationContext getCtx() {
        return getApplicationContext();
    }

    public static ApplicationContext getApplicationContext() {
        return (ApplicationContext) org.codehaus.groovy.grails.commons.ApplicationHolder.application.parentContext
    }

    public static <T> T getBean(String beanName) {
        return (T) getApplicationContext().getBean(beanName);
    }
}