using SaveSamurai.Engine;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace SaveSamurai.Logic
{
    public class Game : GameLogic
    {
        public static readonly int GAME_TICK = 33;
        public static readonly int MIN_DELAY = 22 * GAME_TICK;

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
        private bool isOver;
        /// Надпись GameOver
        private Banner overSign;

        /**
         * Конструктор запускающий новую игру
         */
        public Game()
        {
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
            overSign = new Banner("GAMEOVER", Color.Red);
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
            timer = new Timer();
            timer.Interval = GAME_TICK;
            timer.Tick += new System.EventHandler(this.timer_Tick);

            // Запускаем таймер
            timer.Start();

        }

        private void timer_Tick(object sender, EventArgs e)
        {
            update();
        }

        /**
         * Игровой цикл, вся игровая логика находится в этом методе
         */
        private void update()
        {
            // Перебераем стрелы
            for (int i = 0; i < arrows.Length; i++)
            {
                // Если стрела инициализированна
                if (arrows[i] != null)
                {
                    // Если стрела не спрятана
                    if (!arrows[i].isHidden())
                    {
                        // Если стрела попала в самурая
                        if (arrows[i].isHitObject(samurai))
                        {
                            // Проверяем поймал ли её самурай
                            if (samurai.getDirection() == arrows[i].getDirection())
                            {
                                // Добавляем очки
                                score.value++;
                                if (score.value % 5 == 0)
                                {
                                    addLevel();
                                }
                            }
                            else
                            {
                                // Отбираем жизнь
                                life.value--;
                            }
                            // Убираем стрелу
                            arrows[i].hide();
                        }
                        else
                        {
                            // Двигаем стрелу
                            arrows[i].moove(speed);
                        }
                    }
                    else
                    {
                        // Если задержка кончилась
                        if (delay < 0)
                        {
                            // Спауним стрелу
                            arrows[i].respawn();
                            // Возобновляем задержку
                            delay = mainDelay;
                        }
                        else
                        {
                            delay -= GAME_TICK;
                        }
                    }
                }
            }
            if (life.value < 1)
            {
                gameOver();
            }
            // Перерисовываем поле
            gameField.Invalidate();
        }

        private void gameOver()
        {
            isOver = true;
            timer.Stop();
            // Показываем надпись gameover
            overSign.setVisible(true);
            // Перерисовываем поле
            gameField.Invalidate();
        }

        private void addLevel()
        {
            level++;
            if (level <= 3)
            {
                arrows[level - 1] = new Arrow();
                arrows[level - 1].hide();
                gameField.add(arrows[level - 1]);
                mainDelay -= MIN_DELAY;
            }
            else if (level <= 6)
            {
                arrows[level - 4].hide();
            }
            else if (level == 10)
            {
                speed++;
            }
            else if (level == 15)
            {
                speed++;
            }
        }

        /**
         * Возвращает своё игровое поле
         * 
         * return the gameField
         */
        public GameField getGameField()
        {
            return gameField;
        }

        /**
         * Обработчик полученных от окна нажатий клавиш
         * 
         * key
         *            - код нажатой клавиши
         */
        public void sendKey(Keys key)
        {
            if (key == Keys.E || key == Keys.NumPad9)
            {
                samurai.turn(1);
            }
            if (key == Keys.C || key == Keys.NumPad3)
            {
                samurai.turn(2);
            }
            if (key == Keys.Z || key == Keys.NumPad1)
            {
                samurai.turn(3);
            }
            if (key == Keys.Q || key == Keys.NumPad7)
            {
                samurai.turn(4);
            }
            if (key == Keys.S || key == Keys.NumPad5)
            {
                if (timer.Enabled)
                {
                    pause();
                }
                else
                {
                    resume();
                }
            }
        }

        public void pause()
        {
            timer.Stop();
            // Показываем надпись паузы
            pauseSign.setVisible(true);
            // Перерисовываем поле
            gameField.Invalidate();
        }

        public void resume()
        {
            // Убираем надпись паузы
            pauseSign.setVisible(false);
            timer.Start();
        }

        public int getScore()
        {
            return score.value;
        }

        public bool IsOver()
        {
            return isOver;
        }
    }
}
