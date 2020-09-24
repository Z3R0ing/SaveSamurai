/**
 * author Z3R0ing
 */
package Logic;

import java.awt.Image;

import Data.Files;
import Engine.GameField;

/**
 * ����� �������, ������������ �������
 */
public class Samurai extends GameObject {

	private static final int TEXTURE_WIDTH = 210;
	private static final int TEXTURE_HEIGHT = 400;

	private static Image[] images;
	
	/**
	 * ���������� �������� ����������� 1 - ����� ����, 2 - ����� ���, 3 - ���� ���,
	 * 4 - ���� ����.
	 */
	private int direction = 1;

	/**
	 * ����������� �����, ����������� ��� �������
	 */
	static {
		// ���������� �������� �������
		images = Files.getTextures(4, "s");
	}

	/**
	 * ������ ������� � ��������� 1
	 */
	public Samurai() {
		// ����� ���������� �������
		x = (GameField.GAME_FIELD_WIDTH - TEXTURE_WIDTH) / 2;
		y = GameField.GAME_FIELD_HEIGHT - TEXTURE_HEIGHT;
		width = TEXTURE_WIDTH;
		height = TEXTURE_HEIGHT;
		// ������������ ��� � ��������� �����������
		turn(getDirection());
	}

	/**
	 * ������������ ������� � ����������� �����������
	 * 
	 * @param direction
	 *            - �����������
	 * @see Logic.GameObject#direction
	 */
	public void turn(int direction) {
		// �������� ������� �����������
		setDirection(direction);
		// �������� ������� ��������
		texture = images[direction - 1];
	}
	
	/**
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param direction
	 *            the direction to set
	 */
	private void setDirection(int direction) {
		if (direction <= 0) {
			this.direction = 1;
		} else if (direction > 4) {
			this.direction = 4;
		} else {
			this.direction = direction;
		}
	}
	

}
