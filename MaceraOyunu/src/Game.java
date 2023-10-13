import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start() { // Oyunu başlattığımız metot
        System.out.println("Welcome to the Adventure Game!");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("Please enter a character name: ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Hello " + player.getName() + "; Welcome to this dark and misty island!");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("What happened here is real!");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Please choose a character that you find closest to you!");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player.selectChar();

        // bu bilgiyi sürekli soracağı için bir while döngüsü oluşturuyoruz. O yüzden metodu da while döngüsü
        // içerisine yerleştirdik.
        Location location = null;
        while (true) {
            if(player.getInventory().isFood() && player.getInventory().isWater() && player.getInventory().isFirewood()){
                System.out.println("Congratulations, You Won The Game");
                break;
            }
            player.printInfo();
            System.out.println();
            System.out.println("Locations:");
            System.out.println("==============================================================");
            System.out.println("1-Safe House: You're safe here, no enemies!");
            System.out.println("==============================================================");
            System.out.println("2-Tool Store: You can buy weapons or armor!");
            System.out.println("==============================================================");
            System.out.println("3-Cave: Be Careful! There are obstacles here. ");
            System.out.println("==============================================================");
            System.out.println("4-Forest: Be Careful! There are obstacles here. ");
            System.out.println("==============================================================");
            System.out.println("5-River: Be Careful! There are obstacles here. ");
            System.out.println("==============================================================");
            System.out.println("6-Mine: Be Careful! There are obstacles here. ");
            System.out.println("==============================================================");
            System.out.println("0-Exit Game!\n");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("Please choose your destination: ");
            int selectLoc = input.nextInt();

            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if(player.getInventory().isFood()){
                        System.out.println("You can't enter this area!");
                        continue;
                    }else{
                        location = new Cave(player);
                        break;
                    }
                case 4:
                    if(player.getInventory().isFirewood()){
                        System.out.println("You can't enter this area!");
                        continue;
                    }else{
                        location = new Forest(player);
                        break;
                    }
                case 5:
                    if(player.getInventory().isWater()){
                        System.out.println("You can't enter this area!");
                        continue;
                    }else{
                        location = new River(player);
                        break;
                    }
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    System.out.println("Please enter a valid location number!");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (location == null) {
                System.out.println("You're logged out of the game! See you again!");
                break;
            }

            if (!location.onLocation()) {
                System.out.println("Game Over");
                break;
            }
        }

    }
}
