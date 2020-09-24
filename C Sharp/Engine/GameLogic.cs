using System.Windows.Forms;

namespace SaveSamurai.Engine
{
    public interface GameLogic
    {
        void sendKey(Keys key);

        void resume();

        int getScore();

        void pause();

        bool IsOver();

        GameField getGameField();
    }
}
