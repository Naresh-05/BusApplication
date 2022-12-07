package com.log;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogFile {
	    private static Logger logger=Logger.getLogger("MyLog");
	    static
	    {
	        FileHandler fh;
	        try {
	            fh=new FileHandler("C:\\Users\\naresh-pt6090\\BusApplication\\BusReservationApplication\\src\\main\\webapp\\logFiles\\"+"BusApplication.log",true);
	            logger.addHandler(fh);
	            logger.setUseParentHandlers(false);
	            SimpleFormatter formatter= new SimpleFormatter();
	            fh.setFormatter(formatter);
	            logger.info("Logger Initialized");
	        } catch (Exception e) {
	            logger.log(Level.WARNING,"Exception :: ",e);
	        }
	    }
	    public static void writeToLog(String msg)
	    {
	        logger.info(msg);
	    }
	    public static void writeToLog(String msg,Exception exp)
	    {
	        logger.log(Level.WARNING,msg,exp);
	    }

}