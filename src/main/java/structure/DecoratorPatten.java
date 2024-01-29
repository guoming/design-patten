package structure;

/**
 * 装饰器模式
 * @author guoming
 */
public class DecoratorPatten {
    public static void main(String[] args) {

        ICoffee sampleCoffee=new SampleCoffee();
        //牛奶咖啡
        ICoffee milkCoffee=new MilkCoffeeDecorator(sampleCoffee);
        //加糖咖啡
        SugarCoffeeDecorator sugarCoffee = new SugarCoffeeDecorator(sampleCoffee);
        //巧克力咖啡
        ChocolateCoffeeDecorator chocolateCoffeeDecorator = new ChocolateCoffeeDecorator(sampleCoffee);
        //牛奶加糖
        SugarCoffeeDecorator sugarMilkCoffee = new SugarCoffeeDecorator(milkCoffee);

        //巧克力+糖+牛奶
        ChocolateCoffeeDecorator chocolateSugarMilkCoffeeDecorator = new ChocolateCoffeeDecorator(sugarMilkCoffee);


        System.out.println("原味咖啡，价格="+sampleCoffee.getCost());
        System.out.println("牛奶咖啡，价格="+milkCoffee.getCost());
        System.out.println("加糖咖啡，价格="+sugarCoffee.getCost());
        System.out.println("巧克力咖啡，价格="+chocolateCoffeeDecorator.getCost());
        System.out.println("牛奶加糖咖啡，价格="+sugarMilkCoffee.getCost());
        System.out.println("巧克力+牛奶+加糖咖啡，价格="+chocolateSugarMilkCoffeeDecorator.getCost());

    }

    public interface ICoffee {


        /**
         * 获取成本
         *
         * @return 成本
         */
        Double getCost();
    }


    /**
     * 原味咖啡
     */
    public static class SampleCoffee implements ICoffee
    {

        @Override
        public Double getCost() {
            return 5.0;
        }
    }

    public static class CoffeeDecorator implements ICoffee
    {
        private final ICoffee coffee;

        public  CoffeeDecorator(ICoffee coffee)
        {
            this.coffee = coffee;
        }

        @Override
        public Double getCost() {
            return coffee.getCost();
        }
    }

    /**
     * 牛奶咖啡
     */
    public static  class MilkCoffeeDecorator extends CoffeeDecorator
            implements ICoffee
    {

        public MilkCoffeeDecorator(ICoffee coffee) {
            super(coffee);
        }

        @Override
        public Double getCost() {
            return super.getCost() +1;
        }
    }

    /**
     * 加糖咖啡
     */
    public static  class SugarCoffeeDecorator extends CoffeeDecorator
            implements ICoffee
    {

        public SugarCoffeeDecorator(ICoffee coffee) {
            super(coffee);
        }

        @Override
        public Double getCost() {
            return super.getCost() +0.5;
        }
    }


    /**
     * 巧克力咖啡
     */
    public static class ChocolateCoffeeDecorator
            extends CoffeeDecorator
            implements ICoffee
    {

        public ChocolateCoffeeDecorator(ICoffee coffee) {
            super(coffee);
        }


        @Override
        public Double getCost() {
            return super.getCost()+ 5;
        }
    }
}
