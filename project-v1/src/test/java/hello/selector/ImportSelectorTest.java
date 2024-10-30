package hello.selector;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

public class ImportSelectorTest {

    @Test
    void staticConfig() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(StaticConfig.class);
        HelloBean bean1 = ac.getBean(HelloBean.class);
        WorldBean bean2 = ac.getBean(WorldBean.class);

        assertNotNull(bean1);
        assertNotNull(bean2);

        assertEquals(bean1.getClass().getSimpleName(),"HelloBean");
        assertEquals(bean2.getClass().getSimpleName(),"WorldBean");
    }

    @Test
    void selectorConfig(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SelectorConfig.class);
        HelloBean bean = ac.getBean(HelloBean.class);

        assertNotNull(bean);
        assertEquals(bean.getClass().getSimpleName(),"HelloBean");

        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean(WorldBean.class));
    }

    @TestConfiguration
    @Import({HelloConfig.class, WorldConfig.class})
    static class StaticConfig {

    }

    @TestConfiguration
    @Import(BeanImportSelector.class)
    static class SelectorConfig{

    }
}
