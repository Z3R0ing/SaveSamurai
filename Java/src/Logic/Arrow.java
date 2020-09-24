/**
 * author Z3R0ing
 */
package Logic;

import java.awt.Image;

import Data.Files;
import Engine.GameField;

public class Arrow extends GameObject {

	private static final int TEXTURE_WIDTH = 80;
	private static final int TEXTURE_HEIGHT = 40;

	private static final int TRACE_HEIGHT = 146;
	@SuppressWarnings("unused")
	private static final int TRACE_WIDTH = 292;

	private static final int TOP_INDENT = 49;
	private static final int INDENT = 115;
	private static final int UP_TRACE_Y = TOP_INDENT;
	private static final int DOWN_TRACE_Y = UP_TRACE_Y + TRACE_HEIGHT + INDENT;

	private static Image[] images;

	/**
	 * ���������� �������� ����������� 1 - ����� ����, 2 - ����� ���, 3 - ���� ���,
	 * 4 - ���� ����.
	 */
	private int direction = 1;
	/**
	* ����������-��������� ������� ������������
	*/
	private boolean hidden;

	/**
	 * ����������� �����������, ����������� ��� �������
	 */
	static {
		// ���������� �������� �������
		images = Files.getTextures(2, "a");
	}

	/**
	 * ����������� �����. ����� ����������� ���������,  � ������� � ��������� �����������
	 */
	public Arrow() {
		// ����� ���������� ������
		x = 0;
		y = 0;
		width = TEXTURE_WIDTH;
		height = TEXTURE_HEIGHT;
		
		setDirection((int) (Math.random() * 4));
		spawn();
		hidden = false;
	}

	/**
	 * ����� ������� ������ �� ����������� ����� � ����������� �� �����������
	 */
	private void spawn() {
		// TODO comment what happened in #spawn
		SpawnPlace place = getSpawnPlace();
		x = place.x;
		y = place.y;
		switch (getDirection()) {
		case 1:
		case 2:
			texture = images[0];
			break;
		case 3:
		case 4:
			texture = images[1];
			break;
		}
	}

	/**
	 * �������� ���������� ������ � ��������� ���������� c ����������� ���������
	 * 
	 * @param speed
	 *            - ��������
	 */
	public void moove(int speed) {
		// TODO comment what happened in #moove
		switch (getDirection()) {
		case 1:
		case 2:
			x -= 2 * speed;
			break;
		case 3:
		case 4:
			x += 2 * speed;
			break;
		}
		y += speed;
	}

	/**
	 * ��������� ������ �� ������ � ������
	 * 
	 * @param object
	 *            - ������ ����
	 * @return true - ���� ������ ���� �� �� ���� ������� ��������� �� ������� ����
	 */
	public boolean isHitObject(GameObject object) {
		/*
		 * ������ �������� � ������, ���� ��� �� ��������� �� � ����� �� ������ ������
		 * �������, ������� ���������� ��������� ��� ������ ����������� �� ������� ����
		 */

		boolean isOver = false;
		boolean isRight = false;
		boolean isUnder = false;
		boolean isLeft = false;

		// ���� ������ ������� ������ ���� ������� ������� �������
		if (y + height <= object.y) {
			isOver = true;
		}
		// ���� ����� ������� ������ ������ ������ ������� �������
		if (x >= object.x + object.width) {
			isRight = true;
		}
		// ���� ������� ������� ������ ���� ������ ������� �������
		if (y >= object.y + object.height) {
			isUnder = true;
		}
		// ���� ������ ������� ������ ����� ����� ������� �������
		if (x + width < object.x) {
			isLeft = true;
		}

		if ((isOver || isRight || isUnder || isLeft) == false) {
			/*
			 * ���� ��� �������� ���������� flase ������ ������ �� ��������� �� � ����� ��
			 * ������ ������ �������, ������������� ������ ������ � ������
			 */
			return true;
		} else {
			/*
			 * ���� ���� �� ���� ����� true ������ ������ � �����-�� �� ������ �������,
			 * ������������� � ������ ������ �� ������
			 */
			return false;
		}
	}

	/**
	 * ������ ������ � ������ �����������
	 * 
	 * @param direction
	 *            - ����������� ������
	 */
	public void respawn() {
		// ������������� ����� �����������
		setDirection(direction + (int) (Math.random() * 2 + 1));
		// �������� �������
		hidden = false;
		// ������� ������ �����
		spawn();
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
			int buf = direction % 4;
			if (buf == 0) {
				this.direction = 4;
			} else {
				this.direction = buf;
			}
		} else {
			this.direction = direction;
		}
	}
	
	// ������������, ������� ������ ���������� ���� ������
	enum SpawnPlace {
		/// ��� direction = 1
		RightUp(GameField.GAME_FIELD_WIDTH - TEXTURE_WIDTH, UP_TRACE_Y),
		/// ��� direction = 2
		RightDown(GameField.GAME_FIELD_WIDTH - TEXTURE_WIDTH, DOWN_TRACE_Y),
		/// ��� direction = 3
		LeftDown(0, DOWN_TRACE_Y),
		/// ��� direction = 4
		LeftUp(0, UP_TRACE_Y),
		/// ������� �������
		Hide(-TEXTURE_WIDTH, -TEXTURE_HEIGHT);

		public int x, y;

		SpawnPlace(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	/**
	 * ���������� ����� ������ ������������ �� �����������
	 * 
	 * @return �����
	 */
	private SpawnPlace getSpawnPlace() {
		int currentDirection = getDirection();
		switch (currentDirection) {
		case 1:
			return SpawnPlace.RightUp;
		case 2:
			return SpawnPlace.RightDown;
		case 3:
			return SpawnPlace.LeftDown;
		case 4:
			return SpawnPlace.LeftUp;
		}
		return null;
	}

	/**
	 * ������ ������
	 */
	public void hide() {
		x = SpawnPlace.Hide.x;
		y = SpawnPlace.Hide.y;
		hidden = true;
	}

	/**
	 * ��������� ������ ������������ ������
	 * @return hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

}
