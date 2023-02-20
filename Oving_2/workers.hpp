//
// Created by Eirik Elvestad on 06/02/2023.
//

#ifndef OVING_2_WORKERS_HPP
#define OVING_2_WORKERS_HPP

#include <functional>
#include <iostream>
#include <list>
#include <vector>
#include <thread>
#include <atomic>
#include <condition_variable>

class Workers{
    std::atomic_bool run;
    //bool run;
    int number_threads;
    std::list<std::function<void()>> task_queue;
    //std::vector<std::thread> thread_pool;
    std::vector<std::thread> worker_threads;
    std::condition_variable cv;
    std::mutex task_mutex;

public:
    explicit Workers(int number_threads){
        this->number_threads = number_threads;
    }
    void post(const std::function<void()>& task);
    void post_timeout(const std::function<void()>& task, int pause);
    void start();
    void stop();
    void join();
};

#endif //OVING_2_WORKERS_HPP
