/**
 * author User
 */
package Data;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Псевдо-статичный класс, в котором реализованны методы работы с файлами. Так
 * как Java не позволяет создать статичные классы верхнего уровня, установим
 * модификатор final во избежании расширения этого класса и сделаем недоступным
 * из вне конструктор.
 */
public final class Files {

	/**
	 * Изменяем модификатор доступа конструктора, во избежании создания экземпляров
	 * псевдо-статичного класса
	 */
	private Files() {

	}

	/**
	 * Считывает изображения текстур с диска.
	 * 
	 * Имена изображений текстур для объектов должны быть в формате
	 * "[filesName][0-count].png". Текстуры хранятся в папке ресурсов проекта "res".
	 * 
	 * @param images
	 *            - массив текстур
	 * @param count
	 *            - количество текстур
	 * @param filesName
	 *            - имя файлов текстуры
	 * @return Массив текстур в формате
	 */
	public static Image[] getTextures(int count, String filesName) {
		Image[] images = new Image[count];
		int i = 0;
		for (i = 0; i < count; i++) {
			images[i] = getTexture(filesName + i);
		}
		return images;
	}

	/**
	 * Считывает изображение с диска.
	 * 
	 * Имена изображений текстур для объектов должны быть в формате
	 * "[filesName].png". Текстуры хранятся в папке ресурсов проекта "res".
	 * 
	 * @param filesName
	 *            - имя файла текстуры
	 * @return Изображение по пути "[filesName].png"
	 */
	public static Image getTexture(String filesName) {
		Image image = null;
		try {
			image = ImageIO.read(new File("./res/" + filesName + ".png"));
		} catch (IOException loadTextureException) {
			JOptionPane.showMessageDialog(null, "File not found %\\res\\" + filesName + ".png ", null,
					JOptionPane.ERROR_MESSAGE);
			System.exit(2);
		}
		return image;
	}

	/**
	 * Сохраняет результат игрока в файл результатов
	 * 
	 * @param name
	 *            - имя игрока
	 * @param score
	 *            - набранные очки
	 */
	public static void changeTop(int score, String name) {
		try {
			File file = new File("./res/" + "top.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file, false));
			bufferWriter.write(score + " - " + name);
			bufferWriter.close();
			JOptionPane.showMessageDialog(null, "Ваш рекорд сохранён!", "Успешно", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Ваш рекорд не сохранён!", "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Возвращает лучший результат
	 * 
	 * @return top result
	 */
	public static String getTop() {

		try {
			File file = new File("./res/" + "top.txt");
			if (file.exists()) {
				BufferedReader bufferReader = new BufferedReader(new FileReader(file));
				String res = bufferReader.readLine();
				bufferReader.close();
				if (res == null) {
					return "";
				} else {
					return res;
				}
			}
			file.createNewFile();
			return "";
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Не удаётся получить доступ к файлу рекордов!!!", "Ошибка", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

}
