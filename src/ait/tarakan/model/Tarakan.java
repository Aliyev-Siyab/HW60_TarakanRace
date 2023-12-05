package ait.tarakan.model;
import java.util.Random;


// Класс Tarakan, представляющий поток таракана
class Tarakan extends Thread {
    private final int tarakanNumber; // Номер таракана
    private final int distance; // Дистанция (количество итераций), которую таракан должен преодолеть
    public static volatile int winner = -1; // Переменная для отслеживания победителя
    private static Object lock = new Object(); // Объект блокировки для синхронизации

    // Конструктор класса Tarakan
    Tarakan(int tarakanNumber, int distance) {
        this.tarakanNumber = tarakanNumber;
        this.distance = distance;
    }

    @Override
    public void run() {
        Random random = new Random();
        int currentPosition = 0; // Текущая позиция таракана на дистанции

        // Цикл итераций таракана
        while (currentPosition < distance && winner == -1) {
            System.out.println("Таракан #" + tarakanNumber + " на позиции " + currentPosition);

            currentPosition++; // Увеличение позиции

            try {
                int sleepTime = random.nextInt(4) + 2; // Случайное время сна от 2 до 5 миллисекунд
                Thread.sleep(sleepTime); // Пауза потока
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Если таракан завершил все итерации и победитель еще не объявлен
            if (currentPosition == distance && winner == -1) {
                synchronized (lock) { // Используем синхронизацию для обеспечения атомарности операции
                    if (winner == -1) {
                        winner = tarakanNumber; // Устанавливаем номер таракана как победителя
                        System.out.println("Поздравляем таракана #" + winner + " (победитель)");
                    }
                }
            }
        }
    }
}

