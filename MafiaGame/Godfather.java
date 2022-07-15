// package Godfather;
// import Player.Player;

public class Godfather extends Player{
    public Godfather(String name_){
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
