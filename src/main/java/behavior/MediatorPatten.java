package behavior;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author guoming
 * 中介者模式
 */
public class MediatorPatten {


    public static void main(String[] args) throws ClassNotFoundException {

        IMediator mediator=new Mediator();
        mediator.registerHandler(new OneCommandHandler());
        mediator.registerHandler(new TwoCommandHandler());

        String ret1 = mediator.send(new OneCommand("1"));
        Boolean ret2 = mediator.send(new TwoCommand("2"));

    }

    /**
     * 请求参数
     */
    public static interface ICommandRequest extends Serializable
    {

    }

    /**
     * @param <TIn> 请求参数
     * @param <TOut> 请求返回
     */
    public static interface  ICommandHandler<TIn extends ICommandRequest,TOut>
    {
        /**
         * 处理方法
         * @param request 请求参数
         * @return 请求返回
         */
        TOut handler(TIn request);
    }


    /**
     * 中介者接口
     */
    public interface IMediator {

        /**
         * 注册处理程序
         * @param handler  命令处理程序
         * @throws ClassNotFoundException
         */
       void registerHandler(ICommandHandler<? extends ICommandRequest,?> handler) throws ClassNotFoundException;


        /**
         * 发送请求
         * @param request 请求参数
         * @param <TOut> 参数类型
         * @return 请求返回
         */
       <TOut> TOut send(ICommandRequest request);
    }


    public static class Mediator implements IMediator {

       private final Map<Class<? extends ICommandRequest>,ICommandHandler<? extends ICommandRequest,?>> handlers=new HashMap<>();

        @Override
        public <TCommandResponse> TCommandResponse send(ICommandRequest command) {

            ICommandHandler commandHandler = handlers.get(command.getClass());
            TCommandResponse response = (TCommandResponse)commandHandler.handler(command);
            return response;
        }

        @Override
        public void registerHandler(ICommandHandler<? extends ICommandRequest, ?> handler) throws ClassNotFoundException {

            Class<? extends ICommandHandler> handlerClass = handler.getClass();

            Type[] genericInterfaces = handlerClass.getGenericInterfaces();

            for (Type genericInterface : genericInterfaces) {

                Type[] actualTypeArguments = ((ParameterizedType) genericInterface).getActualTypeArguments();

                Class<? extends ICommandRequest> aClass = (Class<? extends ICommandRequest>) Class.forName(actualTypeArguments[0].getTypeName());

                handlers.put(aClass,handler);

            }
        }
    }

    public static class OneCommand implements ICommandRequest
    {
        public String id;

        public  OneCommand(String id)
        {
            this.id=id;
        }

        @Override
        public String toString() {
            return "id="+id;
        }
    }

    public static class TwoCommand implements ICommandRequest
    {


        public String id;

        public  TwoCommand(String id)
        {
            this.id=id;
        }

        @Override
        public String toString() {
            return "id="+id;
        }
    }

    public static class OneCommandHandler implements ICommandHandler<OneCommand,String>
    {
        @Override
        public String handler(OneCommand request) {
            System.out.println("OneCommandHandler:"+request.toString());
            return "1234";
        }

    }

    public static class TwoCommandHandler implements ICommandHandler<TwoCommand,Boolean>
    {
        @Override
        public Boolean handler(TwoCommand request) {
            System.out.println("TwoCommandHandler:"+request.toString());
            return false;
        }

    }
}
