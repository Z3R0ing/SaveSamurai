using SaveSamurai.Engine;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SaveSamurai.Logic
{
    class Banner : GameObject
    {
        /// Размер шрифта баннера
        private readonly static int FONT_HEIGHT = 32;
        /// Ширина букв
        private readonly static int FONT_WIDTH = FONT_HEIGHT / 2;
        /// Шрифт баннера
        private static Font font = new Font("Arial", FONT_HEIGHT, FontStyle.Regular, GraphicsUnit.Pixel, ((byte)(204)));

        /// Икс координата баннера
        protected int x;
        /// Игрек координата баннера
        protected int y;
        /// Текст баннера
        private String text;
        /// Цвет баннера
        private SolidBrush brush;

        private bool visible;

        /**
         * 
         */
        public Banner(String text)
        {
            this.text = text;
            x = GameField.GAME_FIELD_WIDTH / 2 - (FONT_WIDTH * text.Length) / 2;
            y = GameField.GAME_FIELD_HEIGHT / 2 - FONT_HEIGHT / 2;
            brush = new SolidBrush(Color.Black);
            visible = false;
        }

        public Banner(String text, Color color)
        {
            this.text = text;
            x = GameField.GAME_FIELD_WIDTH / 2 - (FONT_WIDTH * text.Length) / 2;
            y = GameField.GAME_FIELD_HEIGHT / 2 - FONT_HEIGHT / 2;
            brush = new SolidBrush(color);
            visible = false;
        }

        public void setVisible(bool arg)
        {
            visible = arg;
        }

        /*
         * see SaveSamurai.Drawable#draw
         */
        public override void draw(Graphics gr)
        {
            if (visible)
            {
                gr.DrawString(text, font, brush, new Point(x, y));
            }
        }
    }
}
