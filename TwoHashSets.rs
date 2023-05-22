use std::collections::HashSet;

fn main() {
    let hashset1 = HashSet::from([2, 7, 8]);
    let hashset2 = HashSet::from([1, 2, 7]);
    let hashset3 = HashSet::from([1, 2, 3, 4]);
    let hashset4 = HashSet::from([4, 3, 2]);
    let hashset5 = HashSet::from([1, 2, 7, 9]);
    
    // Union of hashsets
    let result: HashSet<_> = hashset1.union(&hashset2).collect();
    
    println!("hashset1 = {:?}", hashset1);
    println!("hashset2 = {:?}", hashset2);
    println!("union = {:?}", result);

    // Intersection of hashsets
    let result2: HashSet<_> = hashset1.intersection(&hashset2).collect();
    
    println!("hashset1 = {:?}", hashset1);
    println!("hashset2 = {:?}", hashset2);
    println!("intersection = {:?}", result2);

    // Difference between hashsets
    let result3: HashSet<_> = hashset3.difference(&hashset4).collect();
    
    println!("hashset3 = {:?}", hashset3);
    println!("hashset4 = {:?}", hashset4);
    println!("difference = {:?}", result3);

    // Symmetric difference of hashsets
    let result4: HashSet<_> = hashset1.symmetric_difference(&hashset5).collect();
    
    println!("hashset1 = {:?}", hashset1);
    println!("hashset5 = {:?}", hashset5);
    println!("symmetric difference = {:?}", result4);
}