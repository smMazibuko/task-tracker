
void main(){

    ArrayList<Task> taskList = new ArrayList<Task>();
    int currentIndex = 0;
    int userChoice;

    System.out.println("Welcome to Task Tracker.\n");

    Scanner mainScanner = new Scanner(System.in);

    System.out.println("Select an option: \n1) Create new task.");
    userChoice = mainScanner.nextInt();

    if (userChoice == 1){
        taskList.add(addTask(currentIndex + 1));
    }

    System.out.println("\nGoodbye");

}

Task addTask(int id){
    Scanner input = new Scanner(System.in);

    System.out.println("\nDescribe your new task:");
    String newTaskDescription = input.nextLine();

    Task newTask = new Task(id, newTaskDescription);

    System.out.println("Task created!");
    input.close();
    return newTask;
}