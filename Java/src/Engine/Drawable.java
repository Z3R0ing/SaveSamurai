/**
 * author Z3R0ing
 */
package Engine;

import java.awt.Graphics;

/**
 * Интерфейс для объектов, которые можно рисовать
 */
public interface Drawable {
	
	/**
	* Метод прорисовки с помощью переменной Graphics
	*/
	void draw(Graphics gr);

}
