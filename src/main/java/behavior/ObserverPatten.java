package behavior;

import javax.security.auth.Subject;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guoming
 * 观察者模式
 */
public class ObserverPatten {

    public static void main(String[] args) {

        ISubject<String> newsSubject=new NewsSubject();
        newsSubject.addObserver(new OneObserver());
        newsSubject.addObserver(new TwoObserver());
        newsSubject.notify("message1");
        newsSubject.notify("message2");

    }


    public static interface IObserver<T>
    {
        void update(T message);
    }

    public static interface ISubject<T>
    {

        /**
         * @param observer
         * 添加观察者
         */
        void addObserver(IObserver<T> observer);


        /**
         * @param observer
         * 移除观察者
         */
        void removeObserver(IObserver<T> observer);

        void notify(T message);
    }

    public static class OneObserver implements IObserver<String>
    {

        @Override
        public void update(String message) {

            System.out.println("OneObserver:"+message);
        }
    }

    public static class TwoObserver implements IObserver<String>
    {
        @Override
        public void update(String message) {

            System.out.println("TwoObserver:"+message);
        }
    }

    public static class NewsSubject implements  ISubject<String>
    {

        List<IObserver<String>> observers=new ArrayList<>();

        @Override
        public void addObserver(IObserver<String> observer) {
            observers.add(observer);

        }

        @Override
        public void removeObserver(IObserver<String> observer) {
                observers.remove(observer);
        }

        @Override
        public void notify(String message) {
            for (IObserver<String> observer : observers) {
                observer.update(message);
            }
        }
    }


}
