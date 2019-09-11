

CONTENTS OF THE FOLDER
	MasterMind.java
		main content of the MasterMind
	README.txt
		README file of MasterMind (this file)

This is the codebreaker for the classic game Mastermind. There are colors and positions. Each turn, the game gives the correct colors in the correct position and correct color in the wrong position. The codebreaker generates all the possible arrays and removes the elements based on the user input. For example, if the correct answer is RBG, then RRR would return 1 correct in the correct position, and then 0 correct in the wrong position. Each turn narrows all the elements in the array.

Notes: The program has default colors 1-8 :
	String colors [] =  {"RED","GREEN","BLUE",
				"YELLOW", "PURPLE", "BLACK", "ORANGE", "GRAY"};
The program grows exponentially and only supports 8 colors in 8 positions. The guessID corresponds to 0-Red, 1-Green ... 7-Gray, which makes this easier to play and debug.

In output, I ran the code for a 4^4, 6^4, and then 7^6. The program takes roughly 1 sec for 7^6 and 50 seconds to process the first move in 7^7, respectively, as a warning!!!

In my output, I designated these as the correct codes:
Correct code for 4^4: RED GREEN BLUE YELLOW   --- guessID: 0123
Correct code for 6^4: BLACK PURPLE YELLOW BLUE   --- guessID: 5432
Correct code for 7^6: GREEN BLUE YELLOW ORANGE ORANGE BLACK   --- guessID: 123665

Also, if you mess up and input an impossible configuration, the program will output errors. FYI.