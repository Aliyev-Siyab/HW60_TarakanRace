package ait.tarakan.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Основной класс приложения
public class TarakansRaceAppl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество тараканов: ");
        int numberOfTarakans = scanner.nextInt(); // Получаем количество тараканов от пользователя

        System.out.print("Введите расстояние (количество итераций): ");
        int distance = scanner.nextInt(); // Получаем дистанцию от пользователя

        scanner.close();

        List<Tarakan> tarakans = new ArrayList<>(); // Список для хранения тараканов

        // Создаем и запускаем потоки для каждого таракана
        for (int i = 1; i <= numberOfTarakans; i++) {
            Tarakan tarakan = new Tarakan(i, distance);
            tarakans.add(tarakan);
            tarakan.start();
        }

        // Ожидаем завершения всех потоков тараканов
        for (Tarakan tarakan : tarakans) {
            try {
                tarakan.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Выводим сообщение о победителе
        System.out.println("Поздравляем таракана #" + Tarakan.winner + " (победитель)");
    }

}
