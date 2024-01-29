package behavior;

/**
 * 模板方法
 * @author guoming
 */
public class TemplateMethodPatten {
    public static void main(String[] args) {

        Game game1=new Game1();
        Game game2=new Game2();

        game1.Play();
        game2.Play();

    }

    public static abstract class Game
    {
        public abstract   void InitGame();

        public abstract   void StartGame();

        public abstract void StopGame();

       public void Play()
        {
            InitGame();
            StartGame();
            StopGame();
        }
    }

    public static class Game1 extends Game
    {

        @Override
        public void InitGame() {
            System.out.println("game init");
        }

        @Override
        public void StartGame() {
            System.out.println("game start");
        }

        @Override
        public void StopGame() {
            System.out.println("game stop");
        }
    }

    public static class Game2 extends Game
    {

        @Override
        public void InitGame() {
            System.out.println("game init");
        }

        @Override
        public void StartGame() {
            System.out.println("game start");
        }

        @Override
        public void StopGame() {
            System.out.println("game stop");
        }
    }
}
