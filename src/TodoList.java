import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TodoList {
    static Scanner scanner = new Scanner(System.in);
    static List<String> todoList = new ArrayList<>();

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        System.out.println("Добро пожаловать в список дел (TODO List)");

        while (true) {
            showMenu();

            try {
                int userChoice = scanner.nextInt();
                scanner.nextLine();

                switch (userChoice) {
                    case 1:
                        showAllTasks();
                        break;
                    case 2:
                        addTask();
                        break;
                    case 3:
                        removeTask();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Действия не существует.");
                }
            } catch (InputMismatchException exc) {
                System.out.println("Ошибка: ожидалось число.");
                scanner.nextLine();
            }
        }
    }

    public static void showMenu() {
        System.out.println("\nTODO");
        System.out.println("1 - Показать все задачи");
        System.out.println("2 - Добавить задачу");
        System.out.println("3 - Удалить задачу");
        System.out.println("4 - Выйти");
        System.out.println("Выберете одно из действий:");
    }

    public static void showAllTasks() {
        if (todoList.isEmpty()) {
            System.out.println("В списке ещё нет задач.");
            return;
        }

        System.out.println("Текущие задачи:");
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println(String.format("%s) %s.", i + 1, todoList.get(i)));
        }
    }

    public static void addTask() {
        System.out.println("Введите название новой задачи:");
        String task = scanner.nextLine();
        todoList.add(task);
        System.out.println(String.format("Задача: (%s) - успешно добавлена.", task));
    }

    public static void removeTask() {
        if (todoList.isEmpty()) {
            System.out.println("Нечего удалять, список пуст.");
            return;
        }
        showAllTasks();
        System.out.println("Введите номер задачи для удаления:");

        try {
            int task_index = scanner.nextInt() - 1;
            if (task_index >= 0 && task_index < todoList.size()) {
                String currentTask = todoList.get(task_index);
                todoList.remove(task_index);
                System.out.println(String.format("Задача: (%s) - успешно удалена.", currentTask));
            } else {
                System.out.println("Задачи с таким номером не существует.");
            }
        } catch (InputMismatchException exc) {
            System.out.println("Ошибка: ожидалось число.");
            scanner.nextLine();
        }
    }
}
