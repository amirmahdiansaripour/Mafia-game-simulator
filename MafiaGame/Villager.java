// package Villager;
// import Player.Player;

public class Villager extends Player{
    public Villager(String name_){
        super(name_);
        groupName = VILLAGER;
    }
    public void performRoleInNight(Player actedOn){
        return;
    }
    public void getReadyForNextDay(){
        return;
    }
}
