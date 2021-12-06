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