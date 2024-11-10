package composite;

//PATTERN OBSERVER
public interface Subscriber{
    /*Les objets qui implémentent Subscriber sont notifiées lorsque
    le nombre de valeurs possibles d'une cellule vide change */
    public void update(int i);
}