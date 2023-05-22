// import HashMap from Rust standard collections library
use std::collections::HashMap;

fn main() {
    // create a new HashMap
    let info: HashMap<i32, String> = HashMap::new();
    
    println!("HashMap = {:?}", info);

    // add elements to HashMap
    let mut fruits: HashMap<i32, String> = HashMap::new();
    
    // add key-value in a hashmap
    fruits.insert(1, String::from("Apple"));
    fruits.insert(2, String::from("Banana"));
    
    println!("fruits = {:?}", fruits);

    // access values in a hashmap
    let first_fruit = fruits.get(&1);
    let second_fruit = fruits.get(&2);
    let third_fruit = fruits.get(&3);
    
    println!("first fruit = {:?}", first_fruit);
    println!("second fruit = {:?}", second_fruit);
    println!("third fruit = {:?}", third_fruit);

    println!("fruits before remove operation = {:?}", fruits);

    // remove value in a hashmap
    fruits.remove(&1);
    
    println!("fruits after remove operation = {:?}", fruits);

    println!("Before update = {:?}", fruits);

    // change elements of HashMap
    
    let mut fruits2: HashMap<i32, String> = HashMap::new();
    
    // insert values in a hashmap
    fruits2.insert(1, String::from("Apple"));
    fruits2.insert(2, String::from("Banana"));
    
    println!("Before update = {:?}", fruits2);
    
    // change value of hashmap with key of 1
    fruits2.insert(1, String::from("Mango"));
    
    println!("After update = {:?}", fruits2);

    // loop and print values of hashmap using values() method
    for fruit in fruits2.values() {
        println!("{}", fruit)
    }
    
    // print the length of hashmap using len() method
    println!("Length of fruits = {}", fruits2.len());
}