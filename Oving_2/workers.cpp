//
// Created by Eirik Elvestad on 06/02/2023.
//

#include "workers.hpp"
#include <thread>
#include <functional>
#include <chrono>



/*
 * runs a task
 */
void Workers::post(const std::function<void()>& task) {
    //std::cout<<"post\n";
    this->post_timeout(task, 0);
}


/*
 * runs a task after (int pause)ms
 */
void Workers::post_timeout(const std::function<void()>& task, int pause) {
    std::unique_lock<std::mutex> lock(task_mutex);
    //std::cout<<"post_timeout\n";
    task_queue.emplace_back([task, pause] {
        std::this_thread::sleep_for(std::chrono::milliseconds(pause));
        task();
    });
    cv.notify_one();
}

/*
 * Makes a vector with x threads and thereafter a function "task". Waits while queue is empty and not running. Iterates
 * through the task queue and sets the first in list to be the task running
 * Lager en vektor med x antall threads, og lager deretter en funksjon
 */

void Workers::start(){
    //std::vector<std::thread> worker_threads;
    for(int i = 0; i < number_threads; i++) {
        worker_threads.emplace_back([&] {
            while(true) {
                std::function<void()> task;
                //std::cout<<"START: " << i << "\n";
                {
                    std::unique_lock<std::mutex> lock(task_mutex);
                    while(task_queue.empty()){
                        //std::cout<<"S_WHILE\n";
                        if(!run) return;
                        //std::cout<<"S_LOCKED\n";
                        std::cout <<  "num t" << this->number_threads;
                        cv.wait(lock);
                    }
                    task = *task_queue.begin();  //stores the first task
                    //std::cout<<"Task begin\n";
                    task_queue.pop_front();      //and removes it from the task_queue
                }
                cv.notify_one();
                if(task){
                    task();
                    //std::cout<<"TASK\n";
                }
            }
        });
    }
}

/*
 * Cancels the worker_threads if the task_queue is empty, if it is not empty, the threads
 * running is blocked until notified
 */
void Workers::stop(){
    std::unique_lock<std::mutex> lock(task_mutex);
    while(!this->task_queue.empty()) {
        std::cout << task_queue.size();
        std::cout<<"STOP\n";
        //HER ER EN FEIL
        cv.wait(lock);
        //std::cout<<"WHILE ferdig\n";
    }
    run.exchange(false);
    std::cout << run << " ok";
    cv.notify_all();
    //std::cout<<"NOTIFY_ALL\n";
}

void Workers::join(){
    this->stop();
    for(auto &thread : worker_threads){
        thread.join();
        //std::cout<<"JOINED\n";
    }
    //std::cout<<"JOIN_SLUTT\n";
}