# baby-names
Analyzing data gathered on baby names from the United States.

totalBirths()
To print the number of girls names , the number of boys names and the total names in the file.

getRank() returns the rank of the name in the file for the given gender,  where rank 1 is the name with the largest number of births. If the name is not in the file, then -1 is returned.

getName() returns the name of the person in the file at this rank, for the given gender, where rank 1 is the name with the largest number of births. If the rank does not exist in the file, then “NO NAME”  is returned.

whatIsNameInYear() determines what name would have been named if they were born in a different year, based on the same popularity. That is, it should determine the rank of name in the year they were born, and then print the name born in newYear that is at the same rank and same gender.

yearOfHighestRank() selects a range of files to process and returns an integer, the year with the highest rank for the name and gender. If the name and gender are not in any of the selected files, it should return -1.

getAverageRank() selects a range of files to process and returns a double representing the average rank of the name and gender over the selected files. It should return -1.0 if the name is not ranked in any of the selected files.

getTotalBirthsRankedHigher() returns an integer, the total number of births of those names with the same gender and same year who are ranked higher than name.
