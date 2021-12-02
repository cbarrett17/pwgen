# gnu make Makefile
PROJECT=PWGen

# define some variables
JCC = javac
P = PWGen
PT = PWGenTest
SHELL = /bin/sh

# define some "targets"

default: class-PWGen

class-PWGen: $(P).java
	# command to build target
	echo "Compiling..."
	$(JCC) $(P).java

compile-test: $(PT).class
	echo "Testing..."

PWGenTest.class: $(PT).java
	$(JCC) $(PT).java
