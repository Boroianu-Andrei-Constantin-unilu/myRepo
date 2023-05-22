fn main() {
    let x = 2;

    // use of match expression to pattern match against variable x
    match x {
        1 => println!("x is 1"),
        2 => println!("x is 2"),
        _ => println!("x is something else"),
    }

    enum Color {
        Red,
        Green,
        Blue,
    }

    let my_color = Color::Green;
    let my_color1 = Color::Red;
    let my_color2 = Color::Blue;

    // use of match expression to match against an enum variant
    match my_color {
        Color::Red => println!("The color is red"),
        Color::Green => println!("The color is green"),
        Color::Blue => println!("The color is blue"),
    }

    let my_option: Option<i32> = Some(222);
    
    // use of match expression to match Option type
    match my_option {
        Some(value) => println!("The option has a value of {}", value),
        None => println!("The option has no value"),
    }

    let my_result: Result<i32, &str> = Ok(100);

    // use of match expression to match Result type
    match my_result {
        Ok(value) => println!("The result is {}", value),
        Err(error) => println!("The error message is {}", error),
    }

    let my_option2: Option<i32> = Some(222);

    // use of if let expression on the Option type
    if let Some(value) = my_option2 {
        println!("The option has a value of {}", value);
    } else {
        println!("The option has no value");
    }
}