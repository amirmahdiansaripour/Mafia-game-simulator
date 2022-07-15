// package Joker;
// import Player.Player;

public class Joker extends Player{
    public Joker(String name_){
        super(name_);
        groupName = JOKER;
    }    
    public void performRoleInNight(Player actedOn){
        return;
    }
    public void getReadyForNextDay(){
        return;
    }
}
