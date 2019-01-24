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

import com.common.ThreadPoolUtil;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;



/**
 * @author qinkai
 * @date 2018年12月7日
 * com.mysql.jdbc.Driver] but failed to unregister it when the web application was stopped
 * 解决tomcat 关闭时，数据库连接池警告
 */
@Component
public class MySpecialListener implements ServletContextListener{
    
    private static final Logger logger = LoggerFactory.getLogger(MySpecialListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("****************Servlet Context Start******************");
        ThreadPoolUtil.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ThreadPoolUtil.destroy();
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
