# Домашнее задание. Переменные многопоточной программы. ThreadLocal. Atomics. Volatile.
___
## Задача 1. Самая бесполезная коробка 
https://github.com/MslFox/ThreadLocal.Atomics.Volatile/blob/f5b7f5500f99536b11ffa643155675ac343587d4/Main.java#L18
### Описание
Реализация игрушки [cамая бесполезная коробка]( http://www.youtube.com/watch?v=tGCW8xftdOA). Один поток будет выключать тумблер, а второй будет эмулировать пользователя, то есть включать тумблер с какой-то периодичностью

### Работа программы
Создание поток-пользователь и поток-игрушка
Поток-пользователь раз в несколько секунд включает тумблер
Поток-игрушка как только обнаруживает включение - выключает тумблер
Поток-пользователь, после нескольких итераций завершает выполнение
Главный поток (main) после завершения потока-пользователя, останавливает поток-игрушку
### Требования к программе
- Включение и выключение тумблера должно сопровождаться выводами в консоль
- Все задержки (кол-во итераций работы потока, периодичность включения) должны быть оформлены в константах
### Дополнительно
- Тумблер используется более, чем одним потоком
- Чтобы отловить завершение потока-пользователя, использовать join()
- В потоке-игрушке нужно реализовать возможность прерывания потока 
____
## Задача 2. Отчет для налоговой 
https://github.com/MslFox/ThreadLocal.Atomics.Volatile/blob/f5b7f5500f99536b11ffa643155675ac343587d4/Main.java#L48
### Описание
У вас есть 3 магазина в городе, которые продают продукты. Вам нужно каждый день при закрытии кассы подавать общий отчет в налоговую службу по своим магазинам. Так как магазины закрываются одновременно - выручка тоже считается одновременно по всем магазинам.

### Работа программы
- Генерация 3 массивов целых положительных чисел
- Создание трех потоков, которые суммируют выручку (каждый по своему массиву) в общий отчет
- Главный поток ждет завершения всех рассчетов и печатает общий итог в консоль
### Дополнительно
- Для записи подсчета выручки используйте LongAdder
