// define a function
fn greet() {
    println!("Hello, World!");
}

// function to add two numbers
fn add() {
    let a = 5;
    let b = 10;

    let sum = a + b;

    println!("Sum of a and b = {}", sum);
}

// function with parameters

fn add2(a: i32, b: i32) {
    let sum = a + b;
    
    println!("Sum of a and b = {}", sum);
}

fn add3(a: i32, b: i32) -> i32 {
    let sum = a + b;

    // return a value from the function
    return sum;
}

fn main() {
    // function call
    greet();
    add();
    add2(2, 11);

    let sum = add3(3, 5);
    println!("Sum of a and b = {}", sum);
}