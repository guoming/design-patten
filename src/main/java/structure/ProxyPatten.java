package structure;

/**
 * 代理模式
 * @author guoming
 */
public class ProxyPatten {

    public static void main(String[] args) {

        IWebRequest request = new HttpWebRequestLogProxy();
        request.sendGetRequest("http://example.com");
        request.sendPostRequest("http:/example.com");
    }


    public interface IWebRequest
    {
         void sendGetRequest(String url);

         void sendPostRequest(String url);

    }

    public static class HttpWebRequest implements IWebRequest
    {

        @Override
        public void sendGetRequest(String url) {
            System.out.println("get: "+url);
        }

        @Override
        public void sendPostRequest(String url) {
            System.out.println("post: "+url);


        }
    }


    public static class HttpWebRequestLogProxy
            extends HttpWebRequest
            implements IWebRequest
    {
        @Override
        public void sendPostRequest(String url) {

            System.out.println("before: "+ url);
            super.sendPostRequest(url);
            System.out.println("after: "+ url);

        }

        @Override
        public void sendGetRequest(String url) {

            System.out.println("before: "+ url);

            super.sendGetRequest(url);

            System.out.println("after: "+ url);

        }
    }
}
