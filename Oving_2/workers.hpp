//
// Created by Eirik Elvestad on 06/02/2023.
//

#ifndef OVING_2_WORKERS_HPP
#define OVING_2_WORKERS_HPP


class workers {
    workers worker_threads(4);
    workers event_loop(1);

    worker_threads.start(); //
    event_loop.start(); //

    worker_threads.post([] {
        // Task A
    });
    worker_threads.post([] {
        // Task B
        // Might run in parallel
    });
    event_loop.post([] {
        // Task C
        // Might run in parallel with Task A and B
    });
    event_loop.post([] {
        // Task D
        // Will run after task C // Might run in parallel
    });
        worker_threads.join(); // Calls join()
        event_loop.join(); // Calls join()
};


#endif //OVING_2_WORKERS_HPP
