public class SafeHouse extends NormalLocation {
    public SafeHouse(Player player) {
        super(1, player, "Safe House");
    }

    @Override
    public boolean onLocation() {
        System.out.println("You are in a Safe House");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Your Power is Renewed!");
        this.getPlayer().setHealth(this.getPlayer().getDefaultHealth());
        return true;
    }
}
