package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JPAUtil {
    private static final Logger LOGGER = Logger.getLogger(JPAUtil.class.getName());
    private static volatile EntityManagerFactory emf;
    
    static {
        try {
            LOGGER.info("Initializing EntityManagerFactory...");
            emf = Persistence.createEntityManagerFactory("TTastyShop");
            LOGGER.info("EntityManagerFactory initialized successfully");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error initializing EntityManagerFactory", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManager getEntityManager() {
        if (emf == null) {
            synchronized (JPAUtil.class) {
                if (emf == null) {
                    try {
                        LOGGER.info("Re-initializing EntityManagerFactory...");
                        emf = Persistence.createEntityManagerFactory("TTastyShop");
                        LOGGER.info("EntityManagerFactory re-initialized successfully");
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "Error re-initializing EntityManagerFactory", e);
                        throw new RuntimeException("Could not initialize EntityManagerFactory", e);
                    }
                }
            }
        }
        return emf.createEntityManager();
    }
    
    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            try {
                emf.close();
                LOGGER.info("EntityManagerFactory closed successfully");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error closing EntityManagerFactory", e);
            }
        }
    }
}
