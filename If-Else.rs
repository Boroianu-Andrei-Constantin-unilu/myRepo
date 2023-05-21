fn main() {
    let x = 7;

    // example of boolean expression
    let condition = x > 5;

    println!("condition is {}", condition);

    let number = 10;
   
    // condition to check if number is greater than zero
    if number > 0 {
        println!("{} is greater than 0", number);
    }

    let number2 = -2;
   
    // condition to check if number is greater than zero
    if number > 0 {
        println!("{} is greater than 0", number2);
    } else {
        println!("{} is less than or equal to 0", number2);
    }

    let number3 = -2;
   
    if number3 > 0 {
        println!("{} is positive", number3);
    } else if number3 < 0 {
        println!("{} is negative", number3);
    } else {
        println!("{} is equal to 0", number3);
    }

    let number4 = -2;
    
    if number4 < 0 {
        // if outer condition evaluates to true evaluate the inner condition
        if number4 == -2 {
            println!("The current number is -2");
        } else {
            println!("The current number is {}", number4);
        }
    }
   
    println!("End of program")
}