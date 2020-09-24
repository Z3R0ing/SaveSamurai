using System;
using System.Drawing;
using System.IO;
using System.Windows.Forms;

namespace SaveSamurai.Data
{
    public static class Files
    {
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
        public static Image[] getTextures(int count, String filesName)
        {
            Image[] images = new Image[count];
            int i = 0;
            for (i = 0; i < count; i++)
            {
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
        public static Image getTexture(String filesName)
        {
            Image image = null;
            try
            {
                image = Image.FromFile("C:\\Users\\User\\source\\repos\\Save Samurai\\Save Samurai\\res\\" + filesName + ".png");
            }
            catch (IOException ex)
            {
                MessageBox.Show("File not found %\\res\\" + filesName + ".png ", "Error!",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                Environment.Exit(2);
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
        public static void changeTop(int score, String name)
        {
            try
            {
                File.WriteAllText("C:\\Users\\User\\source\\repos\\Save Samurai\\Save Samurai\\res\\top.txt", score + " - " + name);
                MessageBox.Show("Ваш рекорд сохранён!", "Успешно",
                    MessageBoxButtons.OK, MessageBoxIcon.Information);
                /*File file = new File("res\\top.txt");
                if (!file.exists())
                {
                    file.createNewFile();
                }
                BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file, false));
                bufferWriter.write(score + " - " + name);
                bufferWriter.close();
                JOptionPane.showMessageDialog(null, "Ваш рекорд сохранён!", "Успешно", JOptionPane.INFORMATION_MESSAGE);*/
            }
            catch (IOException e)
            {
                MessageBox.Show("Ваш рекорд не сохранён!", "Ошибка",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        /**
         * Возвращает лучший результат
         * 
         * @return top result
         */
        public static String getTop()
        {
            String path = "C:\\Users\\User\\source\\repos\\Save Samurai\\Save Samurai\\res\\top.txt";
            try
            {
                if (File.Exists(path))
                {
                    var res = File.ReadAllText(path);
                    if (res == null)
                    {
                        return "";
                    } else
                    {
                        return res;
                    }
                } else
                {
                    File.Create(path);
                    return "";
                }
            }
            catch (IOException exc)
            {
                MessageBox.Show("Не удаётся получить доступ к файлу рекордов!!!", "Ошибка",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                return "";
            }
        }
    }
}
