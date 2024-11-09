# Sudoku_Solver

Programme conçu et implémenté par Adam Mir-sadjadi et Florent Bélot

-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Commandes Terminal :

-Compiler (depuis dossier Sudoku_Solver) :
`javac -d bin/ src/*.java src/*/*.java`

-Exécuter (Idem, depuis dossier Sudoku_Solver) :

-> Prend en arguments autant de fichiers que désiré : 
    java -cp bin/ Solver FILE...
    
Ex :
    java -cp bin/ Solver ressources/grille_moyenne1.txt ressources/grille_tres_difficile.txt
    
-------------------------------------------------------------------------------------------------------------------------------------------------------------------

Notes : 

La classe CellBase est plus une classe "Composant" que la classe Composant actuelle. On implémente bien un composite à mon sens (Produit = Cellule ou CelluleVide) mais les boîtes et les produits ne sont pas considérés de la même façon (Lignes/Colonnes/Squares n'ont rien en commun avec les Cell/EmptyCell), et ce serait de toute façon une erreur de le faire selon moi à moins que l'on y trouve un intérêt dans les Deduction Rules. A discuter.

Le Solver pourrait être le maillon principal d'une Chain of Responsibility entre les différentes DR et le joueur s'il doit entrer un chiffre manuellement.

Design Pattern à implémenter :
- Visiteur (éviter instanceof)
- Observateur
- Singleton (grille)
- Memento
- Revoir le composite
- Chain of responsibility ? (Solver -> DR0 -> Solver -> DR1 -> Solver -> DR2 -> Solver -> DR3 -> Solver) ou pour les règles qui appellent les règles précédentes

Observer : vérifie si des cellules n'ont plus de valeur possble, auquel cas prévient le solver et déclenche le memento pour recharger la grille pré-complétée

-> Mettre les cellules dans une HashMap (clé (colonne,ligne))? (pour les itérations des règles)
ArrayLists pour les valeurs possibles (.size() pour savoir s'il n'y en a plus qu'une, ou si une mauvaise valeur a été entrée si taille 0).

Strategy : créer classe context et modifier les DR

Itérateur sur les carrés, lignes, colonnes pour éviter le composite et la duplication d'infos

Utilliser une librairie pour la Pile plutôt que coder la classe nous-même ?
