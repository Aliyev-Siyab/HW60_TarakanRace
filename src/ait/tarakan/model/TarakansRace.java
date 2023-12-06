package ait.tarakan.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Основной класс приложения
public class TarakansRace {
    public static void main(String[] args) { // Основной метод приложения
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество тараканов: ");
        int numberOfTarakans = scanner.nextInt(); // Получаем количество тараканов от пользователя

        System.out.print("Введите расстояние (количество итераций): ");
        int distance = scanner.nextInt(); // Получаем дистанцию от пользователя

        scanner.close();

        List<Tarakan> tarakans = new ArrayList<>(); // Список для хранения тараканов

        for (int i = 1; i <= numberOfTarakans; i++) { // Создаем и запускаем потоки для каждого таракана
            Tarakan tarakan = new Tarakan(i, distance);
            tarakans.add(tarakan);
            tarakan.start();
        }

        for (Tarakan tarakan : tarakans) { // Ожидаем завершения всех потоков тараканов
            try {
                tarakan.join();
                System.out.println("Таракан #" + tarakan.tarakanNumber + " завершил гонку");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Поздравляем таракана #" + Tarakan.winner + " (победитель)"); // Выводим сообщение о победителе
    }
}