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
 * ����� �������� ����
 */
public class GameFrame extends JFrame {
	
	/**
	* ���� �������� ����
	*/
	private GameLogic game;

	/**
	 * ������ ������� ���� ����.
	 * ��������� ������������ ��� ����������� �������� ������� ����, �� �������
	 * ����������� ����������
	 */
	public GameFrame(GameLogic game) {
		this.game = game;
		// ������������� ��������� ����
		setFocusable(true);
		setBounds(100, 100, Main.FRAME_WIDTH, Main.FRAME_HEIGHT);
		setTitle("Save Samurai");
		setBackground(Color.WHITE);
		setResizable(false);
		/*
		 * ������ �� ������ ��� ������� �� ������ "�������", ����� ��������� �������
		 * ��������� ������� ����
		 */
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		// ����������� ��������� ������� ����
		/*
		 * ��������� ������ ����� ���������� ��� ������� ������� ����
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
		 * ��������� ������� ����.
		 * 
		 * ���� ���� ���������������/������������� - ������/������� �����. ���������� ��
		 * �������������/���������������
		 * 
		 * ���� ���� �������, ��������� �� ���������� (��� ��� ������� � ��� ��
		 * ��������)
		 * 
		 * ��� �������� ���� ������� ������ � ���������� ����
		 */
		addWindowListener(new WindowListener() {
			// ���� �������������
			public void windowActivated(WindowEvent event) {
				game.resume();
			}

			// ���� ���������
			public void windowClosed(WindowEvent event) {
				// doNothing();
			}

			// ���� ������ ������� �������
			public void windowClosing(WindowEvent event) {
				Object[] options = { "��", "������" };
				int n = JOptionPane.showOptionDialog(event.getOppositeWindow(), "�� ������������� ������ �����?",
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

			// ���� ����������������
			public void windowDeactivated(WindowEvent event) {
				game.pause();
			}

			// ���� ����������
			public void windowDeiconified(WindowEvent event) {
				game.resume();
			}

			// ���� ��������
			public void windowIconified(WindowEvent event) {
				game.pause();
			}

			// ���� ���������
			public void windowOpened(WindowEvent event) {
				// doNothing();
			}

		});

		// ����������� � ���� ������ ���� ����
		getContentPane().add(game.getGameField());

		// ���������� ����
		setVisible(true);
	}
	
	/**
	* ��������� ���� � ����������� ����������� ����
	*/
	private void closeFrame() {
		if (game.isOver()) {
			String topLine = Files.getTop();
			int score = game.getScore();
			if (topLine.isEmpty()) {
				String name = JOptionPane.showInputDialog(GameFrame.this, "������� ��� ���:", "����� ������!",
						JOptionPane.WARNING_MESSAGE);
				Files.changeTop(score, name);
			} else {
				if (Integer.parseInt(topLine.split(" ")[0]) < score) {
					String name = JOptionPane.showInputDialog(GameFrame.this, "������� ��� ���:", "����� ������!",
							JOptionPane.WARNING_MESSAGE);
					Files.changeTop(score, name);
				} else {
					JOptionPane.showMessageDialog(GameFrame.this,
							"��� ���������: " + score + "\n������ ���������: "
									+ Integer.parseInt(topLine.split(" ")[0]),
							"���� ��������", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		new Launcher();
		dispose();
	}

}
