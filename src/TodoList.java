import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TodoList {
    static Scanner scanner = new Scanner(System.in);
    static List<String> todoList = new ArrayList<>();
    static final String FILE_NAME = "TodoList.txt";

    public static void main(String[] args) {
        // Launch the application
        startTodo();
    }

    public static void startTodo() {
        // Request for action
        System.out.println("Добро пожаловать в список дел (TODO List)");
        readTasksFromFile();

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
                        saveTasksToFile();
                        break;
                    case 3:
                        removeTask();
                        saveTasksToFile();
                        break;
                    case 4:
                        saveTasksToFile();
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
        // Menu output
        System.out.println("\nTODO");
        System.out.println("1 - Показать все задачи");
        System.out.println("2 - Добавить задачу");
        System.out.println("3 - Удалить задачу");
        System.out.println("4 - Выйти");
        System.out.println("Выберете одно из действий:");
    }

    public static void showAllTasks() {
        // Show all available tasks
        if (todoList.isEmpty()) {
            System.out.println("В списке ещё нет задач.");
            return;
        }

        System.out.println("Текущие задачи:");
        for (int i = 0; i < todoList.size(); i++) {
            System.out.printf("%s) %s.", i + 1, todoList.get(i));
        }
    }

    public static void addTask() {
        // Adding a new task
        System.out.println("Введите название новой задачи:");
        String task = scanner.nextLine();
        todoList.add(task);
        System.out.printf("Задача: (%s) - успешно добавлена.\n", task);
    }

    public static void removeTask() {
        // Deleting an existing task
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
                System.out.printf("Задача: (%s) - успешно удалена.\n", currentTask);
            } else {
                System.out.println("Задачи с таким номером не существует.");
            }
        } catch (InputMismatchException exc) {
            System.out.println("Ошибка: ожидалось число.");
            scanner.nextLine();
        }
    }

    public static void saveTasksToFile() {
        // Saving tasks to a file
        try {
            Files.write(Paths.get(FILE_NAME), todoList);
            System.out.printf("Успешно сохранено в %s\n", FILE_NAME);
        } catch (IOException exc) {
            System.out.printf("Ошибка сохранения: %s\n", exc);
        }
    }

    public static void readTasksFromFile() {
        // Loading tasks from a file
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            todoList.clear();
            todoList.addAll(lines);
            System.out.printf("Задачи успешно загружены из файла %s\n", FILE_NAME);
        } catch (NoSuchFileException exc) {
            System.out.printf("Ошибка загрузки: файл %s не найден.\n", FILE_NAME);
        } catch (IOException exc) {
            System.out.printf("Ошибка загрузки: %s\n", exc);
        }
    }
}
