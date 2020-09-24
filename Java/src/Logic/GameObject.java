/**
 * author Z3R0ing
 */
package Logic;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JOptionPane;

import Engine.Drawable;

/**
* ����������� ���� ��������� ������� �������
*/
public abstract class GameObject implements Drawable {

	/// ������� �������� �������
	protected Image texture;
	/// X ���������� �������
	protected int x;
	/// Y ���������� �������
	protected int y;
	/// ������ �������
	protected int width;
	/// ������ �������
	protected int height;

	/**
	 * ������ � ������� ������� ������
	 * 
	 * @param gr
	 *            - �������
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
