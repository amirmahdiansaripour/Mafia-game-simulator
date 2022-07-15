// package Silencer;
// import Player.Player;
// import Errors.*;
import static java.lang.System.*;

public class Silencer extends Player{
    private boolean silencedOnce;
    
    public Silencer(String name){
        super(name);
        groupName = MAFIA;
        silencedOnce = false;
    }
    public void getReadyForNextDay(){
        silencedOnce = false;
    }

    public void performRoleInNight(Player actedOn){
        if(!silencedOnce){
            try{
                if(actedOn.playerDead()){
                    throw new Errors.PersonIsDead();
                }
                actedOn.silenceSomeone();
                silencedOnce = true;
                return;    
            }
            catch(Errors.PersonIsDead ex){
                out.println(ex.getMessage());                
            }
        }
        else{
            vote(actedOn);
            return;
        }
    }

}
