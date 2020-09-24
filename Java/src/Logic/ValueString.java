/**
 * author Z3R0ing
 */
package Logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Engine.Drawable;

/**
 * Класс показателей
 */
public class ValueString implements Drawable {

	private final static int FONT_HEIGHT = 16;

	private static Font font = new Font("Arial", Font.BOLD, FONT_HEIGHT);
	
	/**
	* Название переменной
	*/
	private String string;
	/**
	* Показатель переменной
	*/
	public int value;
	/**
	* Координаты вывода показателя
	*/
	private int x, y;

	public ValueString(String string, int x, int y) {
		this.string = string;
		this.value = 0;
		this.x = x;
		this.y = y + FONT_HEIGHT;
	}

	public ValueString(String string, int startValue, int x, int y) {
		this.string = string;
		this.value = startValue;
		this.x = x;
		this.y = y + FONT_HEIGHT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SaveSamurai.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics gr) {
		gr.setColor(Color.BLACK);
		gr.setFont(font);
		gr.drawString(string + value, x, y);
	}

}
