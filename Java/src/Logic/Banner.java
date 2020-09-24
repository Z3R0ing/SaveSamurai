/**
 * author Zeroing
 */
package Logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Engine.Drawable;
import Engine.GameField;

/**
 * Класс баннера с текстом
 */
public class Banner implements Drawable {

	/// Размер шрифта баннера
	private final static int FONT_HEIGHT = 32;
	/// Ширина букв
	private final static int FONT_WIDTH = FONT_HEIGHT/2;
	/// Шрифт баннера
	private static Font font = new Font("Arial", Font.BOLD, FONT_HEIGHT);
	
	/// Икс координата баннера
	protected int x;
	/// Игрек координата баннера
	protected int y;
	/// Текст баннера
	private String text;
	/// Цвет баннера
	private Color color;
	
	private boolean visible;

	/**
	 * Создаём банер с текстом
	 * @param text
	 *            - текст баннера
	 */
	public Banner(String text) {
		this.text = text;
		x = GameField.GAME_FIELD_WIDTH / 2 - (FONT_WIDTH * text.length()) / 2;
		y = GameField.GAME_FIELD_HEIGHT / 2 - FONT_HEIGHT / 2;
		color = Color.BLACK;
		visible = false;
	}
	
	/**
	 * Создаём банер с цветным текстом
	 * @param text
	 *            - текст баннера
	 * @param color
	 *            - цвет текста
	 */
	public Banner(String text, Color color) {
		this.text = text;
		x = GameField.GAME_FIELD_WIDTH / 2 - (FONT_WIDTH * text.length()) / 2;
		y = GameField.GAME_FIELD_HEIGHT / 2 - FONT_HEIGHT / 2;
		this.color = color;
		visible = false;
	}
	
	/**
	* Устанавливает видимост в соответствии с переданным аргументом
	*/ 
	public void setVisible(boolean arg) {
		visible = arg;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see SaveSamurai.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics gr) {
		if (visible) {
			gr.setColor(color);
			gr.setFont(font);
			gr.drawString(text, x, y);
		}
	}

}
