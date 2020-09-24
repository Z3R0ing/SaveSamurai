/**
 * author Z3R0ing
 */
package Logic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import Engine.GameField;
import Engine.GameLogic;

/**
 * Класс описывающий логику игры, и связывающий все игровые объекты между собой
 */
public class Game implements GameLogic {

	public static final int GAME_TICK = 33;
	public static final int MIN_DELAY = 22 * GAME_TICK;

	/// Игровое поле игры
	private GameField gameField;
	/// Объект игрока
	private Samurai samurai;
	/// Массив объектов стрел
	private Arrow[] arrows = new Arrow[3];

	/// Игровой таймер
	private Timer timer;

	/// Строка состояния набранных очков
	private ValueString score;
	/// Сттрока состояния оставшихся жизней
	private ValueString life;

	/// Начальная скорость стрел
	private int speed = 4;
	/// Задержка появления стрел в миллисекундах (настройка баланса)
	private int mainDelay = 3 * MIN_DELAY;
	/// Вспомогательная переменная задержки между стрелами
	private int delay;
	/// Переменная сложности
	private int level;

	/// Надпись пауза
	private Banner pauseSign;

	/// Закончена ли игра
	private boolean isOver;
	/// Надпись GameOver
	private Banner overSign;

	/**
	 * Конструктор запускающий новую игру
	 */
	public Game() {
		// Инициализируем переменные
		delay = mainDelay; // Первая задержка всегда равна заданной начальной задержки
		level = 1; // Первый уровень всегда первый 
		// Инициализируем игровые объекты
		samurai = new Samurai();
		arrows[0] = new Arrow();
		arrows[0].hide();
		// Инициализируем строки состояния
		score = new ValueString("Score: ", 0, 0);
		life = new ValueString("Life: ", 5, 650, 0);
		// Инициализируем надписи
		pauseSign = new Banner("PAUSE");
		overSign = new Banner("GAMEOVER", Color.RED);
		// Создаём игровое поле (панель)
		gameField = new GameField();
		// Добавляем объекты на поле
		gameField.add(samurai);
		gameField.add(arrows[0]);
		// Добавляем строки на поле
		gameField.add(score);
		gameField.add(life);
		// Добавляем надписи на поле
		gameField.add(pauseSign);
		gameField.add(overSign);

		/*
		 * Инициализируем игровой таймер, который будет обновлять текущее игровое
		 * состояние, каждый определённый промежуток времени
		 */
		timer = new Timer(GAME_TICK, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});

		// Запускаем таймер
		timer.start();
	}

	/**
	 * Игровой цикл, вся игровая логика находится в этом методе
	 */
	private void update() {
		// Перебераем стрелы
		for (int i = 0; i < arrows.length; i++) {
			// Если стрела инициализированна
			if (arrows[i] != null) {
				// Если стрела не спрятана
				if (!arrows[i].isHidden()) {
					// Если стрела попала в самурая
					if (arrows[i].isHitObject(samurai)) {
						// Проверяем поймал ли её самурай
						if (samurai.getDirection() == arrows[i].getDirection()) {
							// Добавляем очки
							score.value++;
							if (score.value % 5 == 0) {
								addLevel();
							}
						} else {
							// Отбираем жизнь
							life.value--;
						}
						// Убираем стрелу
						arrows[i].hide();
					} else {
						// Двигаем стрелу
						arrows[i].moove(speed);
					}
				} else {
					// Если задержка кончилась
					if (delay < 0) {
						// Спауним стрелу
						arrows[i].respawn();
						// Возобновляем задержку
						delay = mainDelay;
					} else {
						delay -= GAME_TICK;
					}
				}
			}
		}
		if (life.value < 1) {
			gameOver();
		}
		// Перерисовываем поле
		gameField.repaint();
	}

	private void gameOver() {
		isOver = true;
		timer.stop();
		// Показываем надпись gameover
		overSign.setVisible(true);
		// Перерисовываем поле
		gameField.repaint();
	}

	private void addLevel() {
		level++;
		if (level <= 3) {
			arrows[level-1] = new Arrow();
			arrows[level-1].hide();
			gameField.add(arrows[level-1]);
			mainDelay -= MIN_DELAY;
		} else if (level <= 6) {
			arrows[level-4].hide();
		} else if (level == 10) {
			speed++;
		} else if (level == 15) {
			speed++;
		}
	}

	/**
	 * Возвращает своё игровое поле
	 * 
	 * @return the gameField
	 */
	public GameField getGameField() {
		return gameField;
	}

	/**
	 * Обработчик полученных от окна нажатий клавиш
	 * 
	 * @param key
	 *            - код нажатой клавиши
	 */
	public void sendKey(int key) {
		if (key == KeyEvent.VK_E || key == KeyEvent.VK_NUMPAD9) {
			samurai.turn(1);
		}
		if (key == KeyEvent.VK_C || key == KeyEvent.VK_NUMPAD3) {
			samurai.turn(2);
		}
		if (key == KeyEvent.VK_Z || key == KeyEvent.VK_NUMPAD1) {
			samurai.turn(3);
		}
		if (key == KeyEvent.VK_Q || key == KeyEvent.VK_NUMPAD7) {
			samurai.turn(4);
		}
		if (key == KeyEvent.VK_S || key == KeyEvent.VK_NUMPAD5) {
			if (timer.isRunning()) {
				pause();
			} else {
				resume();
			}
		}
	}

	/**
	 * Ставит игру на паузу
	 */
	public void pause() {
		timer.stop();
		// Показываем надпись паузы
		pauseSign.setVisible(true);
		// Перерисовываем поле
		gameField.repaint();
	}

	/**
	 * Востанавливает игру из паузы
	 */
	public void resume() {
		// Убираем надпись паузы
		pauseSign.setVisible(false);
		timer.start();
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score.value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Engine.GameLogic#gameOver()
	 */
	@Override
	public boolean isOver() {
		return isOver;
	}

}
