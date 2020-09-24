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
 * ����� ������� � �������
 */
public class Banner implements Drawable {

	/// ������ ������ �������
	private final static int FONT_HEIGHT = 32;
	/// ������ ����
	private final static int FONT_WIDTH = FONT_HEIGHT/2;
	/// ����� �������
	private static Font font = new Font("Arial", Font.BOLD, FONT_HEIGHT);
	
	/// ��� ���������� �������
	protected int x;
	/// ����� ���������� �������
	protected int y;
	/// ����� �������
	private String text;
	/// ���� �������
	private Color color;
	
	private boolean visible;

	/**
	 * ������ ����� � �������
	 * @param text
	 *            - ����� �������
	 */
	public Banner(String text) {
		this.text = text;
		x = GameField.GAME_FIELD_WIDTH / 2 - (FONT_WIDTH * text.length()) / 2;
		y = GameField.GAME_FIELD_HEIGHT / 2 - FONT_HEIGHT / 2;
		color = Color.BLACK;
		visible = false;
	}
	
	/**
	 * ������ ����� � ������� �������
	 * @param text
	 *            - ����� �������
	 * @param color
	 *            - ���� ������
	 */
	public Banner(String text, Color color) {
		this.text = text;
		x = GameField.GAME_FIELD_WIDTH / 2 - (FONT_WIDTH * text.length()) / 2;
		y = GameField.GAME_FIELD_HEIGHT / 2 - FONT_HEIGHT / 2;
		this.color = color;
		visible = false;
	}
	
	/**
	* ������������� �������� � ������������ � ���������� ����������
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
