# Makefile pour compiler et exécuter le jeu Octopunks

# Compiler les fichiers .java
compile:
	javac -d bin main/src/model/*.java

# Exécuter le jeu en mode console
run_console: compile
	java -cp bin model.Main

# Exécuter le jeu en mode GUI
run_gui: compile
	java -cp bin model.OctopunksGUI

# Exécuter le jeu (par défaut, en mode console)
run: run_console

# Nettoyer les fichiers .class générés
clean:
	rm -rf bin/model/*.class