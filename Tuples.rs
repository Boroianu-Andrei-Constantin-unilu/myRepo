fn main() {
    // initialization of tuple with data type
    let tuple: (&str, f32, u8) = ("Rust", 3.14, 100);
    
    println!("Tuple contents = {:?}", tuple);

    // initialization of tuple without data type
    let tuple2 = ("Rust", "fun", 100);

    println!("Tuple contents = {:?}", tuple2);

    // access tuple elements

    let random_tuple = ("Hello", 200, 3.14);

    // accessing tuple element at index 0
    println!("Value at Index 0 = {}", random_tuple.0);
    
    // accessing tuple element at index 1
    println!("Value at Index 1 = {}", random_tuple.1);
    
    // accessing tuple element at index 2
    println!("Value at Index 2 = {}", random_tuple.2);

    // initialize a mutable tuple
    let mut mountain_heights = ("Everest", 8848, "Fishtail", 6993);
    
    println!("Original tuple = {:?}", mountain_heights);
    
    // change 3rd and 4th element of a mutable tuple
    mountain_heights.2 = "Lhotse";
    mountain_heights.3 = 8516;
    
    println!("Changed tuple = {:?}", mountain_heights);

    // destructure tuple

    let mixture = ("Hello, World!", 16, 2.71828);
    
    // destructuring a tuple
    let (message, number, float) = mixture;
    
    println!("message = {}", message);
    println!("number = {}", number);
    println!("float = {}", float);
}