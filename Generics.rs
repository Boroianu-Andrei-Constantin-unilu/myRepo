use std::collections::HashMap;

fn main() {
    // Create a HashMap with types i32 and &str
    let mut numbers: HashMap<i32, &str> = HashMap::new();

    // Insert values to numbers HashMap
    numbers.insert(1, "One");
    numbers.insert(2, "Two");

    println!("Numbers: {:?}", numbers);
    
    // Create a HashMap with types &str and &str   
    let mut language_codes: HashMap<&str, &str> = HashMap::new();

    // Insert values to language_codes HashMap
    language_codes.insert("EN", "English");
    language_codes.insert("NE", "Nepali");
    
    println!("Language Codes: {:?}", language_codes);

    // defining a struct with generic data type
    #[derive(Debug)]
    struct Point<T> {
        x: T,
        y: T,
    }
    
    // initializing a generic struct with i32 data type
    let int_point = Point { x: 1, y: 2 };
    
    // initializing a generic struct with f32 data type
    let float_point = Point { x: 1.1, y: 2.2 };
    
    println!("int_point: {:?}", int_point);
    println!("float_point: {:?}", float_point);

    // generic function to find minimum between two inputs
    fn min<T: PartialOrd>(a: T, b: T) -> T {
        if a < b {
            return a;
        } else {
            return b;
        }
    }

    // call generic function with integer type as parameters    
    let result1 = min(2, 7);

    // call generic function with float type as parameters
    let result2 = min(2.1, 1.1);
    
    println!("Result1 = {}", result1);
    println!("Result2 = {}", result2);
}