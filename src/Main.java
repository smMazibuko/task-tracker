
void main() {

    ArrayList<Task> taskList = new ArrayList<Task>();
    int currentIndex = 0;
    int userChoice = 1;
    Scanner mainScanner = new Scanner(System.in);

    System.out.println("Welcome to Task Tracker.\n");

    while (userChoice != 3) {

        System.out.println("Select an option: \n1) View all tasks\n2) Create a new task\n3) End program");
        userChoice = mainScanner.nextInt();

        switch (userChoice) {
            case 1:
                if (!taskList.isEmpty()) {
                    viewAllTasks(taskList);
                } else {
                    System.out.println("No tasks found.\n");
                }

            // bug: app breaks after creating one task
            case 2:
                taskList.add(addTask(currentIndex + 1));

            case 3:
                break;

            default:
                System.out.println("Invalid input.\n");
        }

    }

    mainScanner.close();
    System.out.println("\nGoodbye.");

}

// Creates a new task
Task addTask(int id) {

    Scanner input = new Scanner(System.in);

    System.out.println("\nDescribe your new task:");
    String newTaskDescription = input.nextLine();

    Task newTask = new Task(id, newTaskDescription);

    System.out.println("Task created!\n");
    input.close();
    return newTask;

}

// Displays the details of a task using an index
void viewTask(ArrayList<Task> taskList, int id) {

    System.out.println((id + 1) + ". " + taskList.get(id));

}

// Displays every task the list
void viewAllTasks(ArrayList<Task> taskList) {

    System.out.println("\nAll Tasks:\n");

    for (int i = 0; i < taskList.size(); i++) {
        System.out.println((i + 1) + ". " + taskList.get(i).description);
    }

}
