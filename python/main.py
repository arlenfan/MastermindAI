import itertools
import random
import numpy as np
import datetime
import time

# https://webgamesonline.com/mastermind/index.php
def generateArray(uniqueColors, length):
    x = ["RED", "GREEN", "BLUE", "YELLOW", "BROWN", "ORANGE", "BLACK", "WHITE"]
    array = [i for i in itertools.product(x[:uniqueColors], repeat=length)]
    array = np.array(array)
    return array

def move(allPossibilities):
    print("Possibilities left to consider: ", len(allPossibilities))
    if (len(allPossibilities) == 1):
        print("Solution found: ", allPossibilities[0])
        return
    randomIndex = random.randrange(start=0, stop=len(allPossibilities) - 1, step=1)
    guess = allPossibilities[randomIndex]
    print("Guess index ", randomIndex, ":", guess)
    rightRight = int(input("Right color, right position: "))
    rightWrong = int(input("Right color, wrong position: "))
    prune(arr=allPossibilities, rightRight=rightRight, rightWrong=rightWrong, guess=guess)


def prune(arr, rightRight, rightWrong, guess):
    start = time.time()
    toRemove = []

    for index, element in enumerate(arr):
        candidate = element
        rightRightVector = [candidate[i] == guess[i] for i in range(len(candidate))]
        if int(rightRightVector.count(True)) != rightRight:
            toRemove.append(index)
            continue
        invertedRightVector = [not p for p in rightRightVector]
        maskedCandidate = candidate[invertedRightVector]
        maskedGuess = guess[invertedRightVector]

        rightWrongCount = 0
        for i in list(set(maskedCandidate)):
            rightWrongCount += np.count_nonzero(maskedGuess==i)
        if rightWrongCount != rightWrong:
            toRemove.append(index)
    arr = arr[[x for x in range(len(arr)) if x not in toRemove]]
    end = time.time()
    print("This pruning step took ", (end-start), " sec")
    print()
    move(arr)


if __name__ == '__main__':
    uniqueColors = int(input("How many unique colors?"))
    length = int(input("What is the length of the sequence?"))
    # uniqueColors = 8
    # length = 4
    allPossibilities = generateArray(uniqueColors=uniqueColors, length=length)
    move(allPossibilities=allPossibilities)
