using System.Drawing;
using SaveSamurai.Engine;

namespace SaveSamurai.Logic
{
    public abstract class GameObject : Drawable
    {
        /// Текущая текстура объекта
        internal Image texture;
        /// X координата объекта
        internal int x;
        /// Y координата объекта
        internal int y;
        /// Ширина объекта
        internal int width;
        /// Высота объекта
        internal int height;

        /**
         * Рисует с помощью графики объект
         * 
         * param gr
         *            - графика
         */
        public virtual void draw(Graphics gr)
        {
            if (texture != null)
            {
                gr.DrawImage(texture, x, y);
            }
            else
            {
                System.Windows.Forms.MessageBox.Show("Texture of " + this.ToString() + " is null", "Error!",
                    System.Windows.Forms.MessageBoxButtons.OK, System.Windows.Forms.MessageBoxIcon.Error);
                System.Environment.Exit(3);
            }
        }
    }
}
