# gnu make Makefile

# define some variables
PROJECT=PWGen
JCC = javac
PT = PWGenTest
SHELL = bin/sh
CLASSPATH = "system-rules-1.18.0.jar;commons-cli-1.5.0.jar;lib/*;."

# define some "targets"

default: compile

compile: PWGen.java
	# command to build target
	echo "Compiling..."
	$(JCC) -cp $(CLASSPATH) $(PROJECT).java

PWGenTest.class: PWGenTest.java
	$(JCC) -cp $(CLASSPATH) $(PT).java

test: PWGenTest.class
	# command to run Test class
	echo "Testing..."
	java -cp $(CLASSPATH) org.junit.runner.JUnitCore PWGenTest

run-en_US: compile
	java -cp $(CLASSPATH) -Duser.language=en -Duser.country=US $(PROJECT)

run-es_MX: compile
	java -cp $(CLASSPATH) -Duser.language=es -Duser.country=MX $(PROJECT)

run-fr_FR: compile
	java -cp $(CLASSPATH) -Duser.language=fr -Duser.country=FR $(PROJECT)

run-it_IT: compile
	java -cp $(CLASSPATH) -Duser.language=it -Duser.country=IT $(PROJECT)

run-de_DE: compile
	java -cp $(CLASSPATH) -Duser.language=de -Duser.country=DE $(PROJECT)