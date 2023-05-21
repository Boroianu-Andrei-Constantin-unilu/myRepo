fn main() {

    // break loop
    let mut number = 0;
    
    // loop starts here
    loop {
        number += 1;

        // condition to exit the loop
        if number > 5 {
            break;
        }

        println!("{}", number);        
    }

    let mut i = 1;
    
    // start of outer loop
    while i <= 5 {
        let mut j = 1;
     
        // start of inner loop
        while j <= 5 {
            print!("*");
            
            // condition to exit the inner loop
            if j == 3 {
                // terminate the inner loop
                break;
            }
            
            j += 1;
        }
        
        println!("");
        
        i += 1;
    }

    // continue loop

    let mut number = 0;

    while number < 5 {
        number += 1;

        // condition to skip the iteration
        if number == 3 {
            continue;
        }

        println!("{}", number);
    }

    let mut i = 1;
    
    // start of outer loop
    while i <= 5 {
        let mut j = 1;
            
        // start of inner loop
        while j <= 5 {
            j += 1;
            
            // condition to skip iteration of the inner loop
            if j == 3 {
                // move to the next iteration of the inner loop
                continue;
            }
            
            print!("*");
        }
        
        println!("");
        
        i += 1;
    }

    // both in the same loop
    let mut number = 0;

    loop {
        number += 1;

        // condition to skip the iteration
        if number == 3 {
            continue;
        }
        
        // condition to exit the loop
        if number > 5 {
            break;
        }
        
        println!("{}", number);
    }
}