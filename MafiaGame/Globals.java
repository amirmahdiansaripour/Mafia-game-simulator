// package Globals;

import java.util.*;

public interface Globals {
    int UNDEFINED = -1;
    String NULL_STRING = "";
    char DELIMETER = ' ';
    String ASSIGN_ROLE = "assign_role";
    String GET_GAME_STATE = "get_game_state";
    String CREATE_GAME = "create_game";
    String START_GAME = "start_game";
    String READY_FOR_MATCH = "Ready? Set! Go.";
    String END_NIGHT = "end_night";
    String DAY = "Day ";
    String MAFIA_TRIED_TO_KILL = "Mafia tried to kill ";
    String WAS_KILLED_STATEMENT = " was killed";
    String SILENCED_STATEMENT = "Silenced ";
    String JOKER_WON = "Joker won";
    String MAFIA_WON = "Mafia won";
    String VILLAGERS_WON = "Villagers won";
    String SWAP_CHARACTER = "swap_character";
    String END_VOTE = "end_vote";
    String DIED = " died";
    String NIGHT = "Night ";
    String YES_STATEMANT = "Yes";
    String NO_STATEMENT = "No";

    String BULLETPROOF = "bulletproof";
    String VILLAGER = "Villager";
    String MAFIA = "Mafia";
    String DETECTIVE = "detective";
    String JOKER = "joker";
    String SILENCER = "silencer";
    String GODFATHER = "godfather";
    String GOD = "god";
    String DOCTOR = "doctor";
    String NO_GAME_CREATED = "No game created";
    String ALL_DONT_HAVE_ROLES = "One or more players do not have a role";
    String GAME_HAS_ALREADY_STARTED = "Game has already strated";
    String CANT_SWAP_BEFORE_END_OF_NIGTH = "Can't swap before end of night";
    String USER_NOT_JOINED = "User not joined";
    String USER_CAN_NOT_WAKE_UP_DURING_NIGHT = "User can not wake up during night";
    String USER_IS_DEAD = "User is dead";
    String VOTING_IN_PROGRESS = "Voting in progress";
    String CHARACTERS_ALREADY_SWAPPED = "Characters already swapped";
    String VOTER_IS_SILENCED = "Voter is silenced";
    String VOTEE_ALREADY_DEAD = "Votee already dead";
    String PERSON_IS_DEAD = "Person is dead";
    String DETECTIVE_HAS_ALREADY_ASKED = "Detective has already asked";
    String SUSPECT_IS_DEAD = "Suspect is dead";
    String DOCTOR_HAS_ALREADY_HEALED = "Doctor has already healed";
    ArrayList<String> ROLES_ALLOWED_IN_NIGHT = new ArrayList<String>(){
        {
            add(SILENCER);add(MAFIA);add(DOCTOR);add(DETECTIVE);add(GODFATHER);
        }
    };
    
}
