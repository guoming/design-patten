package behavior;

import java.math.BigDecimal;

/**
 * 策略模式
 * @author guoming
 */
public class StrategyPattern {
    public static void main(String[] args) {
        Context context = new Context();
        context.setStrategy(new GradeAPriceStrategy());
        System.out.println(context.getDiscount(100.0));

        context.setStrategy(new GradeBPriceStrategy());
        System.out.println(context.getDiscount(100.0));

        context.setStrategy(new GradeCPriceStrategy());
        System.out.println(context.getDiscount(100.0));
    }

    public static interface IPriceStrategy
    {
        /**
         * 获取价格
         * @return 价格
         */
        Double getDiscount(Double price);

    }

    public static class Context
    {
        IPriceStrategy strategy;
        public void setStrategy(IPriceStrategy strategy)
        {
            this.strategy=strategy;
        }

        public Double getDiscount(Double price)
        {
            return strategy.getDiscount(price);
        }
    }

    public static class GradeAPriceStrategy implements IPriceStrategy
    {

        @Override
        public Double getDiscount(Double price) {
            return price * 0.8;
        }
    }

    public static class GradeBPriceStrategy implements IPriceStrategy
    {

        @Override
        public Double getDiscount(Double price) {
            return price * 0.8;
        }
    }

    public static class GradeCPriceStrategy implements IPriceStrategy
    {

        @Override
        public Double getDiscount(Double price) {
           return price * 0.5;
        }
    }
}
