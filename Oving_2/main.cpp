#include <iostream>
#include "workers.hpp"

int main() {
    Workers worker_threads(4);
    Workers event_loop(1);

    worker_threads.start(); //
    event_loop.start(); //

    worker_threads.post([] {
        std::cout<<"--Task A--\n";
        // Task A
    });
    worker_threads.post([] {
        std::cout<<"--Task B--\n";
        // Task B
        // Might run in parallel
    });
    event_loop.post([] {
        std::cout<<"--Task C--\n";
        // Task C
        // Might run in parallel with Task A and B
    });
    event_loop.post([] {
        std::cout<<"--Task D--\n";
        // Task D
        // Will run after task C // Might run in parallel
    });
    worker_threads.join(); // Calls join()
    event_loop.join(); // Calls join()


    return 0;
}
