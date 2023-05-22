use std::thread;
use std::time::Duration;
use std::sync::mpsc;

fn main() {
    // create a thread
    thread::spawn(|| {
        // everything in here runs in a separate thread
        for i in 0..10 {
            println!("{} from the spawned thread!", i);
            thread::sleep(Duration::from_millis(2));
        }
    });

    // main thread
    for i in 0..5 {
        println!("{} from the main thread!", i);
        thread::sleep(Duration::from_millis(1));
    }

    // join handles

    // create a thread and save the handle to a variable
    let handle = thread::spawn(|| {
        // everything in here runs in a separate thread
        for i in 0..10 {
            println!("{} from the spawned thread!", i);
            thread::sleep(Duration::from_millis(2));
        }
    });

    // main thread
    for i in 0..5 {
        println!("{} from the main thread!", i);
        thread::sleep(Duration::from_millis(1));
    }
    
    // wait for the separate thread to complete
    handle.join().unwrap();

    // create a thread and save the handle to a variable
    let handle = thread::spawn(|| {
        // everything in here runs in a separate thread
        for i in 0..10 {
            println!("{} from the spawned thread!", i);
            thread::sleep(Duration::from_millis(2));
        }
    });
    
    // wait for the separate thread to complete
    handle.join().unwrap();

    // main thread
    for i in 0..5 {
        println!("{} from the main thread!", i);
        thread::sleep(Duration::from_millis(1));
    }

    // move closures

    // main thread starts here
    let message = String::from("Hello, World!");

    // move the message value to a separate thread
    let handle = thread::spawn(move || {
        println!("{}", message);
    });

    // wait for the thread to finish
    handle.join().unwrap();

    // send messages between threads

    // main thread starts here
    // create a new channel
    let (sender, receiver) = mpsc::channel();

    // spawn a new thread
    let handle = thread::spawn(move || {
        // receive message from channel
        let message = receiver.recv().unwrap();

        println!("Received message: {}", message);
    });

    let message = String::from("Hello, World!");
    // send message to channel
    sender.send(message).unwrap();

    // wait for spawned thread to finish
    handle.join().unwrap();
}