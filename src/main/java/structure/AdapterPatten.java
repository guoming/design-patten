package structure;

/**
 * 适配器模式
 * @author guoming
 */
public class AdapterPatten {
    public static void main(String[] args) {

        IUsbInterface classAdapter = new TypeCInterface2UsbInterfaceClassAdapter();
        classAdapter.connect();

        TypeCInterface typeCInterface = new TypeCInterface();
        IUsbInterface objectAdapter = new TypeCInterface2UsbInterfaceObjectAdapter(typeCInterface);
        objectAdapter.connect();

    }

   public static class TypeCInterface
   {
       /**
        * ；连接
        */
       public void connect()
       {
           System.out.println("TypeC接口连接成功");
       }
   }

    public interface  IUsbInterface
    {
        void connect();
    }

    /**
     * TypeC转USP 类适配器
     */
    public static class TypeCInterface2UsbInterfaceClassAdapter
            extends TypeCInterface
            implements IUsbInterface
    {
        @Override
        public void connect() {
            super.connect();
        }
    }

    /**
     * TypeC转USP 类适配器
     */
    public static class TypeCInterface2UsbInterfaceObjectAdapter
            implements IUsbInterface
    {
        private final TypeCInterface typeCInterface;

        public TypeCInterface2UsbInterfaceObjectAdapter(TypeCInterface typeCInterface)
        {

            this.typeCInterface = typeCInterface;
        }

        @Override
        public void connect() {
           this.typeCInterface.connect();
            System.out.println("Usb 连接成功");
        }
    }
}
