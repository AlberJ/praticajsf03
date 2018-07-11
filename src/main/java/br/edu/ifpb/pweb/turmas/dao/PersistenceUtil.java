package br.edu.ifpb.pweb.turmas.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jboss.logging.Logger;


public class PersistenceUtil {

    private static EntityManagerFactory emf;
    private static ManagedEMContext emc;
	private static Logger logger = Logger.getLogger(PersistenceUtil.class);

    static {
        try {
    		emf = Persistence.createEntityManagerFactory("praticajsf03");
    		emc = new ManagedEMContext(emf);
            logger.info("F�brica de EntityManagers constru�da.");
        } catch (Throwable ex) {
            logger.error("Falha ao criar EntityManagerFactory.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    
    public static EntityManager getCurrentEntityManager() {
    	return emc.currentEntityManager();
    }
    
    public static EntityManager getEntityManager() {
    	return emf.createEntityManager();
    }
    

}