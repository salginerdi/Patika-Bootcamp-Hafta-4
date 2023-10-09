import java.util.Random;

public class Snake extends Obstacle {
    public Snake() {
        super(4, "Snake", generateRandomDamage(), 12,0);
    }

    @Override
    public void setAward(int award) {
        super.setAward(award);
    }

    private static int generateRandomDamage() { // Snake karakterimizin damage özelliğinin 3 ve 6 arasında random bir sayı
        // olmasını dağlayan metot
        Random random = new Random();
        int minimumDamage = 3;
        int maximumDamage = 6;
        int randomDamage = random.nextInt(maximumDamage - minimumDamage + 1) + minimumDamage;
        return randomDamage;
    }
}
