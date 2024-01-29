package behavior;

/**
 * 状态模式
 * @author guoming
 */
public class StatePatten {

    public static void main(String[] args) {
        Order order = new Order();
        order.cancel();


        Order order2 = new Order();
        order2.payment();
        order2.delivery();
        order2.sign();
        order2.evaluation();
        order2.done();

    }



    /**
     * 订单状态
     */
    public static interface IOrderState {
        /**
         * 取消
         */
        void cancel();

        /**
         * 支付
         */
        void payment();


        /**
         * 发货
         */
        void delivery();


        /**
         * 签收
         */
        void sign();


        /**
         * 评价
         */
        void evaluation();

        /**
         * 完成
         */
        void done();

    }

    /**
     * 订单
     */
    public static class Order {
        IOrderState state;

        //待支付
        IOrderState pendingOrderState = new UnPaymentOrderState(this);
        //待发货
        IOrderState unDeliveryOrderState = new UnDeliveryOrderState(this);

        //待收货
        IOrderState unReceivedOrderState = new UnReceivedOrderState(this);
        //待评价
        IOrderState unEevaluatedOrderState = new UnEevaluatedOrderState(this);
        //已完成
        IOrderState doneOrderState = new DoneOrderState(this);
        //已取消
        IOrderState canceedOrderState = new CanceledOrderState(this);


        public Order() {
            this.state = pendingOrderState;
        }


        public IOrderState getOrderState() {
            return state;
        }

        public void setOrderState(IOrderState state) {
            this.state = state;
        }


        public void cancel() {

            this.state.cancel();

        }

        public void payment() {
            this.state.payment();
        }

        public void delivery() {
            this.state.delivery();
        }

        public void sign() {
            this.state.sign();
        }

        public void evaluation() {
            this.state.evaluation();
        }

        public void done() {
            this.state.done();
        }

    }


    /**
     * 订单状态抽象类
     */
    public static abstract class OrderState implements IOrderState {
        Order order;

        public OrderState(Order order) {
            this.order = order;
        }

    }


    /**
     * 待付款状态
     */
    public static class UnPaymentOrderState
            extends OrderState
            implements IOrderState {

        public UnPaymentOrderState(Order order) {
            super(order);
        }

        @Override
        public void cancel() {
            System.out.println("成功：订单取消成功");
            this.order.setOrderState(this.order.canceedOrderState);
        }

        @Override
        public void payment() {
            System.out.println("成功：订单付款成功");
            this.order.setOrderState(this.order.unDeliveryOrderState);
        }

        @Override
        public void delivery() {
            System.out.println("失败：订单未付款");
        }

        @Override
        public void sign() {
            System.out.println("失败：订单未发货");
        }

        @Override
        public void evaluation() {
            System.out.println("失败：订单未收货");
        }

        @Override
        public void done() {
            System.out.println("失败：订单未评价");
        }
    }

    /**
     * 待发货
     */
    public static class UnDeliveryOrderState
            extends OrderState
            implements IOrderState {

        public UnDeliveryOrderState(Order order) {
            super(order);
        }

        @Override
        public void cancel() {
            System.out.println("失败：订单已发货");
        }

        @Override
        public void payment() {
            System.out.println("失败：无需重复付款");
        }

        @Override
        public void delivery() {
            System.out.println("成功：发货成功");
            this.order.setOrderState(order.unReceivedOrderState);
        }

        @Override
        public void sign() {
            System.out.println("失败：订单未发货");
        }

        @Override
        public void evaluation() {
            System.out.println("失败：订单未收货");
        }

        @Override
        public void done() {
            System.out.println("失败：订单未评价");
        }

    }

    /**
     * 待收货
     */
    public static class UnReceivedOrderState
            extends OrderState
            implements IOrderState {

        public UnReceivedOrderState(Order order) {
            super(order);
        }

        @Override
        public void cancel() {
            System.out.println("失败：订单已发货");
        }

        @Override
        public void payment() {
            System.out.println("失败：无需重复付款");
        }

        @Override
        public void delivery() {
            System.out.println("失败：无需重复发货");
        }

        @Override
        public void sign() {
            System.out.println("成功：签收成功");
            this.order.setOrderState(order.unEevaluatedOrderState);
        }

        @Override
        public void evaluation() {
            System.out.println("失败：订单未收货");
        }

        @Override
        public void done() {
            System.out.println("失败：订单未评价");
        }

    }

    /**
     * 待评价状态
     */
    public static class UnEevaluatedOrderState
            extends OrderState
            implements IOrderState {

        public UnEevaluatedOrderState(Order order) {
            super(order);
        }

        @Override
        public void cancel() {
            System.out.println("失败：无法取消");
        }

        @Override
        public void payment() {
            System.out.println("失败：订单已支付");
        }

        @Override
        public void delivery() {
            System.out.println("失败：订单已发货");
        }

        @Override
        public void sign() {
            System.out.println("失败：订单已签收");
        }

        @Override
        public void evaluation() {
            System.out.println("成功：评价成功");
            this.order.setOrderState(order.doneOrderState);
        }

        @Override
        public void done() {
            System.out.println("失败：未评价");
        }
    }

    /**
     * 已完成
     */
    public static class DoneOrderState
            extends OrderState
            implements IOrderState {

        public DoneOrderState(Order order) {
            super(order);
        }

        @Override
        public void cancel() {
            System.out.println("失败：订单已收货");
        }

        @Override
        public void payment() {
            System.out.println("失败：订单已支付");
        }

        @Override
        public void delivery() {
            System.out.println("失败：订单已发货");
        }

        @Override
        public void sign() {
            System.out.println("失败：订单已签收");
        }

        @Override
        public void evaluation() {
            System.out.println("失败：订单已评价");
        }

        @Override
        public void done() {
            System.out.println("成功：订单完结成功");
        }
    }

    /**
     * 已取消
     */
    public static class CanceledOrderState
            extends OrderState
            implements IOrderState {


        public CanceledOrderState(Order order) {
            super(order);
        }

        @Override
        public void cancel() {
            System.out.println("失败：订单已取消");
        }

        @Override
        public void payment() {
            System.out.println("失败：订单已取消");
        }

        @Override
        public void delivery() {
            System.out.println("失败：订单已取消");

        }

        @Override
        public void sign() {
            System.out.println("失败：订单已取消");

        }

        @Override
        public void evaluation() {
            System.out.println("失败：订单已取消");

        }

        @Override
        public void done() {
            System.out.println("失败：订单已取消");

        }
    }
}
