
void main() {

    ArrayList<Task> taskList = new ArrayList<Task>();
    int userChoice = 1;
    Scanner userInput = new Scanner(System.in);

    System.out.println("Welcome to Task Tracker.\n");

    while (userChoice != 4) {

        System.out.println("Select an option: \n1) View all tasks\n2) Create a new task\n3) Edit a task\n4) End program");
        userChoice = userInput.nextInt();
        userInput.nextLine(); // consume newline left over by nextInt()

        switch (userChoice) {
            // view all tasks
            case 1:
                if (!taskList.isEmpty()) {
                    viewAllTasks(taskList);
                } else {
                    System.out.println("No tasks found.\n");
                }
                break;

            // create task
            case 2:
                addTask(userInput, taskList);
                break;

            // update task
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

// Displays the details of a task using an index
void viewTask(ArrayList<Task> taskList, int id) {

    System.out.println(id + ". " + taskList.get(id - 1));

}

// Displays every task the list
void viewAllTasks(ArrayList<Task> taskList) {

    System.out.println("\nAll Tasks:");

    for (int i = 0; i < taskList.size(); i++) {
        System.out.println((i + 1) + ". " + taskList.get(i).description);
    }

    System.out.println();

}

// Creates a new task
void addTask(Scanner input, ArrayList<Task> taskList) {

    System.out.println("\nDescribe your new task:");
    String newTaskDescription = input.nextLine();

    Task newTask = new Task(taskList.size(), newTaskDescription);

    System.out.println("Task created!\n");
    taskList.add(newTask);

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
