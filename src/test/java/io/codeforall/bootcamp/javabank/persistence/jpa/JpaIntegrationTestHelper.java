package io.codeforall.bootcamp.javabank.persistence.jpa;

import io.codeforall.bootcamp.javabank.Config;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class JpaIntegrationTestHelper {

    protected EntityManagerFactory emf;
    protected EntityManager em;
    private GenericXmlApplicationContext ctx;

    @Before
    public void init() {

        ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles("test");
        ctx.load(Config.SPRING_CONFIG);
        ctx.refresh();

        emf = ctx.getBean(EntityManagerFactory.class);
        em = emf.createEntityManager();

    }

    @After
    public void tearDown() {

        if (em != null) {
            em.clear();
            em.close();
        }

        if (emf != null) {
            emf.close();
        }

        ctx.destroy();
    }
}
