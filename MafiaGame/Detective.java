// package Detective;
// import Player.Player;
// import Errors.*;
import static java.lang.System.*;

public class Detective extends Player{
    protected boolean aksedOnce;
    public Detective(String name){
        super(name);
        groupName = VILLAGER;
        aksedOnce = false;
    }

    public void getReadyForNextDay(){
        aksedOnce = false;
    }

    public void performRoleInNight(Player actedOn){
        try{
            if(aksedOnce){
                throw new Errors.DetectiveHasAlreadyAsked();
            }
            if(actedOn.playerDead()){
                throw new Errors.SuspectIsDead();
            }
            if(actedOn.getGroup().equals(MAFIA) && actedOn.getRole().equals(GODFATHER) == false){
                out.println(YES_STATEMANT);
            }
            else{
                out.println(NO_STATEMENT);
            }
            aksedOnce = true;
        }
        catch(Errors.DetectiveHasAlreadyAsked ex){
            out.println(ex.getMessage());
        }
        catch(Errors.SuspectIsDead ex){
            out.println(ex.getMessage());
        }
    }
}
