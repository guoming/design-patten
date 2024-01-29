package structure;

import java.util.ArrayList;
import java.util.List;

/**
 * 外观模式
 * @author guoming
 */
public class FacadePatten {

    public static void main(String[] args) {

        TopicFacade facade=new TopicFacade();

        List<String> topNews = facade.getTopNews();

        for (String topNew : topNews) {
            System.out.println(topNew);
        }
    }

    public static class TopicFacade
    {

        SportTopic sportTopic=new SportTopic();
        MusicTopic musicTopic=new MusicTopic();

        public List<String> getTopNews()
        {
            String sportTop10News = sportTopic.getTop10News();

            String musicTop10News = musicTopic.getTop10News();

            ArrayList<String> news = new ArrayList<>();

            news.add(sportTop10News);

            news.add(musicTop10News);

            return news;

        }
    }

    public static class SportTopic
    {
        public String getTop10News()
        {
            return "Sport Top 10 News";
        }
    }

    public static class MusicTopic
    {

        public String getTop10News()
        {
            return "Music Top 10 News";

        }

    }
}
