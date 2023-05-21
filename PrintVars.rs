fn main() {
    let age = 31;
    let name = "Jack"
  
    // print the variable using println!
    println!("Age = {}", age);

    // print the variable using print!
    print!("Age = {}", age);

    // print the variables using println!
    println!("Name = {}, Age = {}", name, age);
    println!("Name = {0}, Age = {1}", name, age);
    println!("Name = {name}, Age = {age}");

    // print the sentences on a line
    print!("Rust is fun!");
    print!("I love Rust programming.");

    // ...or multiple lines
    println!("Rust is fun!");
    println!("I love Rust programming.");
    
    print!("Rust is fun!\nI love Rust programming.");
}