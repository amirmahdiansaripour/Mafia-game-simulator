JC =javac
.SUFFIXES:.java .class
.java.class:
	$(JC) $*.java
CLASSES = \
	Globals.java\
	Errors.java\
	Player.java\
	Detective.java\
	Bulletproof.java\
	Doctor.java\
	Silencer.java\
	Godfather.java\
	Joker.java\
	Mafia.java\
	Villager.java\
	God.java\
	MafiaGame.java
	
default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
