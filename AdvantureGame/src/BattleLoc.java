
public abstract class BattleLoc extends Location {

    protected Obstacle obstacle;
    protected String award;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award) {
        super(player);
        this.name = name;
        this.obstacle = obstacle;
        this.award = award;
    }

    public boolean getLocation() {
        int obsCount = obstacle.count();
        System.out.println("Su an buradasiniz: " + this.getName());
        System.out.println("Dikkatli ol ! Burada " + obsCount + " tane" + obstacle.getName() + " yasiyor !");
        System.out.print("<S>avas veya <K>ac: ");
        String selCase = sc.nextLine();
        selCase = selCase.toUpperCase();
        if (selCase.equals("S")) {
            if (combat(obsCount)) {
                System.out.println(this.getName() + "bolgesindeki tum dusmanlari temizlediniz !");
                if (this.award.equals("food") && player.getInv().isFood() == false) {
                    System.out.println(this.award + " Kazandiniz !");
                    player.getInv().setFood(true);
                } else if (this.award.equals("Water") && player.getInv().isWater() == false) {
                    System.out.println(this.award + " Kazandiniz !");
                    player.getInv().setWater(true);
                } else if (this.award.equals("Firewood") && player.getInv().isFirewood() == false) {
                    System.out.println(this.award + " Kazandiniz !");
                    player.getInv().setFirewood(true);
                    return true;
                }
            } else {
                System.out.println("Oldunuz !");
                return false;
            }
        }
        return true;
    }

    public boolean combat(int obsCount) {
        for (int i = 0; i < obsCount; i++) {
            int defObsHealth = obstacle.getHealth();
            playerStats();
            enemyStats();
            while (player.getHealthy() > 0 && obstacle.getHealth() > 0) {
                System.out.print("<V>ur veya <K>ac :");
                String SelCase = sc.nextLine();
                SelCase = SelCase.toUpperCase();
                if (SelCase.equals("V")) {
                    System.out.println("Siz vurdunuz !");
                    obstacle.setHealth(obstacle.getHealth() - player.getTotalDamage());
                    afterHit();
                    if (obstacle.getHealth() > 0) {
                        System.out.println();
                        System.out.println("Canavar size vurdu !");
                        player.setHealthy(player.getHealthy() - (obstacle.getDamage() - player.getInv().getArmor()));
                        afterHit();
                    }

                } else {
                    break;
                }
                if (obstacle.getHealth() <= 0 && player.getHealthy() > 0) {
                    System.out.println("Dusmani yendiniz !");
                    player.setMoney(player.getMoney() + obstacle.getAward());
                    System.out.println("Guncel paraniz: " + player.getMoney());
                    obstacle.setHealth(defObsHealth);
                } else {
                    return false;
                }
                System.out.println("----------------------");
            }

        }
        return true;
    }

    public void playerStats() {
        System.out.println("Oyuncu degerleri\n--------------");
        System.out.println("Can: " + player.getHealthy());
        System.out.println("Hasar: " + player.getDamage());
        System.out.println("Para: " + player.getMoney());
        if (player.getInv().getDamage() > 0) {
            System.out.println("Silah:" + player.getInv().getaName());
        }
        if (player.getInv().getArmor() > 0) {
            System.out.println("Zirh: " + player.getInv().getaName());
        }
    }

    public void enemyStats() {
        System.out.println("\n" + obstacle.getName() + " Degerleri\n--------------");
        System.out.println("Can: " + obstacle.getHealth());
        System.out.println("Hasar: " + obstacle.getDamage());
        System.out.println("Odul: " + obstacle.getAward());
    }

    public void afterHit() {
        System.out.println("Oyuncu cani: " + player.getHealthy());
        System.out.println(obstacle.getName() + " Cani: " + obstacle.getHealth());
        System.out.println();
    }

}
