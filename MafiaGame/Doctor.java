// package Doctor;
// import Player.Player;
// import Errors.*;
import static java.lang.System.*;

public class Doctor extends Player{
    private boolean alreadyHealed;
    public Doctor(String name){
        super(name);
        groupName = VILLAGER;
        alreadyHealed = false;
    }    
    public void getReadyForNextDay(){
        alreadyHealed = false;
    }
    public void performRoleInNight(Player actedOn){
        try{
            if(alreadyHealed){
                throw new Errors.DoctorHasAlreadyHealed();
            }
            if(actedOn.playerDead()){
                throw new Errors.PatientIsDead();
            }
            actedOn.saveLife();
            alreadyHealed = true;    
        }
        catch(Errors.PatientIsDead ex){
            out.print(ex.getMessage());
        }
        catch(Errors.DoctorHasAlreadyHealed ex){
            out.println(ex.getMessage());
        }
    }
}
