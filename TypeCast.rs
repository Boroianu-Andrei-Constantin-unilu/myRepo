fn main() {
    // assign a floating point f64 value to decimal variable
    let decimal: f32 = 64.31;

    // convert decimal variable to u16 integer type using as keyword
    let integer = decimal as u16;

    println!("decimal = {}", decimal);
    println!("integer = {}", integer);

    let character: char = 'A';

    // convert char type to u8 integer type
    let integer2 = character as u8;

    println!("character = {}", character);
    println!("integer2 = {}", integer2);

    // only u8 integer data type can be converted into char
    let integer3: u8 = 65;
  
    // convert integer to char using the as keyword
    let character2 = integer3 as char;

    println!("integer3 = {}" , integer3);
    println!("character2 = {}", character2);

    let boolean1: bool = false;
    let boolean2: bool = true;
  
    // convert boolean type to integer
    let integer4 = boolean1 as i32;
    let integer5 = boolean2 as i32;

    println!("boolean1 = {}", boolean1);
    println!("boolean1 = {}", boolean2);
    println!("integer4 = {}", integer4);
    println!("integer5 = {}", integer5);
}