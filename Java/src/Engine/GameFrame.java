/**
 * author Z3R0ing
 */
package Engine;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Data.Files;
import SaveSamurai.Launcher;
import SaveSamurai.Main;

/**
 * Класс игрового окна
 */
public class GameFrame extends JFrame {
	
	/**
	* Игра игрового окна
	*/
	private GameLogic game;

	/**
	 * Создаём игровое окно игры.
	 * Интерфейс используется для возможности изменить правила игры, не изменяя
	 * визуального интерфейса
	 */
	public GameFrame(GameLogic game) {
		this.game = game;
		// Устанавливаем параметры окна
		setFocusable(true);
		setBounds(100, 100, Main.FRAME_WIDTH, Main.FRAME_HEIGHT);
		setTitle("Save Samurai");
		setBackground(Color.WHITE);
		setResizable(false);
		/*
		 * Ничего не делать при нажатии на кнопку "Закрыть", чтобы правильно работал
		 * слушатель событий окна
		 */
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		// Прикрепляем слушатели событий окна
		/*
		 * Слушатель клавиш будет отправлять код нажатой клавиши игре
		 */
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				game.sendKey(key);
				if (key == KeyEvent.VK_ENTER) {
					if (game.isOver()) {
						closeFrame();						
					}
				}
			}
		});

		/*
		 * Слушатель событий окна.
		 * 
		 * Если окно деактивированно/активированно - ставит/снимает паузу. Аналогично со
		 * сворачиванием/разворачиванием
		 * 
		 * Если окно закрыто, закрываем всё приложение (так как лаунчер у нас не
		 * закрылся)
		 * 
		 * При закрытии окна спросим игрока о сохранении игры
		 */
		addWindowListener(new WindowListener() {
			// Окно ативировалось
			public void windowActivated(WindowEvent event) {
				game.resume();
			}

			// Окно закрылось
			public void windowClosed(WindowEvent event) {
				// doNothing();
			}

			// Была нажата клавиша закрыть
			public void windowClosing(WindowEvent event) {
				Object[] options = { "Да", "Отмена" };
				int n = JOptionPane.showOptionDialog(event.getOppositeWindow(), "Вы действительно хотите выйти?",
						"Exit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				switch (n) {
				case 0:
					closeFrame();
					break;
				case 1:
					game.pause();
					break;
				}
			}

			// Окно деактивировалось
			public void windowDeactivated(WindowEvent event) {
				game.pause();
			}

			// Окно раскрылось
			public void windowDeiconified(WindowEvent event) {
				game.resume();
			}

			// Окно скрылось
			public void windowIconified(WindowEvent event) {
				game.pause();
			}

			// Окно открылось
			public void windowOpened(WindowEvent event) {
				// doNothing();
			}

		});

		// Прикрепляем к окну ировое поле игры
		getContentPane().add(game.getGameField());

		// Показываем окно
		setVisible(true);
	}
	
	/**
	* Закрываем игру с сохранением результатов игры
	*/
	private void closeFrame() {
		if (game.isOver()) {
			String topLine = Files.getTop();
			int score = game.getScore();
			if (topLine.isEmpty()) {
				String name = JOptionPane.showInputDialog(GameFrame.this, "Введите своё имя:", "Новый рекорд!",
						JOptionPane.WARNING_MESSAGE);
				Files.changeTop(score, name);
			} else {
				if (Integer.parseInt(topLine.split(" ")[0]) < score) {
					String name = JOptionPane.showInputDialog(GameFrame.this, "Введите своё имя:", "Новый рекорд!",
							JOptionPane.WARNING_MESSAGE);
					Files.changeTop(score, name);
				} else {
					JOptionPane.showMessageDialog(GameFrame.this,
							"Ваш результат: " + score + "\nЛучший результат: "
									+ Integer.parseInt(topLine.split(" ")[0]),
							"Игра окончена", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		new Launcher();
		dispose();
	}

}
