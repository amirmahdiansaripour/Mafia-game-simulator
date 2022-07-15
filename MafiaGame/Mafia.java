// package Mafia;
// import Player.Player;

public class Mafia extends Player{
    public Mafia(String name_){
        super(name_);
        groupName = MAFIA;
    }    
    public void performRoleInNight(Player actedOn){
        vote(actedOn);
    }
    public void getReadyForNextDay(){
        return;
    }
}
