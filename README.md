# PWGEN
# Project Overview

The pwgen program is a command-line utility program used to generate random and secure passwords. 
It generates passwords by returning a string of characters from the available characters.

## Low-level Requirements:

1. Project in Java. 
2. Command line arguments (Similar to PhoneNumberSorter).
3. Passes in arguments that specify character length, character types, and case type. 
4. Creates a password of randomized characters that agrees with the specs passed in.

## Usage
usage: pwgen

Creates a random password using any of the possible specifications listed below that are given. 

-h, --help&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Prints a list of available commands. <br>
-s, --symbol&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Indicates the password must contain symbols. <br>
-n, --length&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Indicates the length to be output. There is no default length. <br>
-a, --alpha&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Indicates the password must contain alphabetical characters. <br>
-d, --number&nbsp;&nbsp;&nbsp;&nbsp;Indicates the password must contain numbers. <br>
