#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <list>
#include <iterator>
#include <cstdlib>
#include <unordered_set>
#include "current_time.h"


using namespace std;
CurrentTime current_time = new CurrentTime();
template<typename InputIt, typename T>

bool nextPermutationWithRepetition(InputIt begin, InputIt end, T from_value, T to_value) {
    auto it = std::find_if_not(std::make_reverse_iterator(end),
                               std::make_reverse_iterator(begin),
                               [&to_value](auto current) { return to_value == current; });

    if (it == std::make_reverse_iterator(begin))
        return false;

    auto bound_element_iterator = std::prev(it.base());

    (*bound_element_iterator)++;
    std::fill(std::next(bound_element_iterator), end, from_value);

    return true;
}

void prune(int rightRight, int wrongWrong, vector<int> guess);

vector<vector<int>> all = {};

void generateAll() {
    vector<int> vec(4, 0); //length
    do {
        all.push_back(vec);
    } while (nextPermutationWithRepetition(vec.begin(), vec.end(), 0, 7)); //unique numbers, 7 for oct

}

void printVector() {
    for (auto i = all.begin(); i != all.end(); ++i) {
        for (auto j = i->begin(); j != i->end(); ++j) {
            cout << *j << ' ';
        }
        cout << endl;
    }
}


void move() {
    int allSize = all.size();
    cout << "Possibilities left to consider: " << all.size() << endl;
    if (allSize == 1) {
        cout << "Solution found: ";
        vector<int> solution = all[0];
        std::copy(solution.begin(), solution.end(), std::ostream_iterator<int>(std::cout, " "));
        return;
    }
    int randomIndex = rand() % allSize;
    vector<int> guess = all[randomIndex];
    cout << "Guess index: " << randomIndex << endl;
    std::copy(guess.begin(), guess.end(), std::ostream_iterator<int>(std::cout, " "));
    int rightRight, rightWrong;
    cout << "Right color, right position: " << "Right color, wrong position: " << endl;
    cin >> rightRight >> rightWrong;
    prune(rightRight, rightWrong, guess);

}

void prune(int rightRight, int rightWrong, vector<int> guess) {
    uint64_t start1 = current_time.milliseconds();
    unordered_set<int> toRemove;
    for (int i = 0; i < all.size(); ++i) {
        vector<int> candidate = all[i];
        vector<bool> rightRightVector = {};
        int rightRightCount = 0;
        for (int j = 0; j != candidate.size(); ++j) {
            if (candidate[j] == guess[j]) {
                rightRightVector.push_back(true);
                rightRightCount++;
            } else {
                rightRightVector.push_back(false);
            }
        }
        if (rightRightCount != rightRight) {
            toRemove.insert(i);
            continue;
        }
        // mask candidate and guess
        vector<int> maskedCandidate;
        vector<int> maskedGuess;
        unordered_set<int> maskedGuessSet;
        for (int j = 0; j < rightRightVector.size(); ++j) {
            if (!rightRightVector[j]) {
                maskedCandidate.push_back(candidate[j]);
                maskedGuess.push_back(guess[j]);
                maskedGuessSet.insert(guess[j]);
            }
        }
        int rightWrongCount = 0;
        for (int j = 0; j < maskedCandidate.size(); ++j) {
            if (maskedGuessSet.find(candidate[j]) != maskedGuessSet.end()) {
                rightWrongCount++;
            }
        }
        if (rightWrongCount != rightWrong) {
            toRemove.insert(i);
        }
    }


    for (int i = all.size() - 1; i >= 0; i--) {
        if (toRemove.find(i) != toRemove.end()) {
            all.erase(all.begin() + i);
        }

    }
    uint64_t end1 = current_time.milliseconds();
    cout << "This prune took " << (end1-start1) <<" milliseconds";
    move();

}

int main() {
    generateAll();
    move();
    return 0;
}