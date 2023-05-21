fn main() {
    // define a Person struct
    struct Person {
        name: String,
        age: u8,
        height: u8
    }
    
    // instantiate Person struct
    let person = Person {
        name: String::from("John Doe"),
        age: 18,
        height: 178
    };
    
    // access value of name field in Person struct
    println!("Person name = {}", person.name);

    // access value of age field in Person struct
    println!("Person age = {}", person.age);

    // access value of height field in Person struct
    println!("Person height = {}", person.height);

    // destructure Person struct into name, age and height variables
    let Person { name, age, height } = person;
    
    println!("Person name = {}", name);
    println!("Person age = {}", age);
    println!("Person height = {}", height);
}