use std::fs::File;
use std::fs::OpenOptions;
use std::io::Read;
use std::io::Write;
use std::fs;

fn main() {
    // Create a file
    let mut data_file = File::create("data.txt").expect("creation failed");

    // Write contents to the file
    data_file.write("Hello, World!".as_bytes()).expect("write failed");

    println!("Created a file data.txt");

    // Open a file in read only mode in the local file system
    let data_result = File::open("data.txt");

    // Reading a file returns a Result enum
    // Result can be a file or an error
    let data_file = match data_result {
        Ok(file) => file,
        Err(error) => panic!("Problem opening the data file: {:?}", error),
    };

    println!("Data file: {:?}", data_file);

    // Read a file in the local file system
    let mut data_file = File::open("data.txt").unwrap();

    // Create an empty mutable string
    let mut file_content = String::new();

    // Copy contents of file to a mutable string
    data_file.read_to_string(&mut file_content).unwrap();

    println!("File content: {:?}", file_content);

    // Open a file with append option
    let mut data_file = OpenOptions::new()
        .append(true)
        .open("data.txt")
        .expect("cannot open file");

    // Write to a file
    data_file
        .write("I am learning Rust!".as_bytes())
        .expect("write failed");

    println!("Appended content to a file");

    // Remove a file
    fs::remove_file("data.txt").expect("could not remove file");
    
    println!("Removed file data.txt");
}