use std::io; // import io module
use std::fs; // import module to handle file system

#[derive(Debug)]
struct ToDoItem {
    id: u8,
    title: String,
    description: String,
    status: bool,
}

// 1. Changed parameter to a mutable Vector reference so we can use .push()
fn add_task(task_list: &mut Vec<ToDoItem>) {
    let mut title = String::new();
    let mut description = String::new();
    let read = io::stdin();

    // Calculate ID based on current length
    let id: u8 = task_list.len() as u8 + 1;

    println!("What is the task title: ");
    read.read_line(&mut title).expect("Failed to read");

    println!("Give a description: ");
    read.read_line(&mut description).expect("Failed to read");

    let new_task = ToDoItem {
        id: id,
        // .trim().to_string() removes the "Enter" key (\n) from the end of the input
        title: title.trim().to_string(),
        description: description.trim().to_string(),
        status: false,
    };

    // 2. Actually add the item to the list
    task_list.push(new_task);
    println!("Task added successfully!\n");
}

// 3. Changed parameter to a slice, which is fine for just reading data
fn read_list(task_list: &[ToDoItem]) {
    println!("\n--- Current Tasks ---");

    if task_list.is_empty() {
        println!("Your to-do list is empty!\n");
        return;
    }

    // 4. Moved the status check inside the loop to check each specific item
    for task in task_list {
        let status_text = if task.status { "Done" } else { "Yet to be done" };

        println!(
            "{}. {}\n Description: {}\n Status: {}\n",
            task.id, task.title, task.description, status_text
        );
    }
}

// Function to save tasks to a file
fn write_file(task_list: &[ToDoItem]) {
    let data = format!("{:?}", task_list);
    match fs::write("Tasks.txt", data) {
        Ok(_) => println!("Tasks saved to Tasks.txt"),
        Err(e) => println!("Failed to write file: {}", e),
    }
}

fn main() {
    // 5. Moved the list OUTSIDE the loop so it doesn't reset every time
    let mut list: Vec<ToDoItem> = vec![];
    let read = io::stdin();

    loop {
        let mut cmd = String::new();

        println!("1. Add Task");
        println!("2. List of Tasks");
        println!("3. Exit");
        println!("Enter a number: ");

        read.read_line(&mut cmd).expect("Failed to read");

        // 6. .trim() removes the hidden newline character before matching
        match cmd.trim() {
            "1" => add_task(&mut list),
            "2" => read_list(&list),
            "3" => {
                write_file(&list);
                println!("Exiting program...");
                break; // Exit the loop to end the program
            },
            _ => println!("Invalid command, please try again.\n"),
        }
    }
}