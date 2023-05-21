fn main() {
    // scope of outer_var variable is inside the main function code block
    let outer_var = 100;
    
    // start of the inner code block
    {
        // scope of inner_var variable is only inside this new code block
        let inner_var = 200;
        println!("inner_var = {}", inner_var);
        println!("outer_var inside inner block = {}", outer_var);
    }
    // end of the inner code block
    
    println!("outer_var = {}", outer_var);

    // variable shadowing

    let random = 100;

    // start of the inner block
    {
        println!("random variable before shadowing in inner block = {}", random);

        // this declaration shadows the outer random variable
        let random = "abc";

        println!("random after shadowing in inner block = {}", random);
    }
    // end of the inner block

    println!("random variable in outer block = {}", random);

    // variable freezing

    let mut age = 100;

    {
        // shadowing by immutable age variable
        let age = age;

        println!("age variable inner block = {}", age);
        // age goes out of scope
    }

    // age variable is not frozen in this scope
    age = 3;

    println!("age variable outer block = {}", age);
}