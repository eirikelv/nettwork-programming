//
// Created by Eirik Elvestad on 12/01/2023.
//
#include <vector>
#include <thread>
#include <string>
#include <sstream>
#include <iostream>
#include <cmath>

#include "prime.hpp"

using namespace std;

std::mutex vectLock;
std::vector<int> intervalList;

/*
bool isPrime(int num){
    if(num <= 1 || num % 2 == 0) return false;

    for (int i = 3; i < std::sqrt(num); ++i){
        if(num % i == 0) return false;
    }
    return true;
}
 */

bool isPrime(int num){
    if(num <= 1 || num % 2 == 0) return false;

    for (int i = 2; i<num ; ++i){
        if(num % i == 0) return false;
    }

    return true;
}

/*
std::vector<int> primeList(int lowNum, int highNum){
    vector<int> intervalList;
    for(int i = lowNum; i<=highNum; i++){
        if(isPrime(i)){
            vectLock.lock();
            intervalList.push_back(i);
            vectLock.unlock();
        }
    }
    return intervalList;
}
*/

void primeList(int lowNum, int highNum){
    for(int i = lowNum; i<=highNum; i++){
        if(isPrime(i)){
            vectLock.lock();
            intervalList.push_back(i);
            vectLock.unlock();
        }
    }
}
void FindPrimesWithThreads(int start, int end, int numThreads){
    std::vector<std::thread> threadVect;
    int threadSpread = end / numThreads;
    int newEnd = start + threadSpread - 1;
    for(int i = 0; i < numThreads; i++){
        threadVect.emplace_back(primeList,start,newEnd);
        start += threadSpread;
        newEnd += threadSpread;
    }
    for(auto& t : threadVect){
        t.join();
    }

}

void printNum(){
    int size = intervalList.size();
    std::sort(intervalList.begin(), intervalList.end());
    for(int i = 0; i < size; i++){
        std::cout << to_string(intervalList[i]) + " ";
    }
}
