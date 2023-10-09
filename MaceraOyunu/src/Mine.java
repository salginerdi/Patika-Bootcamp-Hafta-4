import java.util.Random;

public class Mine extends BattleLoc {
    Random random = new Random();
    private Player player;

    public Mine(Player player) {
        super(6, player, "Mine", new Snake(), " ", 5);
        this.player = player;
        String awardMessage = getRandomAward(player);
        getObstacle().setAward(Integer.parseInt(awardMessage));
    }

    private void addCoinsToPlayer(int coinsToAdd) {
        int currentCoins = getPlayer().getCoin();
        getPlayer().setCoin(currentCoins + coinsToAdd);
    }

    private String getRandomAward(Player player) {
        double chance = random.nextDouble() * 100;
        String result = "";

        if (chance < 15) {
            // Silah kazanma ihtimali
            double weaponChance = random.nextDouble() * 100;
            if (weaponChance < 50) {
                Weapons gun = new Weapons("Gun", 2, 3, 35);
                getPlayer().getInventory().setWeapons(gun);
                result = "Congratulations, you've won a Gun!";
            } else if (weaponChance < 80) {
                Weapons rifle = new Weapons("Rifle", 3, 7, 45);
                getPlayer().getInventory().setWeapons(rifle);
                result = "Congratulations, you've won a Rifle!";
            } else {
                Weapons sword = new Weapons("Sword", 1, 2, 25);
                getPlayer().getInventory().setWeapons(sword);
                result = "Congratulations, you've won a Sword!";
            }
        } else if (chance < 35) {
            // Zırh kazanma ihtimali
            double armorChance = random.nextDouble() * 100;
            if (armorChance < 50) {
                Armors light = new Armors(1, "light", 1, 15);
                getPlayer().getInventory().setArmors(light);
                result = "Congratulations, you've won a Light Armor!";
            } else if (armorChance < 80) {
                Armors mid = new Armors(2, "mid", 3, 25);
                getPlayer().getInventory().setArmors(mid);
                result = "Congratulations, you've won a Mid Armor!";
            } else {
                Armors heavy = new Armors(3, "heavy", 5, 40);
                getPlayer().getInventory().setArmors(heavy);
                result = "Congratulations, you've won a Heavy Armor!";
            }
        } else if (chance < 25) {
            // Para kazanma ihtimali
            double coinChance = random.nextDouble() * 100;
            if (coinChance < 20) {
                addCoinsToPlayer(10);
                result = "Congratulations, you've won 10 Coin!";
            } else if (coinChance < 50) {
                addCoinsToPlayer(5);
                result = "Congratulations, you've won 5 Coin!";
            } else {
                addCoinsToPlayer(1);
                result = "Congratulations, you've won 1 Coin";
            }
        } else if (chance < 45) {
            result = "Sorry, you've won nothing!";
        }
        return result;
    }
}
