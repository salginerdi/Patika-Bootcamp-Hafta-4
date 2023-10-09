public abstract class NormalLocation extends Location {
    public NormalLocation(int id, Player player, String name) {
        super(id, player, name);
    }

    @Override
    public boolean onLocation() { // Normal location'larda oyunu kaybetme ihtimalimiz olmadığı için true döndürürüz.
        return true;
    }
}
