package com.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author qinkai
 * 线程池工具类，保证全局使用一个相同的线程池，方便控制线程的创建和销毁
 * 2019年1月23日
 */
public final class ThreadPoolUtil {

    private static Logger logger = LogManager.getLogger(ThreadPoolUtil.class.getName());
    private static ExecutorService threadPool;

    public static void init() {
        logger.info("<<<<<<<<<<<<<<程序启动, 正在初使化线程池>>>>>>>>>>>>");
        threadPool = Executors.newFixedThreadPool(20);
        logger.info("<<<<<<<<<<<<<<程序启动, 线程池初使化完成>>>>>>>>>>>>");
    }

    public static void destroy() {
        threadPool.shutdown();
        logger.info("<<<<<<<<<<<<<<程序结束, 关闭线程池完成>>>>>>>>>>>>");
    }

    public static ExecutorService getThreadPool() {
        if (threadPool == null) {
            throw new IllegalArgumentException("线程池尚未初始化");
        }
        return threadPool;
    }
    
    public static void submit(Runnable runnable){
        getThreadPool().submit(runnable);
    }
}
