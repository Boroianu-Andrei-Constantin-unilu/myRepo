fn main() {
    // usage of for loop
    for i in 1..6 {
        println!("{}", i);
    }

    let mut sum = 0;
    
    // for loop to iterate over first 10 natural numbers
    for i in 1..11 {
        sum += i;
    }
    
    println!("Sum: {}", sum);
}