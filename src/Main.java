
void main() {

    ArrayList<Task> taskList = new ArrayList<>();
    int userChoice = 1;
    Scanner userInput = new Scanner(System.in);

    System.out.println("Welcome to Task Tracker.\n");

    while (userChoice != 4) {

        System.out.println("Select an option: \n1) Create a new task\n2) View your tasks\n3) Edit a task\n4) End program");
        userChoice = userInput.nextInt();
        userInput.nextLine(); // consume newline left over by nextInt()

        switch (userChoice) {
            // create task
            case 1:
                addTask(userInput, taskList);
                break;

            // view one or multiple tasks
            case 2:
                viewTask(userInput, taskList);
                break;

            // update an existing task
            case 3:
                updateTask(userInput, taskList);
                break;

            // end program
            case 4:
                break;

            default:
                System.out.println("Invalid input.\n");
                break;
        }

    }

    userInput.close();
    System.out.println("\nGoodbye.");

}

// Creates a new task
void addTask(Scanner input, ArrayList<Task> taskList) {

    System.out.println("\nDescribe your new task:");
    String newTaskDescription = input.nextLine();

    Task newTask = new Task(taskList.size() + 1, newTaskDescription);

    System.out.println("Task created!\n");
    taskList.add(newTask);

}

// Displays the details of one specified task
void viewTask(Scanner scanner, ArrayList<Task> taskList) {

    if (!taskList.isEmpty()) {
        System.out.print("\nChoose a task to view:\n[ ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(taskList.get(i).id);
            if (i < taskList.size() - 1) System.out.print(" | ");
        }
        System.out.println(" ] [ 0 => All Tasks ]");

        int chosenId = scanner.nextInt();
        scanner.nextLine();

        if (chosenId == 0) viewAllTasks(taskList);
        else System.out.println("> " + taskList.get(chosenId - 1).description + "\n");

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

void updateTask(Scanner input, ArrayList<Task> taskList){

    System.out.println("\nWhich task would you like to edit?");
    System.out.print("[ ");
    for (int i = 0; i < taskList.size(); i++){
        if (i > 0) System.out.print(" | ");
        System.out.print(i + 1);
    }
    System.out.print(" ]\n");
    int id = input.nextInt();
    input.nextLine();

    System.out.println("\nDescribe your task:");
    String newTaskDescription = input.nextLine();

    Task newTask = new Task(id, newTaskDescription);

    taskList.set(id - 1, newTask);
    System.out.println("Task updated!\n");

}
