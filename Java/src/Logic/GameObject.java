/**
 * author Z3R0ing
 */
package Logic;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JOptionPane;

import Engine.Drawable;

/**
* Абстрактный клас описывает игровые объекты
*/
public abstract class GameObject implements Drawable {

	/// Текущая текстура объекта
	protected Image texture;
	/// X координата объекта
	protected int x;
	/// Y координата объекта
	protected int y;
	/// Ширина объекта
	protected int width;
	/// Высота объекта
	protected int height;

	/**
	 * Рисует с помощью графики объект
	 * 
	 * @param gr
	 *            - графика
	 */
	@Override
	public void draw(Graphics gr) {
		if (texture != null) {
			gr.drawImage(texture, x, y, null);
		} else {
			JOptionPane.showMessageDialog(null, "Texture of " + this.toString() + " is null", null,
					JOptionPane.ERROR_MESSAGE);
			System.exit(3);
		}
	}

}
