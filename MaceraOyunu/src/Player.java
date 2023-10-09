import java.util.Scanner;

public class Player {
    private Scanner input = new Scanner(System.in);
    private int damage;
    private int health;
    private int defaultHealth;
    private int coin;
    private String charName;
    private Inventory inventory;
    private String name; // Sadece name değerini kullanıcıdan alacağım için
    // constructor'a sadece bu değeri alıyorum. Inventory class'ı oluşturduktan sonra constructor'a bunu da ekleriz.

    // Constructor
    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    // Karakterimizi seçtiğimiz metot
    public void selectChar() {
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println("Characters:");
        System.out.println("==============================================================");
        for (GameChar gameChar : charList) {
            System.out.println(gameChar.getId() + "-" +
                    gameChar.getName() +
                    "\t| Damage:" + gameChar.getDamage() +
                    "\t| Health:" + gameChar.getHealth() +
                    "\t| Coin:" + gameChar.getCoin());
            System.out.println("==============================================================");
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("Which is your character? Please enter the character number: ");
        int selectChar = input.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                System.out.println("Please enter a number between 1 and 3!");
                break;
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Your Character is " + this.getCharName() + "!");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initPlayer(GameChar gameChar) { // seçtiğimiz karakterin hasar,sağlık ve para değerlerini player
        // sınıfından hasar,sağlık ve para değerlerine eşlemek için bu metodu kullanıyoruz.
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setDefaultHealth(gameChar.getHealth());
        this.setCoin(gameChar.getCoin());
        this.setCharName(gameChar.getName());
    }

    public void printInfo() {
        System.out.println(
                "Your Weapon: " + this.getInventory().getWeapons().getName() +
                        " | Your Armor: " + this.getInventory().getArmors().getName() +
                        " | Damage: " + this.getTotalDamage() +
                        " | Block: " + this.getInventory().getArmors().getBlock() +
                        " | Health: " + this.getHealth() +
                        " | Coin: " + this.getCoin());
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getWeapons().getDamage();
    }

    // Getter ve Setter Metotları
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }
}
