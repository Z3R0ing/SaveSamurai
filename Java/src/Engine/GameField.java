/**
 * author Z3R0ing
 */
package Engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JPanel;

import Data.Files;
import SaveSamurai.Main;

/**
 * Панель игрового поля, на которой рисуются все объекты
 */
public class GameField extends JPanel {

	public static final int GAME_FIELD_WIDTH = Main.FRAME_WIDTH - 6;
	public static final int GAME_FIELD_HEIGHT = Main.FRAME_HEIGHT - 29;

	///
	private Image background;

	/// Список элементов на игровом поле
	private ArrayList<Drawable> elements;

	/**
	 * 
	 */
	public GameField() {
		// Задаём цвет фона
		setBackground(Color.WHITE);
		// Инициализируем список элементов
		elements = new ArrayList<Drawable>();
		background = Files.getTexture("back");
	}

	/**
	 * Добавляет элемент на поле
	 * 
	 * @param element
	 *            - элемент
	 */
	public void add(Drawable element) {
		elements.add(element);
	}

	/**
	 * Отрисовывает игровое поле
	 */
	public void paintComponent(Graphics gr) {
		// Очищаем панель
		gr.clearRect(0, 0, 800, 600);
		// Рисуем фон
		gr.drawImage(background, 0, 0, null);
		// Отрисовываем игровые элементы
		for (Drawable element : elements) {
			if (element != null)
				element.draw(gr);
		}
	}

}
