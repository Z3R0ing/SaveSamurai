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
 * ������-��������� �����, � ������� ������������ ������ ������ � �������. ���
 * ��� Java �� ��������� ������� ��������� ������ �������� ������, ���������
 * ����������� final �� ��������� ���������� ����� ������ � ������� �����������
 * �� ��� �����������.
 */
public final class Files {

	/**
	 * �������� ����������� ������� ������������, �� ��������� �������� �����������
	 * ������-���������� ������
	 */
	private Files() {

	}

	/**
	 * ��������� ����������� ������� � �����.
	 * 
	 * ����� ����������� ������� ��� �������� ������ ���� � �������
	 * "[filesName][0-count].png". �������� �������� � ����� �������� ������� "res".
	 * 
	 * @param images
	 *            - ������ �������
	 * @param count
	 *            - ���������� �������
	 * @param filesName
	 *            - ��� ������ ��������
	 * @return ������ ������� � �������
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
	 * ��������� ����������� � �����.
	 * 
	 * ����� ����������� ������� ��� �������� ������ ���� � �������
	 * "[filesName].png". �������� �������� � ����� �������� ������� "res".
	 * 
	 * @param filesName
	 *            - ��� ����� ��������
	 * @return ����������� �� ���� "[filesName].png"
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
	 * ��������� ��������� ������ � ���� �����������
	 * 
	 * @param name
	 *            - ��� ������
	 * @param score
	 *            - ��������� ����
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
			JOptionPane.showMessageDialog(null, "��� ������ �������!", "�������", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "��� ������ �� �������!", "������", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * ���������� ������ ���������
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
			JOptionPane.showMessageDialog(null, "�� ������ �������� ������ � ����� ��������!!!", "������", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

}
