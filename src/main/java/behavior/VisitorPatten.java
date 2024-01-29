package behavior;

import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式
 * @author guoming
 */
public class VisitorPatten {
    public static void main(String[] args) {

        List<IShape> shapes=new ArrayList<>();
        shapes.add(new Circle());
        shapes.add(new Rectangle());

        IVisitor drawVisitor = new ShapeDrawVisitor();
        IVisitor resizeVisitor =new ShapeResizeVisitor();

        for (IShape shape : shapes) {
           shape.accept(drawVisitor);
           shape.accept(resizeVisitor);
        }
    }

    public interface  IVisitor
    {
        /**
         * 访问圆形
         * @param circle
         */
        void visit(Circle circle);


        /**
         * 访问矩形
         * @param rectangle
         */
        void visit(Rectangle rectangle);
    }

    /**
     * 形状
     */
    public interface  IShape {



        void accept(IVisitor visitor);
    }

    /**
     * 圆形
     */
    public static class Circle implements IShape
    {
        /**
         * X轴位置
         */
        Integer X;

        /**
         * Y轴位置
         */
        Integer Y;

        /**
         * 半径
         */
        private Integer radius;

        @Override
        public void accept(IVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * 矩形
     */
    public static class Rectangle implements IShape {

        /**
         * X轴位置
         */
        Integer X;

        /**
         * Y轴位置
         */
        Integer Y;

        /**
         * 长度
         */
        private Integer Length;

        /**
         * 宽度
         */
        private Integer Width;

        @Override
        public void accept(IVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * 画图
     */
    public static class ShapeDrawVisitor implements IVisitor
    {

        @Override
        public void visit(Circle circle) {
            System.out.println("Circle Draw");
        }

        @Override
        public void visit(Rectangle rectangle) {
            System.out.println("Rectangle Draw");

        }
    }

    public static class ShapeResizeVisitor implements IVisitor
    {

        @Override
        public void visit(Circle circle) {
            System.out.println("Circle Resize");

        }

        @Override
        public void visit(Rectangle rectangle) {
            System.out.println("Rectangle Resize");
        }
    }

}
