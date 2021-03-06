# PWGEN
# Project Overview

The pwgen program is a command-line utility program used to generate random and secure passwords. 
It generates passwords by returning a string of characters from the available characters.

## High-level Features:

1. Project in Java. 
2. Command line arguments (Similar to PhoneNumberSorter).
3. Passes in arguments that specify character length, character types, and case type. 
4. Creates a password of randomized characters that agrees with the specs passed in.
5. Translates resources into the following locales: English, French, Italian, German, and Spanish.

## Usage
usage: pwgen --help, --symbol, --length, --alpha, --number <br>
Generates a random password using any of the possible specifications listed below that are given. 

Available flags:

    -h, --help    Prints a list of available commands.
    -n, --length  Indicates the length to be output. There is no default length.
    -s, --symbol  Indicates the password must contain symbols.
    -a, --alpha   Indicates the password must contain alphabetical characters.
    -d, --number  Indicates the password must contain numbers.

Status Exit Codes:<br>
(0) - Successful<br>
(1) - Invalid Input<br>
(2) - Invalid Password Length<br>
(3) - Invalid Number Format (given arg -n)<br>
