using SaveSamurai.Data;
using System;
using System.Windows.Forms;

namespace SaveSamurai.Engine
{
    public partial class GameFrame : Form
    {
        private GameLogic game;

        public GameFrame(GameLogic game)
        {
            this.game = game;
            InitializeComponent();
            Controls.Add(game.getGameField());
        }

        // Окно закрылось
        private void GameFrame_FormClosed(object sender, FormClosedEventArgs e)
        {
            Close();
        }

        // Если окно деактивированно - ставит паузу. Аналогично со сворачиванием
        private void GameFrame_Activated(object sender, System.EventArgs e)
        {
            game.resume();
        }

        // Если окно активированно - снимает паузу. Аналогично с разворачиванием
        private void GameFrame_Deactivate(object sender, System.EventArgs e)
        {
            game.pause();
        }

        /*
		 * Слушатель клавиш будет отправлять код нажатой клавиши игре
		 */
        private void GameFrame_KeyDown(object sender, KeyEventArgs e)
        {
            Keys key = e.KeyCode;
            game.sendKey(key);
            if (key == Keys.Enter)
            {
                if (game.IsOver())
                {
                    closeFrame();
                }
            }
        }

        // Была нажата клавиша закрыть
        private void GameFrame_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (e.CloseReason == CloseReason.UserClosing)
            {
                var res = MessageBox.Show("Хотите выйти?", "Выход",
                        MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (res == DialogResult.Yes)
                {
                    closeFrame();
                } else
                {
                    e.Cancel = true;
                }
            }
        }

        private void closeFrame()
        {
            if (game.IsOver())
            {
                String topLine = Files.getTop();
                int score = game.getScore();
                if (topLine == "")
                {
                    String name = InputBox.GetString();
                    Files.changeTop(score, name);
                }
                else
                {
                    if (int.Parse(topLine.Split(' ')[0]) < score)
                    {
                        String name = InputBox.GetString();
                        Files.changeTop(score, name);
                    }
                    else
                    {
                        MessageBox.Show("Ваш результат: " + score
                            + "\r\nЛучший результат: " + int.Parse(topLine.Split(' ')[0]), "Игра окончена",
                            MessageBoxButtons.OK, MessageBoxIcon.Information);
                    }
                }
            }
            Dispose();
        }

        private class InputBox : Form
        {
            static String returnString;
            TextBox t1;

            private InputBox()
            {
                t1 = new TextBox();
                Button b1 = new Button();
                Width = 0;
                Height = 0;
                AutoSize = true;
                FormBorderStyle = FormBorderStyle.FixedToolWindow;
                MaximizeBox = false;
                MinimizeBox = false;
                StartPosition = FormStartPosition.CenterParent;
                ShowInTaskbar = false;
                Text = "Введите имя:";
                t1.Width = 300;
                t1.Text = "";;
                Controls.Add(t1);
                b1.Text = "Принять";
                b1.Click += new System.EventHandler(Button_Click);
                b1.Left = t1.Right + 5;
                Controls.Add(b1);
            }

            public static String GetString()
            {
                InputBox inputBox = new InputBox();
                inputBox.ShowDialog();
                return returnString;
            }

            private void Button_Click(object sender, EventArgs e)
            {
                returnString = t1.Text;
                Dispose();
            }
        }
    }
}
