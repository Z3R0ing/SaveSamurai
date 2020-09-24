using SaveSamurai.Data;
using SaveSamurai.Engine;
using System;
using System.Drawing;

namespace SaveSamurai.Logic
{
    class Arrow : GameObject
    {
        private static readonly int TEXTURE_WIDTH = 80;
        private static readonly int TEXTURE_HEIGHT = 40;

        private static readonly int TRACE_HEIGHT = 146;

        private static readonly int TRACE_WIDTH = 292;

        private static readonly int TOP_INDENT = 49;
        private static readonly int INDENT = 115;
        private static readonly int UP_TRACE_Y = TOP_INDENT;
        private static readonly int DOWN_TRACE_Y = UP_TRACE_Y + TRACE_HEIGHT + INDENT;

        private static Image[] images;

        /// Переменная рандома
        private static Random rand;

        /**
         * Переменная текущего направления 1 - Право верх, 2 - Право низ, 3 - Лево низ,
         * 4 - Лево верх.
         */
        private int direction = 1;
        private bool hidden;

        /**
         * 
         */
        static Arrow()
        {
            rand = new Random();
            // Определяем текстуры объекта
            images = Files.getTextures(2, "a");
        }

        /**
         * TODO Arrow description
         */
        public Arrow()
        {
            // Задаём координаты стрелы
            x = 0;
            y = 0;
            width = TEXTURE_WIDTH;
            height = TEXTURE_HEIGHT;
            setDirection(rand.Next(4));
            spawn();
            hidden = false;
        }

        /**
         * Будет ставить стрелу по координатам места в зависимости от направления
         */
        private void spawn()
        {
            // TODO comment what happened in #spawn
            SpawnPlace place = getSpawnPlace();
            x = place.x;
            y = place.y;
            switch (getDirection())
            {
                case 1:
                case 2:
                    texture = images[0];
                    break;
                case 3:
                case 4:
                    texture = images[1];
                    break;
            }
        }

        /**
         * Изменяет координаты стрелы в выбранном направлени c определённой скоростью
         * 
         * param speed
         *            - скорость
         */
        public void moove(int speed)
        {
            // TODO comment what happened in #moove
            switch (getDirection())
            {
                case 1:
                case 2:
                    x -= 2 * speed;
                    break;
                case 3:
                case 4:
                    x += 2 * speed;
                    break;
            }
            y += speed;
        }

        /**
         * Проверяет попала ли стрела в объект
         * 
         * param gameObject
         *            - объект цели
         * return true - если стрела хотя бы на один пиксель находится на объекте цели
         */
        public bool isHitObject(GameObject gameObject)
        {
            /*
             * Стрела попадает в объект, если она не находится ни с одной из четырёх сторон
             * объекта, поэтому необходимо проверить все четыре направления от объекта цели
             */

            bool isOver = false;
            bool isRight = false;
            bool isUnder = false;
            bool isLeft = false;

            // Если нижняя граница стрелы выше верхней границы объекта
            if (y + height <= gameObject.y)
            {
                isOver = true;
            }
            // Если левая граница стрелы правее правой границы объекта
            if (x >= gameObject.x + gameObject.width)
            {
                isRight = true;
            }
            // Если верхняя граница стрелы ниже нижней границы объекта
            if (y >= gameObject.y + gameObject.height)
            {
                isUnder = true;
            }
            // Если правая граница стрелы левее левой границы объекта
            if (x + width < gameObject.x)
            {
                isLeft = true;
            }

            if ((isOver || isRight || isUnder || isLeft) == false)
            {
                /*
                 * Если все проверки возвращают flase значит стрела не находится ни с одной из
                 * четырёх сторон объекта, следовательно стрела попала в объект
                 */
                return true;
            }
            else
            {
                /*
                 * Если хотя бы одна вернёт true значит стрела с какой-то из сторон объекта,
                 * следовательно в объект стрела не попала
                 */
                return false;
            }
        }

        /**
         * Ставит стрелу в нужное направление
         * 
         * param direction
         *            - направление стрелы
         */
        public void respawn()
        {
            // Устанавливаем новое направление
            setDirection(direction + (rand.Next(3) + 1));
            // Перестаём прятать
            hidden = false;
            // Спауним стрелу снова
            spawn();
        }

        /**
         * return the direction
         */
        public int getDirection()
        {
            return direction;
        }

        /**
         * param direction
         *            the direction to set
         */
        private void setDirection(int direction)
        {
            if (direction <= 0)
            {
                this.direction = 1;
            }
            else if (direction > 4)
            {
                int buf = direction % 4;
                if (buf == 0)
                {
                    this.direction = 4;
                }
                else
                {
                    this.direction = buf;
                }
            }
            else
            {
                this.direction = direction;
            }
        }

        private class SpawnPlace
        {
            public static SpawnPlace RightUp = new SpawnPlace(GameField.GAME_FIELD_WIDTH - TEXTURE_WIDTH, UP_TRACE_Y);
            public static SpawnPlace RightDown = new SpawnPlace(GameField.GAME_FIELD_WIDTH - TEXTURE_WIDTH, DOWN_TRACE_Y);
            public static SpawnPlace LeftDown = new SpawnPlace(0, DOWN_TRACE_Y);
            public static SpawnPlace LeftUp = new SpawnPlace(0, UP_TRACE_Y);
            public static SpawnPlace Hide = new SpawnPlace(-TEXTURE_WIDTH, -TEXTURE_HEIGHT);

            public int x, y;

            SpawnPlace(int x, int y)
            {
                this.x = x;
                this.y = y;
            }
        }


        /**
         * Возвращает место спауна взависимости от направления
         * 
         * return Место
         */
        private SpawnPlace getSpawnPlace()
        {
            int currentDirection = getDirection();
            switch (currentDirection)
            {
                case 1:
                    return SpawnPlace.RightUp;
                case 2:
                    return SpawnPlace.RightDown;
                case 3:
                    return SpawnPlace.LeftDown;
                case 4:
                    return SpawnPlace.LeftUp;
            }
            return null;
        }

        /**
         * Прячет стрелу
         */
        public void hide()
        {
            x = SpawnPlace.Hide.x;
            y = SpawnPlace.Hide.y;
            hidden = true;
        }

        /**
         * return hidden
         */
        public bool isHidden()
        {
            return hidden;
        }
    }
}
