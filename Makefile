# gnu make Makefile

# define some variables
PROJECT=PWGen
JCC = javac
PT = PWGenTest
SHELL = bin/sh
CLASSPATH = "system-rules-1.18.0.jar;commons-cli-1.5.0.jar;lib/*;."
RESOURCES = "PWGen_de_DE.properties;PWGen_en_US.properties;PWGen_es_MX.properties;PWGen_fr_FR.properties;PWGen_it_IT.properties;."
DEFAULT_LENGTH_RUN = -n 10

# define some "targets"
default: compile

compile: PWGen.java
	# command to build target
	echo Compiling...
	$(JCC) -cp $(CLASSPATH) $(PROJECT).java

PWGenTest.class: PWGenTest.java
	$(JCC) -cp $(CLASSPATH) $(PT).java

test: PWGenTest.class
	# command to run Test class
	echo "Testing..."
	java -cp $(CLASSPATH) org.junit.runner.JUnitCore PWGenTest

run-en_US: compile
	java -cp $(CLASSPATH) -Duser.language=en -Duser.country=US $(PROJECT) -h
	java -cp $(CLASSPATH) -Duser.language=en -Duser.country=US $(PROJECT) $(DEFAULT_LENGTH_RUN)

run-es_MX: compile
	java -cp $(CLASSPATH) -Duser.language=es -Duser.country=MX $(PROJECT) -h
	java -cp $(CLASSPATH) -Duser.language=es -Duser.country=MX $(PROJECT) $(DEFAULT_LENGTH_RUN)

run-fr_FR: compile
	java -cp $(CLASSPATH) -Duser.language=fr -Duser.country=FR $(PROJECT) -h
	java -cp $(CLASSPATH) -Duser.language=fr -Duser.country=FR $(PROJECT) $(DEFAULT_LENGTH_RUN)

run-it_IT: compile
	java -cp $(CLASSPATH) -Duser.language=it -Duser.country=IT $(PROJECT) -h
	java -cp $(CLASSPATH) -Duser.language=it -Duser.country=IT $(PROJECT) $(DEFAULT_LENGTH_RUN)

run-de_DE: compile
	java -cp $(CLASSPATH) -Duser.language=de -Duser.country=DE $(PROJECT) -h
	java -cp $(CLASSPATH) -Duser.language=de -Duser.country=DE $(PROJECT) $(DEFAULT_LENGTH_RUN)

# run a help command in two different languages
run-demo1: compile
	java -cp $(CLASSPATH) -Duser.language=fr -Duser.country=FR $(PROJECT) -j
	java -cp $(CLASSPATH) -Duser.language=it -Duser.country=IT $(PROJECT) -h

# run a valid english run to show it works
run-demo2: compile run-en_US
	java -cp $(CLASSPATH) -Duser.language=en -Duser.country=US $(PROJECT) $(DEFAULT_LENGTH_RUN) -a

# run an invalid command to show the error handling
run-demo3-pt1: compile
	java -cp $(CLASSPATH) $(PROJECT) -j

run-demo3-pt2: compile
	java -cp $(CLASSPATH) $(PROJECT) -n -1

run-demo3-pt3: compile
	java -cp $(CLASSPATH) $(PROJECT) -n HI