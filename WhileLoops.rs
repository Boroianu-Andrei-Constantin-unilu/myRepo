fn main() {
let mut counter = 1;

    // usage of while loop
    while counter < 6 {
        println!("{}", counter);
        
        counter += 1;
    }

    // variable to print multiplication table for
    let i = 2;
    
    // counter variable that starts at 1
    let mut j = 1;
    
    // while loop that runs for 10 iterations
    while j <= 10 {
        // multiply i and j
        let mult = i * j;
        
        // print multiplication result on each iteration
        println!("{} * {} = {}", i, j, mult);

        // increase value of counter variable j
        j += 1;
    }

    // outer loop counter
    let mut i = 1;
    
    // outer loop
    while i <= 5 {
        // inner loop counter
        let mut j = 1;
        
        // inner loop
        while j <= 5 {
            print!("*");
            
            // increase inner loop counter
            j += 1;
        }
        
        println!("");
        
        // increase outer loop counter
        i += 1;
    }
}