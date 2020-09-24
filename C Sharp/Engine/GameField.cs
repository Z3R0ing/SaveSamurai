using SaveSamurai.Data;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;

namespace SaveSamurai.Engine
{
    public class GameField : Panel
    {
        public static readonly int GAME_FIELD_WIDTH = Program.FRAME_WIDTH - 6;
        public static readonly int GAME_FIELD_HEIGHT = Program.FRAME_HEIGHT - 29;

        ///
        private Image background;

        /// Список элементов на игровом поле
        private List<Drawable> elements;

        /// Переменная графики
        private Graphics gr;

        public GameField()
        {
            DoubleBuffered = true;
            Dock = DockStyle.Fill;
            Location = new Point(0, 0);
            Name = "GameField";
            Size = new Size(784, 561);
            TabIndex = 0;
            Paint += new PaintEventHandler(GameField_Paint);
            gr = CreateGraphics();
            elements = new List<Drawable>();
            background = Files.getTexture("back");
        }

        /**
	 * Добавляет элемент на поле
	 * 
	 * @param element
	 *            - элемент
	 */
        public void add(Drawable element)
        {
            elements.Add(element);
        }

        /**
         * Отрисовывает игровое поле
         */
        private void GameField_Paint(object sender, PaintEventArgs e)
        {
            gr = e.Graphics;
            // Очищаем панель
            gr.Clear(BackColor);
            // Рисуем фон
            gr.DrawImage(background, 0, 0);
            // Отрисовываем игровые элементы
            foreach (Drawable element in elements)
            {
                if (element != null)
                    element.draw(gr);
            }
        }
    }
}
