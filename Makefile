# gnu make Makefile

# define some variables
PROJECT=PWGen
JCC = javac
PT = PWGenTest
SHELL = /bin/sh

# define some "targets"

default: compile

run: compile
	# command to build target
	echo "Compiling..."
	$(JCC) $(PROJECT).java

run-en_US: compile
	java -Duser.language=en -Duser.country=US $(PROJECT)

run-es_MX: compile
	java -Duser.language=es -Duser.country=MX $(PROJECT)

run-fr_FR: compile
	java -Duser.language=fr -Duser.country=FR $(PROJECT)

run-it_IT: compile
	java -Duser.language=it -Duser.country=IT $(PROJECT)
