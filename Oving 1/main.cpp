//
// Created by Eirik Elvestad on 12/01/2023.
//
#include <iostream>
#include <ctime>

#include "prime.hpp"


int main() {
    int lowestInteger;
    int highInteger;
    int numThreads;
    std::cout << "-----Welcome to Find_The_Optimus_Prime------ \n\t Write the lowest integer in the interval\n\t";
    std::cin >> lowestInteger;
    std::cout << "\t Write the highest integer in the interval\n\t";
    std::cin >> highInteger;
    std::cout << "\t Write num of threads\n\t";
    std::cin >> numThreads;

    int startTime = clock();
    FindPrimesWithThreads(lowestInteger, highInteger, numThreads);
    int endTime = clock();
    printNum();

    std::cout << "\n" << (endTime - startTime)/double(CLOCKS_PER_SEC);

    return 0;
}
