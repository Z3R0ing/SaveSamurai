/**
 * author Zeroing
 */
package Engine;

/**
 * Интерфейс игровой логики для GUI
 * Позволяет изменять игру, не изменяя существующее GUI
 */
public interface GameLogic {

	/**
	 * Обрабатывает нажатые клавиши
	 * @param key
	 */
	void sendKey(int key);

	/**
	 * Восстанавливает игру из паузы
	 */
	void resume();

	/**
	 * Возвращает количество набранных очков
	 */
	int getScore();

	/**
	 * Ставит игру на паузу
	 */
	void pause();
	
	boolean isOver();

	/**
	 * Возвращает игровое поле игры
	 * @return игровое поле
	 */
	GameField getGameField();

}
