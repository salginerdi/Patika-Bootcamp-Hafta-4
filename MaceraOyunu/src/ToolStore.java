public class ToolStore extends NormalLocation {
    public ToolStore(Player player) {
        super(2, player, "Tool Store");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Welcome to Tool House!");
        boolean showMenu = true;
        while (showMenu) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1-Weapons");
            System.out.println("2-Armors");
            System.out.println("3-Exit");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("Your choice : ");
            int selectCase = input.nextInt();
            while (selectCase < 1 || selectCase > 3) {
                System.out.print("You have made an invalid transaction! Please select again: ");
                selectCase = input.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapons();
                    buyWeapon();
                    break;
                case 2:
                    printArmors();
                    buyArmors();
                    break;
                case 3:
                    System.out.println("See You Again!");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapons() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=============== Weapons ================");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Weapons w : Weapons.weapons()) {
            System.out.println("< " + w.getId() + " - " + w.getName() + " | Coin: " + w.getPrice() + " | Damage: " + w.getDamage() + " >");
        }
        System.out.println("< 0 - Exit! >");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void buyWeapon() {
        System.out.print("Choose a weapon: ");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapons.weapons().length) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("You have made an invalid transaction! Please select again: ");
            selectWeaponID = input.nextInt();
        }

        if (selectWeaponID != 0) {
            Weapons selectedWeapon = Weapons.getWeaponObjById(selectWeaponID);
            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getCoin()) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("You don't have enough coin to buy this weapon!");
                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(selectedWeapon.getName() + " purchased!");
                    int balance = this.getPlayer().getCoin() - selectedWeapon.getPrice();
                    this.getPlayer().setCoin(balance);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Your remaining coin: " + this.getPlayer().getCoin());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.getPlayer().getInventory().setWeapons(selectedWeapon);
                }
            }

        }
    }

    public void printArmors() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("================ Armors ===============");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Armors a : Armors.armors()) {
            System.out.println("< " + a.getId() + " - " + a.getName() + " | Coin: " + a.getPrice() + " | Damage: " + a.getBlock() + " >");
        }
        System.out.println("< 0 - Exit! >");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void buyArmors() {
        System.out.print("Choose a armour: ");
        int selectArmorID = input.nextInt();
        while (selectArmorID < 0 || selectArmorID > Weapons.weapons().length) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("You have made an invalid transaction! Please select again: ");
            selectArmorID = input.nextInt();
        }
        if (selectArmorID != 0) {
            Armors selectedArmor = Armors.getArmorObjById(selectArmorID);
            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getCoin()) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("You don't have enough coin to buy this armor!");
                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(selectedArmor.getName() + " purchased!");
                    int balance = this.getPlayer().getCoin() - selectedArmor.getPrice();
                    this.getPlayer().setCoin(balance);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Your remaining coin: " + this.getPlayer().getCoin());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.getPlayer().getInventory().setArmors(selectedArmor);
                }
            }
        }
    }

}

