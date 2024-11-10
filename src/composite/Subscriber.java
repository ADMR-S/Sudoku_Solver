package composite;

//PATTERN OBSERVER
/**
 * Les objets qui implémentent Subscriber sont notifiées lorsque le nombre de valeurs possibles d'une cellule vide change
 */
public interface Subscriber{
    /**
     * Fonction d'actualisation pour prendre en compte les notfications côté Subscriber
     *
     * @param  i  le nombre de valeurs possibles restantes à l'EmptyCell qui appelle la méthode
     */
    public void update(int i);
}