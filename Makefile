# gnu make Makefile

# define variable
PROJECT=PWGen
# define some "targets"
# example:
# target: [dependencies]
#   command to build target

#intermediate target for the Java bytecode
$(PROJECT).class: $(PROJECT).java
    javac PWGen.java

# target for compiling the java code
compile: $(PROJECT).class

# target for running the Java code
run: compile
    java Program