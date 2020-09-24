using System;
using System.Windows.Forms;
using SaveSamurai.Engine;
using SaveSamurai.Logic;

namespace SaveSamurai
{
    public partial class Launcher : Form
    {
        public Launcher()
        {
            InitializeComponent();
        }

        private void butNew_Click(object sender, EventArgs e)
        {
            newGame();
        }

        private void newGame()
        {
            Hide();
            new GameFrame(new Game()).ShowDialog();
            Show();
        }
    }
}
