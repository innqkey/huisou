package com.huisou.config;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;



/**
 * @author qinkai
 * @date 2018年12月7日
 */
@Component
public class MySpecialListener implements ServletContextListener{
    
    private static final Logger logger = LoggerFactory.getLogger(MySpecialListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("****************Servlet Context Start******************");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("****************Servlet Context Shutdown******************");
        logger.info("****************Destroying Context..............................................");
        try {
            logger.info("****************Calling MySQL AbandonedConnectionCleanupThread checkedShutdown");
            AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException e) {
            logger.error("****************Error calling MySQL AbandonedConnectionCleanupThread shutdown {}", e);
        }

        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();

            if (driver.getClass().getClassLoader() == cl) {
                try {
                    logger.info("**********************Deregistering JDBC driver {}", driver);
                    DriverManager.deregisterDriver(driver);

                } catch (SQLException ex) {
                    logger.error("******************Error deregistering JDBC driver {}", driver);
                }
            } else {
                logger.trace("*******************Not deregistering JDBC driver {} as it does not belong to this webapp's ClassLoader", driver);
            }
        }
    }

}
