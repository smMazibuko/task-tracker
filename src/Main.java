
import java.util.ArrayList;
import java.util.Scanner;

void main(String[] args) {

    ArrayList<Task> taskList = new ArrayList<>();

    if (args.length == 0) {
        printUsage();
        return;
    }

    String command = args[0].toLowerCase();

    switch (command) {
        // task add "Wash dishes"
        case "add":
            if (args.length < 2) {
                System.out.println("Usage: task add \"<description>\"");
            } else {
                String description = joinArgs(args, 1);
                addTask(taskList, description);
            }
            break;

        // task all — view every task
        case "all":
            viewAllTasks(taskList);
            break;

        // task remove <id>
        case "remove":
            if (args.length < 2) {
                System.out.println("Usage: task remove <id>");
            } else {
                removeTask(taskList, parseId(args[1]));
            }
            break;

        // task edit <id>
        case "edit":
            if (args.length < 2) {
                System.out.println("Usage: task edit <id>");
            } else {
                updateTask(taskList, parseId(args[1]));
            }
            break;

        // task done <id>
        case "done":
            if (args.length < 2) {
                System.out.println("Usage: task done <id>");
            } else {
                markDone(taskList, parseId(args[1]));
            }
            break;

        // task help
        case "help":
            printUsage();
            break;

        // task <id> — view a single task by its number
        default:
            int id = parseId(command);
            if (id > 0) {
                viewTask(taskList, id);
            } else {
                System.out.println("Unknown command: " + command);
                printUsage();
            }
            break;
    }

}

// Creates a new task
void addTask(ArrayList<Task> taskList, String description) {
    Task newTask = new Task(taskList.size() + 1, description);
    taskList.add(newTask);
    System.out.println("Task created!\n");
}

// Displays the details of one specified task
void viewTask(ArrayList<Task> taskList, int id) {

    if (!taskList.isEmpty()) {
        if (id == 0) viewAllTasks(taskList);
        else System.out.println("> " + taskList.get(id - 1).description + "\n");

    } else {
        System.out.println("No tasks found.\n");
    }

}

// Displays every task the list
void viewAllTasks(ArrayList<Task> taskList) {

    System.out.println("\nAll Tasks:");

    for (int i = 0; i < taskList.size(); i++) {
        System.out.println((i + 1) + ". " + taskList.get(i).description);
    }

    System.out.println();

}

// Removes a task by ID
void removeTask(ArrayList<Task> taskList, int id) {
    Task task = findById(taskList, id);
    if (task == null) {
        System.out.println("Task #" + id + " not found.");
    } else {
        taskList.remove(task);
        System.out.println("Task #" + id + " removed.");
    }
}

void updateTask(ArrayList<Task> taskList, int id){

    Scanner input = new Scanner(System.in);
    System.out.println("\nDescribe your task:");
    String newTaskDescription = input.nextLine();

    Task newTask = new Task(id, newTaskDescription);

    taskList.set(id - 1, newTask);
    System.out.println("Task updated!\n");

}

// Marks a task as complete
void markDone(ArrayList<Task> taskList, int id) {
    Task task = findById(taskList, id);
    if (task == null) {
        System.out.println("Task #" + id + " not found.");
        return;
    }
    task.completeTask();
    System.out.println("Task #" + id + " marked as done.");
}

// Finds a task by its ID, returns null if not found
Task findById(ArrayList<Task> taskList, int id) {
    for (Task task : taskList) {
        if (task.id == id)
            return task;
    }
    return null;
}

// Joins args from a starting index into a single string
String joinArgs(String[] args, int from) {
    StringBuilder sb = new StringBuilder();
    for (int i = from; i < args.length; i++) {
        if (i > from) sb.append(" ");
        sb.append(args[i]);
    }
    return sb.toString();
}

// Safely parses an integer, returns -1 on failure
int parseId(String s) {
    try {
        return Integer.parseInt(s);
    } catch (NumberFormatException e) {
        return -1;
    }
}

// Prints the help / usage message
void printUsage() {
    System.out.println("""

            Task Tracker — Usage:
              task add "<description>"    Add a new task
              task all                    View all tasks
              task <id>                   View a single task
              task edit <id>              Edit a task's description
              task remove <id>            Remove a task
              task done <id>              Mark a task as complete
              task help                   Show this help message
            """);
}
