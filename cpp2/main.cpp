#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <list>
#include <iterator>
#include <cstdlib>
#include <unordered_set>
#include <string>
#include <chrono>
#include "pow.cpp"
#include "current_time.cpp"

using namespace std;

int positions;
int numberTokens;
vector<string> mainTokens;
int guesses=0;
int guessIndex=0;
int rightWrong=0;
int rightRight=0;
CurrentTime current_time;
    uint64_t start1 = current_time.milliseconds();
    uint64_t end1 = current_time.milliseconds();

void nextMove();
string guessFix(string a);
void response(int colorsRightPositionWrong, int positionsAndColorRight);
bool checkArray(vector<int> v, int x);
vector<string> generateArray(vector<string> x, int y, int z);

void MasterMind(vector<string> x, int positions, int numberTokens){
mainTokens.clear();
for (int i=0;i<myPow(numberTokens, positions); i++)
{
            mainTokens.push_back(x[i]);
        }
        guessIndex = rand() % mainTokens.size();
        guesses++;
        cout <<   "Guess #" << guesses <<endl;
        cout << "Number of possibilities left: " << mainTokens.size() <<endl;
        cout <<"Guess: " << guessFix(x[guessIndex]) <<endl;
        cout<<"Enter number of right color(s) AND right position(s): "<<endl;
        cin>>rightRight;
                cout<<"Enter number of right color(s) BUT wrong position(s): "<<endl;
        cin>>rightWrong;
        response(rightWrong,rightRight);


    }
     void response(int colorsRightPositionWrong, int positionsAndColorRight) {
        start1 = current_time.milliseconds();
        int x = 0, y = 0;
        string temp = mainTokens[guessIndex];

        for (int i = 0; i < mainTokens.size(); i++) {
            for (int j = 0; j < temp.length(); j++) {
                if (mainTokens[i][j] == temp[j]) {
                    x++;
                }
            }
            if (x != positionsAndColorRight) {
                mainTokens.erase(mainTokens.begin()+i);
                x = 0;
                i--;
            }
            x = 0;
        }
        vector<int> forbidden;
        vector<int> doubleForbidden;
        for (int i = 0; i < mainTokens.size(); i++) {
            if (colorsRightPositionWrong == 0) {
                i = mainTokens.size() - 1;
                break;
            }
            for (int j = 0; j < temp.length(); j++) {
                if (checkArray(doubleForbidden, j)) {
                    continue;
                }

                for (int k = 0; k < temp.length(); k++) {

                    if (j == k) {
                        for (int p = 4; p < temp.length(); p++) {
                            if (mainTokens[i][p] == temp[p]) {
                                doubleForbidden.push_back(p);
                            }
                        }
                        continue;
                    }
                    if (checkArray(forbidden, k)) {
                        continue;
                    }
                    if (checkArray(doubleForbidden, k)) {
                        continue;
                    }
                    if (temp[j] == mainTokens[i][k]) {
                        y++;
                        forbidden.push_back(k);
                        break;
                    }
                }
            }
            forbidden.clear();
            doubleForbidden.clear();
            if (y != colorsRightPositionWrong) {
                mainTokens.erase(mainTokens.begin()+i);
                y = 0;
                i--;
            }
            y = 0;
        }
        nextMove();
    }
         bool checkArray(vector<int> v, int x) {
         return(std::find(v.begin(), v.end(), x) != v.end());

    }

    void newGame() {
        guesses = 0;
        cout<<" Enter number of positions: "<<endl;
        cin>>positions;
        cout<<"The number of positions: "<< positions <<endl;
        cout<<"Enter number of tokens: "<<endl;
        cin>>numberTokens;
        vector<string> tokens;
        generateArray(tokens, positions, numberTokens);
       MasterMind(tokens, positions, numberTokens);

    }

       void nextMove() {
               end1 =  current_time.milliseconds();
               cout<<"The calculation took " <<(end1-start1)<<" milliseconds."<<endl;
               if (mainTokens.size()<1){
               cout<<"Result: "<<mainTokens[0];
               return;
               }
                       guessIndex = rand() % mainTokens.size();
                       guesses++;

        cout<<"Number of possibilities left: "<<mainTokens.size()<<endl;
        cout<<"Guess: "<<guessFix(mainTokens[guessIndex])<<endl;
                cout<<"Enter number of right color(s) AND right position(s): "<<endl;
        cin>>rightRight;
                cout<<"Enter number of right color(s) BUT wrong position(s): "<<endl;
        cin>>rightWrong;
        response(rightWrong,rightRight);


    }

        string guessFix(string a) {
        //https://www.webgamesonline.com/mastermind/index.php
        string returnString = "";
        string colors[] = {"RED", "GREEN", "BLUE",
                "YELLOW", "BROWN", "ORANGE", "BLACK", "WHITE"};
        for (int i = 0; i < a.length(); i++) {
            returnString += colors[(int) a[i] - 48];
            returnString += " ";
        }
        return returnString;
    }


int main() {
        positions=8;
        numberTokens=8;

        vector<string> tokens ((int) myPow(numberTokens, positions));
        mainTokens=generateArray(tokens, positions, numberTokens);
        MasterMind(mainTokens, positions, numberTokens);
    return 0;
}


    vector<string> generateArray(vector<string> x, int y, int z) {
        int possibilities = (int) myPow(z, y);
        int i = 0, j = 0, k = 0;
        int counter = 0;
        while (counter < y) {
            k = 0;
            for (i = 0; i < (int) myPow(z, counter + 1); i++) {
                for (j = 0; j < possibilities / ((int) myPow(z, counter + 1)); j++) {
                    x[k] += (to_string(i % z));
                    k++;
                }
            }
            counter++;
        }
        return x;
    }
