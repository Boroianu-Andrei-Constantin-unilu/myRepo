fn main() {
    let a = 20;
    let b = 2;

    // add two variables using + operator
    let x = a + b;
    println!("{} + {} = {}", a, b, x);

    // subtract two variables using - operator
    let y = a - b;
    println!("{} - {} = {}", a, b, y);

    // multiply two variables using * operator
    let z = a * b;
    println!("{} * {} = {}", a, b, z);

    let dividend = 21;
    let divisor = 8;

    // arithmetic division using / operator with integers
    let division = dividend / divisor;

    println!("{} / {} = {}", dividend, divisor, division);

    let dividend2 = 21.0;
    let divisor2 = 8.0;

    // arithmetic division using / operator with floating point values
    let division2 = dividend2 / divisor2;

    println!("{} / {} = {}", dividend2, divisor2, division2);

    let dividend3 = 21;
    let divisor3 = 8;

    // arithmetic remainder using % operator
    let remainder3 = dividend3 % divisor3;
  
    println!("{} % {} = {}", dividend3, divisor3, remainder3);

    // compound assignment operator
    let mut a = 2;
  
    // arithmetic addition and assignment
    a += 3;

    println!("a = {}", a);

    // comparison operators
    let a = 7;
    let b = 3;
    
    // use of comparison operators
    let c = a > b;
    let d = a < b;
    let e = a == b;
    
    println!("{} >= {} is {}", a, b, c);
    println!("{} <= {} is {}", a, b, d);
    println!("{} == {} is {}", a, b, e);

    // logical operators
    let a = true;
    let b = false;
    
    // logical AND operation
    let c = a && b;

    // logical OR operation
    let d = a || b;

    // logical NOT operation
    let e = !a;
    
    println!("{} && {} = {}", a, b, c);
    println!("{} || {} = {}", a, b, d);
    println!("!{} = {}", a, e);
}