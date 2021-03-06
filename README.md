# Date-Checker

#### Author@Luka Didham

## Description
Java prgram which takes in text entered by the user and returns if said text is a valid date. Also accounts for leap years and century leap years e.g 1900 not a leap year, 
however 1904 is. The rules are based off the website https://www.howmanysyllables.com/syllable_rules/howtocountsyllables "Written Method" rules. The leap rules are sourced from
https://www.rmg.co.uk/stories/topics/which-years-are-leap-years-can-you-have-leap-seconds#:~:text=To%20be%20a%20leap%20year,2028%20are%20all%20leap%20years.

## Java Libraries Used
`java.util.Scanner`
`java.text.SimpleDateFormat`
`java.util.StringTokenizer`

## How to run program
To complie the program just complie the Etude2.java file  
The command to complie the java class is as follows  

`javac Etude1.java`

`java Etude1`

## Test Results
    6/6/6
    06 Jun 2006
    3/mar/99
    03 Mar 1999
    29/FeB/99
    29 Feb 99 - INVALID: Day is greater than days of this month in this year. Is a leap year: false
    29/FeB/20
    29 Feb 2020
    29/FeB/1900
    29 Feb 1900 - INVALID: Day is greater than days of this month in this year. Is a leap year: false
    29/FeB/1904
    29 Feb 1904
    299/FeB/1904
    299 FeB 0 - INVALID: Day impossible for any month
    29/13/1904
    29 13 1904 - INVALID: Month Input Error. Needs to be of format mm or m or 0m or the first three letters of the month name
    29 13 1904
    29 13 1904 - INVALID: Month Input Error. Needs to be of format mm or m or 0m or the first three letters of the month name
    29 12 1904
    29 Dec 1904
    29-12-1904
    29 Dec 1904
