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
 * ����� ����������� ������ ����, � ����������� ��� ������� ������� ����� �����
 */
public class Game implements GameLogic {

	public static final int GAME_TICK = 33;
	public static final int MIN_DELAY = 22 * GAME_TICK;

	/// ������� ���� ����
	private GameField gameField;
	/// ������ ������
	private Samurai samurai;
	/// ������ �������� �����
	private Arrow[] arrows = new Arrow[3];

	/// ������� ������
	private Timer timer;

	/// ������ ��������� ��������� �����
	private ValueString score;
	/// ������� ��������� ���������� ������
	private ValueString life;

	/// ��������� �������� �����
	private int speed = 4;
	/// �������� ��������� ����� � ������������� (��������� �������)
	private int mainDelay = 3 * MIN_DELAY;
	/// ��������������� ���������� �������� ����� ��������
	private int delay;
	/// ���������� ���������
	private int level;

	/// ������� �����
	private Banner pauseSign;

	/// ��������� �� ����
	private boolean isOver;
	/// ������� GameOver
	private Banner overSign;

	/**
	 * ����������� ����������� ����� ����
	 */
	public Game() {
		// �������������� ����������
		delay = mainDelay; // ������ �������� ������ ����� �������� ��������� ��������
		level = 1; // ������ ������� ������ ������ 
		// �������������� ������� �������
		samurai = new Samurai();
		arrows[0] = new Arrow();
		arrows[0].hide();
		// �������������� ������ ���������
		score = new ValueString("Score: ", 0, 0);
		life = new ValueString("Life: ", 5, 650, 0);
		// �������������� �������
		pauseSign = new Banner("PAUSE");
		overSign = new Banner("GAMEOVER", Color.RED);
		// ������ ������� ���� (������)
		gameField = new GameField();
		// ��������� ������� �� ����
		gameField.add(samurai);
		gameField.add(arrows[0]);
		// ��������� ������ �� ����
		gameField.add(score);
		gameField.add(life);
		// ��������� ������� �� ����
		gameField.add(pauseSign);
		gameField.add(overSign);

		/*
		 * �������������� ������� ������, ������� ����� ��������� ������� �������
		 * ���������, ������ ����������� ���������� �������
		 */
		timer = new Timer(GAME_TICK, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});

		// ��������� ������
		timer.start();
	}

	/**
	 * ������� ����, ��� ������� ������ ��������� � ���� ������
	 */
	private void update() {
		// ���������� ������
		for (int i = 0; i < arrows.length; i++) {
			// ���� ������ �����������������
			if (arrows[i] != null) {
				// ���� ������ �� ��������
				if (!arrows[i].isHidden()) {
					// ���� ������ ������ � �������
					if (arrows[i].isHitObject(samurai)) {
						// ��������� ������ �� � �������
						if (samurai.getDirection() == arrows[i].getDirection()) {
							// ��������� ����
							score.value++;
							if (score.value % 5 == 0) {
								addLevel();
							}
						} else {
							// �������� �����
							life.value--;
						}
						// ������� ������
						arrows[i].hide();
					} else {
						// ������� ������
						arrows[i].moove(speed);
					}
				} else {
					// ���� �������� ���������
					if (delay < 0) {
						// ������� ������
						arrows[i].respawn();
						// ������������ ��������
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
		// �������������� ����
		gameField.repaint();
	}

	private void gameOver() {
		isOver = true;
		timer.stop();
		// ���������� ������� gameover
		overSign.setVisible(true);
		// �������������� ����
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
	 * ���������� ��� ������� ����
	 * 
	 * @return the gameField
	 */
	public GameField getGameField() {
		return gameField;
	}

	/**
	 * ���������� ���������� �� ���� ������� ������
	 * 
	 * @param key
	 *            - ��� ������� �������
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
	 * ������ ���� �� �����
	 */
	public void pause() {
		timer.stop();
		// ���������� ������� �����
		pauseSign.setVisible(true);
		// �������������� ����
		gameField.repaint();
	}

	/**
	 * �������������� ���� �� �����
	 */
	public void resume() {
		// ������� ������� �����
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
