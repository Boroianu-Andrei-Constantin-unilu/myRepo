// A simple macro named `hello_world`
macro_rules! hello_world {
    // `()` indicates that the macro takes no argument
    () => {
        // The macro will expand into the contents of this block
        println!("Hello, World!")
    };
}

// A macro named `print_message`
macro_rules! print_message {
    // Match rule that takes an argument expression
    ($message:expr) => {
        println!("{}", $message)
    };
}

// A macro which uses repetitions
macro_rules! repeat_print {
    // match rule which matches multiple expressions in an argument
    ($($x:expr),*) => {
        $(
            println!("{}", $x);
        )*
    };
}

fn main() {
    // Call the hello_world macro
    // This call will expand into `println!("Hello, World!");`
    hello_world!()
    // Call the macro with an argument
    print_message!("I am learning Rust!");
    // Call the macro with multiple arguments
    repeat_print!(1, 2, 3);
}