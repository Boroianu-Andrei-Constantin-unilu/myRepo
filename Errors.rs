use std::fs::File;

fn main() {
    // unrecoverable errors

    println!("Hello, World!");

    // Explicitly exit the program with an unrecoverable error
    panic!("Crash");

    // let numbers = [1, 2 ,3];
    // println!("unknown index value = {}", numbers[3]);

    // recoverable errors

    let data_result = File::open("data.txt");

    // using match for Result type
    let data_file = match data_result {
        Ok(file) => file,
        Err(error) => panic!("Problem opening the data file: {:?}", error),
    };

    println!("Data file", data_file);
}