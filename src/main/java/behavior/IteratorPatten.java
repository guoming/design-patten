package behavior;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 迭代器模式
 * @author guoming
 */
public class IteratorPatten {

    public static void main(String[] args) {

        CarCollection carCollection=new CarCollection();
        carCollection.Add(new Car("Car1","Red"));
        carCollection.Add(new Car("Car1","Blue"));
        carCollection.Add(new Car("Car1","Green"));


        Iterator<Car> iterator = carCollection.getIterator();
        while(iterator.hasNext())
        {
            Car next = iterator.next();
            System.out.println(next.toString());
        }
    }

    public interface Iterator<T> {
        boolean hasNext();

        T next();
    }

    public interface  ICollection<T>
    {
        Iterator<T> getIterator();
    }

    public static class AscIterator<T> implements Iterator<T> {

        List<T> list;

        public AscIterator(List<T> list)
        {
            this.list=list;
        }

        int index=0;

        @Override
        public boolean hasNext() {

            return index< list.size();
        }

        @Override
        public T next() {
            if (hasNext()) {
                return list.get(index++);
            } else {
                return null;
            }
        }
    }


    public static class Car
    {
        private String Name;
        private String Color;

        public Car(String Name, String Color)
        {
            this.Name=Name;
            this.Color=Color;
        }

        @Override
        public String toString() {
            return "name="+Name+",Color="+Color;
        }
    }


    public static class CarCollection implements  ICollection<Car>
    {

       private final List<Car> carList=new ArrayList<>();

        public void Add(Car car)
        {
            this.carList.add(car);
        }

        @Override
        public Iterator<Car> getIterator() {
           return new AscIterator<Car>(carList);
        }


    }
}
