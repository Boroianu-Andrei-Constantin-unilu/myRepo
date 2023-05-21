fn main() {
    // initialization of array with data type
    let numbers: [i32; 5] = [1, 2, 3, 4, 5];
    
    println!("Array of numbers = {:?}", numbers);

    // initialization of array without data type
    let numbers2 = [6, 7, 8, 9, 10];
    
    println!("Array of numbers = {:?}", numbers2);

    // initialization of array with default values
    let numbers3: [i32; 5] = [3; 5];
    
    println!("Array of numbers = {:?}", numbers3);

    // an array without data type
    let a = [5, 4, 3, 2, 1];
    
    // an array with data type and size
    let b: [i32; 5] = [1, 2, 3, 4, 5];
    
    // an array with default values
    let c = [3; 5];
    
    println!("a = {:?}", a);
    println!("b = {:?}", b);
    println!("c = {:?}", c);

    // access elements in array

    let colors = ["red", "green", "blue"];
    
    // accessing element at index 0
    println!("1st Color: {}", colors[0]);

    // accessing element at index 1
    println!("2nd Color: {}", colors[1]);

    // accessing element at index 2
    println!("3rd Color: {}", colors[2]);

    // mutable array

    let mut numbers: [i32; 5] = [1, 2, 3, 4, 5];
    
    println!("original array = {:?}", array);
    
    // change the value of the 3rd element in the array
    numbers[2] = 0;
    
    println!("changed array = {:?}", numbers);

    // loop through array

    let colors = ["red", "green", "blue"];
    
    // loop through an array to print its index and value
    for index in 0..3 {
        println!("Index: {} -- Value: {}", index, colors[index]);
    }
}