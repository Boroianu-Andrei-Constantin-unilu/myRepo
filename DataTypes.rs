fn main() {
    // Signed integer type 
    let x: i32 = -200;
    let y: i32 = 200;

    println!("x = {}", x);
    println!("y = {}", y);

    // Unsigned integer type (positive only!)
    let z: u32 = 300;

    println!("z = {}", z);

    // f32 floating point type
    let a: f32 = 3.1;

    // f64 floating point type
    let b: f64 = 45.0000031;

    println!("a = {}", a);
    println!("b = {}", b);

    // boolean type
    let flag1: bool = true;
    let flag2: bool = false;

    println!("flag1 = {}", flag1);
    println!("flag2 = {}", flag2);

    // char type
    let character: char = 'z';
    let special_character: char = '$';

    println!("character = {}", character);
    println!("special_character = {}", special_character);
    
    // automatic identification of type

    let t = 51;
    println!("t = {}", t);
}