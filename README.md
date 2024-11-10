# Sudoku_Solver

Programme conçu et implémenté par Adam Mir-sadjadi et Florent Bélot

-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Commandes Terminal :

-Compiler (depuis dossier Sudoku_Solver) :
`javac -d bin/ src/*.java src/*/*.java src/*/*/*.java`

-Exécuter (Idem, depuis dossier Sudoku_Solver) :

-> Prend en arguments autant de fichiers que désiré : 
    java -cp bin/ Solver FILE...
    
Ex :
    java -cp bin/ Solver ressources/grille_moyenne1.txt ressources/grille_tres_difficile.txt
    
    -Générer doc (idem, depuis dossier Sudoku_Solver):
        doxygen Doxyfile
    
-------------------------------------------------------------------------------------------------------------------------------------------------------------------

Notes : 

La classe CellBase est plus une classe "Composant" que la classe Composant actuelle. On implémente bien un composite à mon sens (Produit = Cellule ou CelluleVide) mais les boîtes et les produits ne sont pas considérés de la même façon (Lignes/Colonnes/Squares n'ont rien en commun avec les Cell/EmptyCell), et ce serait de toute façon une erreur de le faire selon moi à moins que l'on y trouve un intérêt dans les Deduction Rules. A discuter.

ArrayLists pour les valeurs possibles (.size() pour savoir s'il n'y en a plus qu'une, ou si une mauvaise valeur a été entrée si taille 0).

Le Solver pourrait être le maillon principal d'une Chain of Responsibility entre les différentes DR et le joueur s'il doit entrer un chiffre manuellement.

Design Pattern à implémenter :
- Visiteur (éviter instanceof)
- Iterator (remplacerait le composite et renverrait les lignes, colonnes et carrés de la grille)
- Factory pour les cellules et les composants
- Revoir le composite (passer grid en composant, envisager comment passer Cell et EmptyCell en composant ?)
- Chain of responsibility ? (Solver -> DR0 -> Solver -> DR1 -> Solver -> DR2 -> Solver -> DR3 -> Solver) ou pour les règles qui appellent les règles précédentes

Observer : vérifie si des cellules n'ont plus de valeur possble, auquel cas prévient le solver et déclenche les memento pour recharger la grille pré-complétée
Pourrait prévenir le solver pour qu'il déclenche la DR1 quand on fait tomber le nombre de valeurs possibles à 1, ou la DR2 si condition remplie, ou DR3...
Empiler d'abord les cellules qui ont le moins de valeurs possibles grâce à l'Observer ?

Itérateur qui retourne tous les carrés, toutes les lignes ou toutes les colonnes pour éviter le composite et la duplication d'infos (on aurait juste la grille représentée par une HashMap ((xpos, ypos), valeur) et des méthodes d'iterator pour chopper les carrés, lignes et colonnes)

Singleton pour la pile / le solver ?

Mettre la solution dans un fichier pour chaque sudoku ?

Doxygen

https://fr.wikipedia.org/wiki/Math%C3%A9matiques_du_sudoku
NP-Complet

Tableau de 9 cases plutôt qu'ArrayList -> bien pour iterator
