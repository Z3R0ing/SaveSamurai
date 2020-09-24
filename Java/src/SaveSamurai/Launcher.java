/**
 * author Z3R0ing
 */
package SaveSamurai;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Data.Files;
import Engine.GameFrame;
import Logic.Game;

public class Launcher extends JFrame {

	private JButton butNew;
	private JButton butTop;
	private JButton butHowTo;
	private JButton butExit;
	private JPanel pane;
	private Font fontForButtons = new Font("Arial", Font.PLAIN, 24);

	/**
	 * Создаём окно лаунчера со всеми кнопками
	 */
	public Launcher() {
		setTitle("Launcher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		butNew = new JButton("Новая игра");
		butNew.setFont(fontForButtons);
		butNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newGame();
			}
		});

		butTop = new JButton("ТОП-1");
		butTop.setFont(fontForButtons);
		butTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTop();
			}
		});

		butHowTo = new JButton("Как играть");
		butHowTo.setFont(fontForButtons);
		butHowTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showHowTo();
			}
		});

		butExit = new JButton("Выход");
		butExit.setFont(fontForButtons);
		butExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		pane = new JPanel();
		setBounds(100, 100, Main.FRAME_WIDTH, Main.FRAME_HEIGHT);
		setResizable(false);
		pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		pane.setLayout(null);
		butNew.setBounds(320, 205, 160, 40);
		pane.add(butNew);
		butTop.setBounds(320, 255, 160, 40);
		pane.add(butTop);
		butHowTo.setBounds(320, 305, 160, 40);
		pane.add(butHowTo);
		butExit.setBounds(320, 355, 160, 40);
		pane.add(butExit);
		setContentPane(pane);

		setVisible(true);
		repaint();
	}

	/**
	 * Запускает новую игру
	 */
	private void newGame() {
		new GameFrame(new Game());
		dispose();
	}

	/**
	 * Выводит лучший результат
	 */
	private void showTop() {
		String topLine = Files.getTop();
		if (topLine.isEmpty()) {
			topLine = "Лучшего результата ещё нет!";
		}
		JOptionPane.showMessageDialog(this, topLine, "Лучший результат", JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Выводит справку о игре
	 */
	private void showHowTo() {
		JOptionPane.showMessageDialog(this,
				"<html><h1 style=\"text-align: center;\"><strong> Игра \"Спаси Самурая\" </strong></h1><br><p>Помоги храброму самураю защитить свой дом от нападения агресивных варваров!</p><br><p>Отбивайся от вражеских лучников своим щитом так долго, как сможешь!</p><br><p>Используй NUM9, NUM3, NUM1, NUM7 или E, C, Z, Q чтобы поворачиваться к стрелам.<br>С помощью клавиш NUM5 и S ты можешь поставить игру на паузу.</p><br><p>Вперёд, <em> Samurai </em> , сражайся с честью!</p>",
				"Как играть", JOptionPane.INFORMATION_MESSAGE);
	}

}
