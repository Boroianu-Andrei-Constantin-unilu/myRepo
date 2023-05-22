// import HashSet from Rust standard collections library
use std::collections::HashSet;

fn main() {
    // create a new HashSet
    let color: HashSet<String> = HashSet::new();
    
    println!("HashSet = {:?}", color);

    // add values to HashSet

    let mut colors: HashSet<&str> = HashSet::new();
    
    // insert values in a HashSet
    colors.insert("Red");
    colors.insert("Yellow");
    colors.insert("Green");

    println!("colors = {:?}", colors);

    // check for a value in a HashSet
    if colors.contains("Red") {
        println!("We have the color \"Red\" in the HashSet.")
    }

    println!("colors before remove operation = {:?}", colors);

    // remove value from a HashSet
    colors.remove("Yellow");
    
    println!("colors after remove operation = {:?}", colors);

    // iterate over a hashset
    for color in colors {
        // print each value in the hashset
        println!("{}", color);
    }

    // Create HashSet with default set of values using from() method
    let numbers = HashSet::from([2, 7, 8, 10]);
    
    println!("numbers = {:?}", numbers);
}