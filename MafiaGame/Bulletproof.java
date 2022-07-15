// package Bulletproof;
// import Player.Player;
public class Bulletproof extends Player{
    // private int extraLife; 
    public Bulletproof(String name){
        super(name);
        extraLife = 1;
        groupName = VILLAGER;
    }
    public void performRoleInNight(Player actedOn){
        return;
    }
    public void getReadyForNextDay(){
        return;
    }
}
