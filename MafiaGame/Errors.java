// package Errors;
// import Globals.Globals;

public class Errors extends Exception implements Globals{
    public Errors(String errTxt){
        super(errTxt);
    }
    public static class NoGameCreated extends Errors{
        public NoGameCreated(){
            super(NO_GAME_CREATED);
        }
    }
    public static class AllDontHaveRoles extends Errors{
        public AllDontHaveRoles(){
            super(ALL_DONT_HAVE_ROLES);
        }
    }
    public static class GameHasAlreadyStarted extends Errors{
        public GameHasAlreadyStarted(){
            super(GAME_HAS_ALREADY_STARTED);
        }
    }
    public static class CantSwapBeforeEndofNight extends Errors{
        public CantSwapBeforeEndofNight(){
            super(CANT_SWAP_BEFORE_END_OF_NIGTH);
        }
    }
    public static class UserNotJoined extends Errors{
        public UserNotJoined(){
            super(USER_NOT_JOINED);
        }
    }
    public static class UserCantWakeUpDuringNight extends Errors{
        public UserCantWakeUpDuringNight(){
            super(USER_CAN_NOT_WAKE_UP_DURING_NIGHT);
        }
    }
    public static class UserIsDead extends Errors{
        public UserIsDead(){
            super(USER_IS_DEAD);
        }
    }
    public static class VotingInProgress extends Errors{
        public VotingInProgress(){
            super(VOTING_IN_PROGRESS);
        }
    }
    public static class CharactersAlreadySwapped extends Errors{
        public CharactersAlreadySwapped(){
            super(VOTING_IN_PROGRESS);
        }
    }
    public static class VoterIsSilenced extends Errors{
        public VoterIsSilenced(){
            super(VOTER_IS_SILENCED);
        }
    }
    public static class VoteeAlreadyDead extends Errors{
        public VoteeAlreadyDead(){
            super(VOTEE_ALREADY_DEAD);
        }
    }
    public static class PersonIsDead extends Errors{
        public PersonIsDead(){
            super(PERSON_IS_DEAD);
        }
    }
    public static class DetectiveHasAlreadyAsked extends Errors{
        public DetectiveHasAlreadyAsked(){
            super(DETECTIVE_HAS_ALREADY_ASKED);
        }
    }
    public static class SuspectIsDead extends Errors{
        public SuspectIsDead(){
            super(SUSPECT_IS_DEAD);
        }
    }
    public static class PatientIsDead extends Errors{
        public PatientIsDead(){
            super(SUSPECT_IS_DEAD);
        }
    }
    public static class DoctorHasAlreadyHealed extends Errors{
        public DoctorHasAlreadyHealed(){
            super(SUSPECT_IS_DEAD);
        }
    }

}