using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SaveSamurai.Logic
{
    class ValueString : GameObject
    {
        private readonly static int FONT_HEIGHT = 16;

        private static Font font = new Font("Arial", FONT_HEIGHT, FontStyle.Regular, GraphicsUnit.Pixel, ((byte)(204)));

        private String str;
        public int value;
        private int x, y;

        public ValueString(String str, int x, int y)
        {
            this.str = str;
            this.value = 0;
            this.x = x;
            this.y = y + FONT_HEIGHT;
        }

        public ValueString(String str, int startValue, int x, int y)
        {
            this.str = str;
            this.value = startValue;
            this.x = x;
            this.y = y + FONT_HEIGHT;
        }

        /*
         * see SaveSamurai.Drawable#draw
         */
        public override void draw(Graphics gr)
        {
            gr.DrawString(str + value, font, new SolidBrush(Color.Black), new Point(x, y));
        }
    }
}
