# Todo-List App
This is a command line todo application. The system will allow a user to add and track the status of their todos by due date, category, priority, and status (complete/incomplete). The application stores all todos in a CSV file. The CSV file is a plain text file, containing data organized into columns separated by a comma. The data in each column is enclosed in double quotes. The first line of the file contains the headers for each column.

## Todo data structure
A ​Todo​ consists of the following information:
- `text`​ - a description of the task to be done. This field is required.
- `completed`​ - indicates whether the task is completed or incomplete. If not specified,this field should be false by default. However, it should be possible to create a new ​Todo with ​completed​ set to true.
- `due` ​- a due date. This field is optional.
- `priority`​ - an `integer` indicating the priority of the todo.This field is optional. If the user chooses to specify a priority, it must be between 1 and 3, with 1 being the highest priority. If no priority is specified, the todo can be treated as lowest priority (i.e. 3).
- `category`​ - a user-specified `String` that can be used to group related todos, e.g. “school”, “work”, “home”. This field is optional.
*Sample input of todos can be found in `todos.csv` file.*

## Functionality
The system must support the following functionality:
- **Add a new todo**: The user must supply the information required by the ​Todo​ data structure. They can also choose to specify the optional information. When a new ​Todo​ is added, the CSV file will be updated.
- **Complete an existing todo**: The user set the completed status of an existing ​Todo​ to true. When the status is changed, the CSV file will be updated.
- **Display todos**: The user can request that the program display a list of ​Todos​. By default, all of the ​Todos should be printed but users can supply additional arguments to customize the list:
    - Filter the list to only include incomplete ​Todos​;
    - Filter the list to only include ​Todos​ with a particular category;
    - Sort the ​Todos​ by date (ascending) ;
    - Sort the ​Todos​ by priority (ascending).
The two filter arguments can be combined but only one sort can be applied at a time. For example, the user could request all incomplete ​Todos​ with category, “work”, sorted by date but the user cannot request all incomplete ​Todos​ sorted by date AND priority.

## Structure
- MVC Architecture
  - Model: model package 
  - View: view package 
  - Controller:  task package, command package
- Design Pattern
  - Abstract factory pattern 
    - Where: createSubTask() method in IMainTask interface, implemented in concrete MainTask classes (e.g. AddNewTodo, DisplayTodo) in task package 
    - Why: Hides the constructor behind abstraction; the method decides which subtype to return, not the client
  - Iterator 
    - Where: TodoCategoryIterator class in task package 
    - Why: Encapsulates the approach to find all Todos with a certain category in a single class
  - Builder Pattern 
    - Where: CommandLineOption class in command package 
    - Why: For this todo application, we can divide users’ input into four big categories presented by enums in UserSetting class in model package. Each big category has its own restrictions, for example, whether it has args or not, the length of args and so on. Using builder pattern can help us easily create the object of each specific tasks along with its own limitation
  - Singleton 
    - Where: static method createTodoList() in TodoList class in model package 
    - Why: ensures data consistency of TodoList throughout the lifetime of program 
    - Notes: In the current version of our application, createTodoList() isn’t of the real singleton pattern for the sake of writing unit tests, but it can be easily changed back to be a singleton once testing is completed.