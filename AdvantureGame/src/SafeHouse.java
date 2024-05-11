
public class SafeHouse extends NormalLoc {
    
    public SafeHouse(Player player) {
        super(player, "Guvenli ev");
    }
    
    
      public boolean getLocation(){
       player.setHealthy(player.getrHealthy());
          System.out.println("Caniniz yenilendi...");
          System.out.println("Suan guvenli ev adli yerdesiniz...");
          return true;
    }
    
    
}
