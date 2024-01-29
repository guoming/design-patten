package structure;

/**
 * 桥接模式
 * @author guoming
 */
public class BridgePatten {

    public static void main(String[] args) {

        IColor redColor=new RedColor();
        IColor blueColor= new BlueColor();

        IBag handBag=new HandBag();
        IBag wallet=new Wallet();

        handBag.setColor(redColor);
        handBag.setName("A1 手提包");
        System.out.println(handBag.toString());

        handBag.setColor(blueColor);
        handBag.setName("A2 手提包");
        System.out.println(handBag.toString());

        wallet.setColor(redColor);
        wallet.setName("B1 钱包");
        System.out.println(wallet.toString());

        wallet.setColor(blueColor);
        wallet.setName("B2 钱包");
        System.out.println(wallet.toString());

    }

    /**
     * 颜色接口
     */
    public interface IColor
    {
        /**
         * 获取颜色名称
         * @return 颜色名称
         */
        String getName();
    }

    /**
     * 包接口
     */
    public interface IBag
    {
        /**
         * 设置颜色
         * @param color 颜色
         */
        void setColor(IColor color);

        /**
         * 设置名称
         * @param name 名称
         */
        void setName(String name);
    }

    public static class Bag implements IBag
    {
        IColor color;
        String name;

        @Override
        public void setColor(IColor color) {
            this.color=color;

        }

        @Override
        public void setName(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            return "包名称="+name+"，颜色="+color.getName();
        }
    }

    /**
     * 红色
     */
    public static class RedColor implements IColor
    {


        @Override
        public String getName() {
            return "红色";
        }
    }

    /**
     * 蓝色
     */
    public static class BlueColor implements IColor
    {

        @Override
        public String getName() {
            return "蓝色";
        }
    }

    /**
     * 挎包
     */
    public static class HandBag
            extends Bag
            implements IBag
    {

    }

    /**
     * 钱包
     */
    public static class Wallet
            extends Bag
            implements IBag
    {

    }

}
