fn main() {
    let numbers = [2, 1, 17, 99, 34, 56];
    
    // iterator (using iter())
    let numbers_iterator = numbers.iter();
    
    for number in numbers_iterator {
        println!("{}", number);
    }

    // iterator (using next())
    let mut colors = vec!["Red", "Yellow", "Green"];
    
    // iterator
    let mut colors_iterator = colors.iter();
    println!("colors iterator = {:?}", colors_iterator);
    
    // fetch values from iterator one by one using next() method
    println!("{:?}", colors_iterator.next());
    println!("{:?}", colors_iterator.next());
    println!("{:?}", colors_iterator.next());
    println!("{:?}", colors_iterator.next());

    // iteration methods

    // using iter() to iterate through a collection
    for color in colors.iter() {
        // reference to the items in the iterator
        println!("{}", color);
    }
    
    // the collection is untouched and still available here
    println!("colors = {:?}", colors);

    // using into_iter() to iterate through a collection
    // for color in colors.into_iter() {
        // the items in the collection move into this scope
        // println!("{}", color);
    // }
    // end of scope of for loop
    
    // error
    // the collection is not available here as the for loop scope ends above
    // println!("colors = {:?}", colors);

    // using iter_mut() to iterate through a collection
    for color in colors.iter_mut() {
        // modify the item in the collection
        *color = "Black";
        println!("{}", color);
    }
    
    // the modified collection is available here
    println!("colors = {:?}", colors);

    // iterator adapters

    let numbers: Vec<i32> = vec![1, 2, 3];
   
    // using the map iterator adapter
    let even_numbers: Vec<i32> = numbers.iter().map(|i| i * 2).collect();
    
    println!("numbers = {:?}", numbers);
    println!("even_numbers = {:?}", even_numbers);

    // looping through a range
    for i in 1..6 {
        println!("{}", i);
    }
}