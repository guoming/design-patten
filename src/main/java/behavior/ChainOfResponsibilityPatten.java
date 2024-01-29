package behavior;

/**
 * 责任链模式
 * @author guoming
 */
public class ChainOfResponsibilityPatten {

    public static void main(String[] args) {

        Logger logger=new Logger();
        logger.debug("1");
        logger.info("2");
        logger.warn("3");
        logger.error("4");
    }

    public static class Logger
    {

        private ILogAppender logAppender;

        public Logger() {

            ILogAppender debugLogger = new DebugLogAppender();
            ILogAppender infoLogger = new InfoLogAppender();
            ILogAppender warnLogAppender = new WarnLogAppender();

            debugLogger.setNext(infoLogger);
            infoLogger.setNext(warnLogAppender);
            logAppender = debugLogger;
        }

        public void debug(String message)
        {
            logAppender.log(LoggerType.DEBUG,message);
        }

        public void info(String message)
        {
            logAppender.log(LoggerType.INFO,message);
        }

        public void warn(String message)
        {
            logAppender.log(LoggerType.WARN,message);
        }

        public void error(String message)
        {
            logAppender.log(LoggerType.ERROR,message);
        }
    }

    public static enum LoggerType
    {
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    public interface ILogAppender
    {
        void setNext(ILogAppender logger);
        void log(LoggerType type,String mesage);
        void write(String message);
    }

    public static abstract class AbstractLogAppender implements ILogAppender
    {
        LoggerType loggerType;
        private ILogAppender nextLogAppender;

        public AbstractLogAppender(LoggerType loggerType)
        {
            this.loggerType=loggerType;
        }

        public void setNext(ILogAppender logger)
        {
            this.nextLogAppender=logger;
        }

        public void log(LoggerType type,String mesage)
        {
            if(type== loggerType) {
                write(mesage);
            }

            if(nextLogAppender!=null)
            {
                nextLogAppender.log(type,mesage);
            }
        }

        public abstract void write(String message);
    }

    public static class DebugLogAppender
        extends AbstractLogAppender
        implements ILogAppender
    {

        public DebugLogAppender()
        {
            super(LoggerType.DEBUG);
        }

        @Override
        public void write(String message) {
            System.out.println("DEBUG: "+message);
        }
    }

    public static class InfoLogAppender
            extends AbstractLogAppender
            implements ILogAppender
    {
        public InfoLogAppender()
        {
            super(LoggerType.INFO);
        }

        @Override
        public void write(String message) {
            System.out.println("INFO: "+message);
        }
    }

    public static class WarnLogAppender
            extends AbstractLogAppender
            implements ILogAppender
    {
        public WarnLogAppender()
        {
            super(LoggerType.WARN);
        }

        @Override
        public void write(String message) {
            System.out.println("WARN: "+message);
        }
    }
}
