# gnu make Makefile

# define some "targets"

target: [dependencies]
#	command to build target

# default "all" target
all: compile

# intermediate target for the Java bytecode
PWGen.class: PWGen.java
	echo "Compiling..."
	javac PWGen.java

# target for compiling the Java code
compile: PWGen.class

# target for running the Java code
run: compile
	echo "Running..."
	java PWGen

# target for testing the Java code
PWGen.class: PWGen.java PWGenTest.java
	javac PWGenTest.java

compile-test: PWGenTest.class

run-test: compile-test
	java org.junit.runner.JUnitCore PWGenTest
