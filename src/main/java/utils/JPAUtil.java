package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JPAUtil {
    private static final Logger LOGGER = Logger.getLogger(JPAUtil.class.getName());
    private static volatile EntityManagerFactory emf;
    
    static {
        initializeFactory();
    }
    
    private static void initializeFactory() {
        try {
            LOGGER.info("Initializing EntityManagerFactory...");
            
            // Thêm các properties để debug
            Map<String, String> properties = new HashMap<>();
            properties.put("hibernate.show_sql", "true");
            properties.put("hibernate.format_sql", "true");
            
            emf = Persistence.createEntityManagerFactory("TTastyShop", properties);
            LOGGER.info("EntityManagerFactory initialized successfully");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error initializing EntityManagerFactory", e);
            if (e.getCause() != null) {
                LOGGER.log(Level.SEVERE, "Root cause: ", e.getCause());
            }
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManager getEntityManager() {
        if (emf == null) {
            synchronized (JPAUtil.class) {
                if (emf == null) {
                    initializeFactory();
                }
            }
        }
        try {
            return emf.createEntityManager();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating EntityManager", e);
            throw new RuntimeException("Could not create EntityManager", e);
        }
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
