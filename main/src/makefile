# Compiler et exécuter le jeu

# Chemin vers le répertoire contenant les fichiers source Java
SRC_DIR = PROJET-N4/main/src

# Chemin vers le répertoire contenant les images
IMAGE_DIR = $(SRC_DIR)/images

# Chemin vers le répertoire contenant les fichiers PDF
PDF_DIR = $(SRC_DIR)

# Nom de la classe principale contenant la méthode main
MAIN_CLASS = model.Main

# Commande de compilation
JAVAC = javac

# Commande d'exécution
JAVA = java

# Options de compilation
JAVAC_FLAGS = -d bin -sourcepath $(SRC_DIR) -cp $(IMAGE_DIR)

# Options d'exécution
JAVA_FLAGS =

# Fichiers source Java
SOURCES = $(wildcard $(SRC_DIR)/**/*.java)

# Fichiers compilés
CLASSES = $(SOURCES:$(SRC_DIR)/%.java=bin/%.class)

.PHONY: all clean run

# Règle par défaut
all: $(CLASSES)

# Règle pour compiler les fichiers source
bin/%.class: $(SRC_DIR)/%.java
    $(JAVAC) $(JAVAC_FLAGS) $<

# Règle pour exécuter l'application
run: $(CLASSES)
    $(JAVA) $(JAVA_FLAGS) $(MAIN_CLASS)

# Règle pour nettoyer les fichiers compilés
clean:
    rm -rf bin/*
