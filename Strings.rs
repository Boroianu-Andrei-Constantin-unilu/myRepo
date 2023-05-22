fn main() {
    // string creation using String::from() method
    let word = String::from("Hello, World!");

    println!("word = {}", word);

    // mutable string
    let mut word = String::from("cat");
    
    println!("original string = {}", word);
    
    // push a new string at the end of the initial string 
    word.push_str(" dog");
    
    println!("changed string = {}", word);

    // slice string

    let word2 = String::from("Hello, World!");

    // slicing a string
    let slice = &word2[0..5];

    println!("string = {}", word2);
    println!("slice = {}", slice);

    // iterate over string

    let str = String::from("Hello");
    
    // Loop through each character in a string using chars() method
    for char in str.chars() {
        println!("{}", char);
    }

    // create an empty string
    let mut word = String::new();
    
    println!("original string = {}", word);
    
    // append a string to the word variable
    word.push_str("Hello, World!");

    println!("changed string = {}", word);
}