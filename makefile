J = java
JC = javac
JFLAGS = -classpath ./src/
MAIN = ./src/vintage/Main.java
CLASS = ./src/vintage/Main.class

all: compile run clean

compile:
	$(JC) $(MAIN)

run:
	$(J) $(JFLAGS) vintage.Main

clean:
	rm $(CLASS)