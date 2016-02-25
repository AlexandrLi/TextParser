package com.epam.alexandrli.textparser.factory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewLoggerFactory {

    public static Logger createLoggerWithConfigFromFile(Class clazz, String fileName) {
        try {
            getLoggerConfig(fileName);
        } catch (JoranException e) {
            e.printStackTrace();
        }
        return LoggerFactory.getLogger(clazz);
    }

    private static void getLoggerConfig(String fileName) throws JoranException {
        LoggerContext context = (LoggerContext) org.slf4j.LoggerFactory.getILoggerFactory();
        JoranConfigurator jc = new JoranConfigurator();
        jc.setContext(context);
        context.reset();
        jc.doConfigure(NewLoggerFactory.class.getClassLoader().getResourceAsStream("logger/" + fileName));
    }
}
