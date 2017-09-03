# MakeFile for median filter Assignment
# BSSDIN001

SRCDIR = src
BINDIR = bin
DOCDIR = doc

JC = javac
JFLAGS = -g -d $(BINDIR) -cp $(BINDIR)

vpath %.java $(SRCDIR)
vpath %.class $(BINDIR)

.SUFFIXES: .java .class

.java.class:	
	$(JC) $(JFLAGS) $<
	
CLASSES = MedFilt.class \
	  Medfiltpar.class \
	  Medfiltseq.class \

classes: $(CLASSES:.java=.class)

doc: $(BINDIR)
	javadoc -d $(DOCDIR) ./src/MedFilt.java
	javadoc -d $(DOCDIR) ./src/Medfiltpar.java
	javadoc -d $(DOCDIR) ./src/Medfiltseq.java
	
clean:
	$(RM) $(BINDIR)/*.class
	$(RM) $(SRC)/*.java~
	$(RM) $(DOCDIR)/*
