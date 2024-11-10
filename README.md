# Sudoku_Solver

Programme conçu et implémenté par Adam Mir-sadjadi et Florent Bélot

Documentation at https://admr-s.github.io/Sudoku_Solver/

-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Commandes Terminal :

    -Compiler (depuis dossier Sudoku_Solver) :
        javac -d bin/ src/*.java src/*/*.java src/*/*/*.java

    -Exécuter (Idem, depuis dossier Sudoku_Solver) :
        java -cp bin/ Solver FILE...
    -> Prend en argument autant de fichier que désiré
    -Ex :
        java -cp bin/ Solver ressources/grille_moyenne1.txt ressources/grille_tres_difficile.txt

    -Exécuter (Sans compiler avec le .jar):
        java -jar Sudoku_Solver-main.jar FILE...
    
    -Générer doc (idem, depuis dossier Sudoku_Solver):
        doxygen Doxyfile
    
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
Ce solver entend résoudre des grilles de sudoku avec ou sans aide de l'utilisateur selon leur difficulté.
Un rapport en pdf détaille les choix de conception du projet.
