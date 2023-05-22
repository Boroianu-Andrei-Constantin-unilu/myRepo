use std::num::ParseIntError;

// function to find a user by their username which returns an Option type
fn get_user(username: &str) -> Option<&str> {
    if username.is_empty() {
        return None;
    }

    return Some(username);
}

// Function to parse an integer
fn parse_int() -> Result<i32, ParseIntError> {
    // Example of ? where value is unwrapped
    let x: i32 = "12".parse()?; // x = 12
    
    // Example of ? where error is returned
    let y: i32 = "12a".parse()?; // returns an Err() immediately
    
    Ok(x + y) // Doesn't reach this line
}

fn main() {
    // returns an Option
    let user_option = get_user("Hari");

    // use of match expression to get the result out of Option
    let result = match user_option {
        Some(user) => user,
        None => "not found!",
    };

    // print the result
    println!("user = {:?}", result);

    // use of unwrap method to get the result of Option enum from get_user function
    let result = get_user("Hari").unwrap();

    // print the result
    println!("user = {:?}", result);

    // use of expect method to get the result of Option enum from get_user function
    let result = get_user("").expect("fetch user");

    // print the result
    println!("user = {:?}", result);

    // question mark operator
    let res = parse_int();

    println!("{:?}", res);
}