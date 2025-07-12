package utils;

import dao.UserDAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class DatabaseInitializer implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(DatabaseInitializer.class.getName());
    private ExecutorService executorService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Initializing database connections...");
        executorService = Executors.newSingleThreadExecutor();
        
        executorService.submit(() -> {
            try {
                // Warm up connection pool
                UserDAO userDAO = new UserDAO();
                userDAO.getAll(); // Thực hiện một truy vấn đơn giản để khởi tạo connection
                
                LOGGER.info("Database connection pool initialized successfully");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error initializing database connection pool", e);
            }
        });
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (executorService != null) {
            executorService.shutdown();
        }
    }
} 