fn main() {
    // closure that prints a text
    let print_text = || println!("Hello, World!");
    
    print_text();

    // closure with parameter

    // define a closure and store it in a variable
    let add_one = |x: i32| x + 1;
    
    // call closure and store the result in a variable
    let result = add_one(2);
    
    println!("Result = {}", result);

    // multi-line closure

    // define a multi-line closure
    let squared_sum = |x: i32, y: i32| {
    
        // find the sum of two parameters
        let sum: i32 = x + y;
        
        // find the squared value of the sum
        let result: i32 = sum * sum;
        
        return result;
    };
    
    // call the closure
    let result = squared_sum(5, 3);
    
    println!("Result = {}", result);

    // closure environment capture

    let num = 100;
    
    // A closure that captures the num variable
    let print_num = || println!("Number = {}", num);
    
    print_num();

    // variable not changed in closure

    let word = String::from("Hello");
    
    // immutable closure
    let print_str = || {
        println!("word = {}", word);
    };

    // immutable borrow is possible outside the closure
    println!("length of word = {}", word.len());
    
    print_str();

    // variable changed in closure

    let mut word = String::from("Hello");
    
    // mutable closure
    let mut print_str = || {
        // value of word is changed here
        word.push_str(" World!");
        println!("word = {}", word);
    };
     
     // cannot immutable borrow because the variable is borrowed as mutable inside the closure
     // println!("length of word = {}", word.len());
    
    print_str();

    // can immutable borrow because the closure has been already used
    println!("length of word = {}", word.len());

    // variable moved in closure

    let word = String::from("Hello");

    // immutable closure
    let print_str = || {
        // word variable is moved to a new variable
        let new_word = word;
        println!("word = {}", new_word);
    };

    print_str();

    // cannot immutable borrow because word variable has moved inside closure
    // println!("length of word = {}", word.len());

}