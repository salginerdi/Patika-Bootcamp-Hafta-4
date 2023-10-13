import java.util.Random;

public abstract class BattleLoc extends Location {
    Random random = new Random();
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(int id, Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(id, player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1; // Burada +1 eklememizin sebebi MaxObstacle sayımızdan 1 az canavar
        // üreteceği içindir. Aynı sayıda üretmesi için ekliyoruz.
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("You're here now: " + this.getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Be Careful! " + obsNumber + " " + this.getObstacle().getName() + " living here");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("<F>ight or <R>un!");
        String selectCase = input.nextLine().toUpperCase();
        if (selectCase.equals("F") && combat(obsNumber)) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + "You have defeated all obstacles!");
            switch (this.award) {
                case "Food!":
                    System.out.println("You won the food!");
                    getPlayer().getInventory().setFood(true);
                    break;
                case "Water!":
                    System.out.println("You won the water!");
                    getPlayer().getInventory().setWater(true);
                    break;
                case "Firewood!":
                    System.out.println("You won the firewood!");
                    getPlayer().getInventory().setFirewood(true);
                    break;
            }
            return true;

        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("You're dead!");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            Mine mine = new Mine(getPlayer());

            this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());
            playerStats();
            obstacleStats(i);

            // Rastgele bir sayı üret (0.0 - 1.0 arası)
            double randomValue = Math.random();

            // Eğer rastgele sayı 0.5'ten küçükse obstacle önce vurur
            boolean obstacleHitsFirst = randomValue < 0.5;

            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.print("<H>it or <R>un!");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("H")) {
                    if (obstacleHitsFirst) {
                        System.out.println("Obstacle Hit You!");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmors().getBlock();
                        if (obstacleDamage < 0) {
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    } else {
                        System.out.println("You Hit!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    }
                    obstacleHitsFirst = !obstacleHitsFirst; // Sırayı değiştir
                } else {
                    return false;
                }
            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Congratulations, You Won!");
                if (this.getObstacle().getName().equals("Snake")) {
                    System.out.println(mine.getMessage());
                }
                System.out.println();
                if (!getObstacle().getName().equals("Snake")) {
                    System.out.println(this.getObstacle().getAward() + " you have won coin!");
                }
                this.getPlayer().setCoin(this.getPlayer().getCoin() + this.getObstacle().getAward());
                System.out.println("Your current coin: " + this.getPlayer().getCoin());
            } else {
                return false;
            }
        }
        return true;
    }


    public void afterHit() {
        System.out.println("Your Health: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Health: " + this.getObstacle().getHealth());
    }

    public void playerStats() {
        System.out.println("Player Statistics");
        System.out.println("-----------------");
        System.out.println("Health: " + this.getPlayer().getHealth());
        System.out.println("Weapon: " + this.getPlayer().getInventory().getWeapons().getName());
        System.out.println("Damage: " + this.getPlayer().getTotalDamage());
        System.out.println("Armor: " + this.getPlayer().getInventory().getArmors().getName());
        System.out.println("Block: " + this.getPlayer().getInventory().getArmors().getBlock());
        System.out.println("Coin: " + this.getPlayer().getCoin());
    }

    public void obstacleStats(int i) {
        System.out.println(i + "." + this.getObstacle().getName() + " Statistics");
        System.out.println("-----------------");
        System.out.println("Health: " + this.getObstacle().getHealth());
        System.out.println("Damage: " + this.getObstacle().getDamage());
        System.out.println("Award: " + this.getObstacle().getAward());
    }
}
