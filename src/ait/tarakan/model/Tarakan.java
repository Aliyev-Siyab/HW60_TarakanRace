package ait.tarakan.model;

import java.util.Random;

// Класс Tarakan, представляющий поток таракана

class Tarakan extends Thread {
    final int tarakanNumber; // Номер таракана
    private final int distance; // Дистанция (количество итераций), которую таракан должен преодолеть
    static volatile int winner = -1; // Переменная для отслеживания победителя
    private static Object lock = new Object(); // Объект блокировки для синхронизации

    Tarakan(int tarakanNumber, int distance) { // Конструктор класса Tarakan
        this.tarakanNumber = tarakanNumber;
        this.distance = distance;
    }

    @Override
    public void run() { // Переопределенный метод run() для выполнения потока таракана
        Random random = new Random();
        int currentPosition = 0; // Текущая позиция таракана на дистанции

        while (currentPosition < distance) { // Цикл для итераций таракана
            System.out.println("Таракан #" + tarakanNumber + " на позиции " + currentPosition);
            currentPosition++; // Увеличение позиции

            try {
                int sleepTime = random.nextInt(4) + 2; // Случайное время сна от 2 до 5 миллисекунд
                Thread.sleep(sleepTime); // Пауза потока
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (currentPosition == distance) { // Проверка, завершил ли таракан гонку
                synchronized (lock) { // Используем синхронизацию для безопасности операции
                    if (winner == -1) { // Если победитель еще не объявлен
                        winner = tarakanNumber; // Устанавливаем номер таракана как победителя
                    }
                }
            }
        }
    }
}