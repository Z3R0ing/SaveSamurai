/**
 * author Z3R0ing
 */
package Logic;

import java.awt.Image;

import Data.Files;
import Engine.GameField;

/**
 * Класс самурая, управляемого игроком
 */
public class Samurai extends GameObject {

	private static final int TEXTURE_WIDTH = 210;
	private static final int TEXTURE_HEIGHT = 400;

	private static Image[] images;
	
	/**
	 * Переменная текущего направления 1 - Право верх, 2 - Право низ, 3 - Лево низ,
	 * 4 - Лево верх.
	 */
	private int direction = 1;

	/**
	 * Статический класс, выполняется при запуске
	 */
	static {
		// Определяем текстуры объекта
		images = Files.getTextures(4, "s");
	}

	/**
	 * Создаём самурая в положении 1
	 */
	public Samurai() {
		// Задаём координаты самурая
		x = (GameField.GAME_FIELD_WIDTH - TEXTURE_WIDTH) / 2;
		y = GameField.GAME_FIELD_HEIGHT - TEXTURE_HEIGHT;
		width = TEXTURE_WIDTH;
		height = TEXTURE_HEIGHT;
		// Попорачиваем его в стартовое направление
		turn(getDirection());
	}

	/**
	 * Поворачивает самурая в определённом направлении
	 * 
	 * @param direction
	 *            - направление
	 * @see Logic.GameObject#direction
	 */
	public void turn(int direction) {
		// Изменяем текущее направление
		setDirection(direction);
		// Изменяем текущую текстуру
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
