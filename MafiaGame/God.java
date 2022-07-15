// package God;
// import Player.Player;
// import Errors.*;
// import Globals.Globals;
// import Godfather.Godfather;
// import Joker.Joker;
// import Detective.Detective;
// import Doctor.Doctor;
// import Villager.Villager;
// import Mafia.Mafia;
// import Godfather.Godfather;
// import Silencer.Silencer;
// import Bulletproof.Bulletproof;

import static java.lang.System.*;
import java.util.*;

public class God implements Globals{

    Scanner sc = new Scanner(System.in);
    
    
    protected ArrayList<String> namesOfPlayers;
    protected ArrayList<Player> players;
    protected String role;
    protected boolean swappedOnce; 
    
    public God(){
        players = new ArrayList<Player>();
        namesOfPlayers = new ArrayList<String>();
    }

    public void swap(int counter1, int counter2){
        Collections.swap(players, counter1, counter2);
    }

    public int found(String toBeFound, ArrayList<String> searchBetween){
        for(int counter = 0; counter < searchBetween.size(); counter++){
            if(toBeFound.equals(searchBetween.get(counter)))
                return counter;
        }
        return UNDEFINED;
    }

    public void printKilledPlayer(){
        int counter;
        for (counter = 0; counter < players.size(); counter++) {
            if ((players.get(counter).playerRemoved() == false) && players.get(counter).playerDead()) {
                players.get(counter).playerIsRemoved();
                out.println(players.get(counter).getName() + WAS_KILLED_STATEMENT);
                break;
            }
        }
        return;
    }

    public void printSilencedPlayers(){
        int counter;
        ArrayList<String> silencedPlayers = new ArrayList<String>();
        for (counter = 0; counter < players.size(); counter++) {
            if (players.get(counter).playerSilenced())
                silencedPlayers.add(players.get(counter).getName());
    
        }
        
        Collections.sort(silencedPlayers.subList(0, silencedPlayers.size()));
        
        for (counter = 0; counter < silencedPlayers.size(); counter++) {
            if(counter == 0)
                out.print(SILENCED_STATEMENT);
            out.print(silencedPlayers.get(counter));
            if(counter != silencedPlayers.size()-1)
                out.print(DELIMETER);
            
            if(counter == silencedPlayers.size()-1)
                out.print("\n");
        }
    }

    public void lastNightEvents(){
        printKilledPlayer();
        printSilencedPlayers();
    }

    public boolean anyOneHasRole(){
        // out.println(namesOfPlayers.size());
        // out.println(players.size());
        
        if(namesOfPlayers.size() > players.size()){
            return false;
        }
        return true;
    }

    public int[] countMafiaAndVillagers(){
        int numOfVillagers = 0;
        int numOfMafia = 0;
        //out.print(namesOfPlayers);
        //out.print("\n");
        for(int i = 0; i < players.size(); i++){
            //players.get(i).printPlayerData();
            if(players.get(i).getGroup().equals(MAFIA)&&
                !players.get(i).playerDead())
                numOfMafia++;
            
            else if(players.get(i).getGroup().equals(VILLAGER)&&
                !players.get(i).playerDead())
                numOfVillagers++;
        }
        return new int[]{numOfMafia, numOfVillagers};
    }
    
    public boolean checkWinner(int killedPlayerIndex, boolean isDay){
        if (players.get(killedPlayerIndex).getRole().equals(JOKER) 
        && isDay && players.get(killedPlayerIndex).playerDead()) {
            out.println(JOKER_WON);
            return true;
        }
        int arr[] = new int[2];
        arr = countMafiaAndVillagers();
        if (arr[0] == 0) {
            out.println(VILLAGERS_WON);
            return true;
        }
        else if (arr[1] <= arr[0]) {
            out.println(MAFIA_WON);
            return true;
        }
        return false;
    }


    void swapJumbled(int first, int second){
        String changedName = players.get(first).getName();
        players.get(first).changeData(players.get(second).getName());
        players.get(second).changeData(changedName);
    }

    public void jumbled(String data){
        // out.println("Darham Barham\n\n");
        ArrayList<String> swappedPlayers = seperateBySpaces(data);
        int firstPlayerIndex = found(swappedPlayers.get(1), namesOfPlayers);
        int secondPlayerIndex = found(swappedPlayers.get(2), namesOfPlayers);
        try{
            if(firstPlayerIndex == UNDEFINED || secondPlayerIndex == UNDEFINED){
                throw new Errors.UserNotJoined();
            }
            if(players.get(firstPlayerIndex).playerDead() || players.get(secondPlayerIndex).playerDead()){
                throw new Errors.UserIsDead();
            }
            if(swappedOnce){
                throw new Errors.CharactersAlreadySwapped();
            }
            swapJumbled(firstPlayerIndex, secondPlayerIndex);
            swappedOnce = true;
            sortBasedOnNames();
        }
        catch(Errors.CharactersAlreadySwapped ex){
            out.println(ex.getMessage());
        }
        catch(Errors.UserNotJoined ex){
            out.println(ex.getMessage());
        }
        catch(Errors.UserIsDead ex){
            out.println(ex.getMessage());
        }
    }

    public ArrayList<Integer> findFrequency(int maxIndex, ArrayList<Integer> indexes){
        ArrayList<Integer> indexesOfMaxElements = new ArrayList<Integer>();
        for(int counter = 0; counter < indexes.size(); counter++){
            if(indexes.get(counter) == maxIndex){
                indexesOfMaxElements.add(counter);
            }
        }
        return indexesOfMaxElements;
    }

    public int leastAlphabetNameSuspect(ArrayList<Integer> indexesOfMaxElements){
        int counter;
        ArrayList<String> multipleSuspects = new ArrayList<String>();
        for(counter = 0; counter < indexesOfMaxElements.size(); counter++){
            int index = indexesOfMaxElements.get(counter);
            multipleSuspects.add(players.get(index).getName());
        }
        String leastAlphabetName = Collections.min(multipleSuspects);
        int indexOfKilledPlayer = found(leastAlphabetName, namesOfPlayers);
        return indexOfKilledPlayer;
    }

    public int whoWasPlannedToBeKilled(){
        ArrayList<Integer> votesToEachPlayer = new ArrayList<Integer>();
        int counter;
        for(counter = 0; counter < players.size(); counter++)
            votesToEachPlayer.add(players.get(counter).getNumOfVotes());
        
        int maxNumOfVotes = Collections.max(votesToEachPlayer);
        ArrayList<Integer> indexesOfMaxElements = findFrequency(maxNumOfVotes, votesToEachPlayer);
        if(indexesOfMaxElements.size() > 1){
            return leastAlphabetNameSuspect(indexesOfMaxElements);
        }
        else{
            return indexesOfMaxElements.get(0);
        }
    }

    public int[] findVoterAndVotee(String data){
        ArrayList<String> voterAndVotee = seperateBySpaces(data);
        int voterFound = found(voterAndVotee.get(0), namesOfPlayers);
        int voteeFound = found(voterAndVotee.get(1), namesOfPlayers);
        return new int[]{voterFound, voteeFound};
    }

    public boolean playerKilledAtDay(){
        int killedPlayerIndex = whoWasPlannedToBeKilled();
        players.get(killedPlayerIndex).playerIsKilled();
        players.get(killedPlayerIndex).playerIsRemoved();
        out.println(players.get(killedPlayerIndex).getName() + DIED);
        return checkWinner(killedPlayerIndex, true);
    }
    
    public boolean handleVoteInday(boolean votingBegan, String data){
        try{
            int indexes_of_voterAndVotee[] = new int[2];
            indexes_of_voterAndVotee = findVoterAndVotee(data);
            if(indexes_of_voterAndVotee[0] == UNDEFINED || indexes_of_voterAndVotee[1] == UNDEFINED){
                throw new Errors.UserNotJoined();
            }
            players.get(indexes_of_voterAndVotee[0]).performRoleInDay(players.get(indexes_of_voterAndVotee[1]));
            return true;
        }
        catch(Errors.UserNotJoined ex){
            out.println(ex.getMessage());
            return votingBegan;
        }
    }

    public void handleVoteAtNight(String data){
        int indexes_of_voterAndVotee[] = new int[2];
                    indexes_of_voterAndVotee = findVoterAndVotee(data);
                    try{
                        if(indexes_of_voterAndVotee[0] == UNDEFINED || indexes_of_voterAndVotee[1] == UNDEFINED){
                            throw new Errors.UserNotJoined();
                        }
                        else if(found(players.get(indexes_of_voterAndVotee[0]).getRole(), ROLES_ALLOWED_IN_NIGHT) == UNDEFINED){
                            throw new Errors.UserCantWakeUpDuringNight();
                        }
                        else if(players.get(indexes_of_voterAndVotee[0]).playerDead()){
                            throw new Errors.UserIsDead();
                        }
                        players.get(indexes_of_voterAndVotee[0]).performRoleInNight(players.get(indexes_of_voterAndVotee[1]));
                    }
                    catch(Errors.UserNotJoined ex){
                        out.println(ex.getMessage());
                    }
                    catch(Errors.UserIsDead ex){
                        out.print(ex.getMessage());
                    }
                    catch(Errors.UserCantWakeUpDuringNight ex){
                        out.println(ex.getMessage());
                    }
    }
    
    public boolean eventsInDay(){
        boolean matchResult = false;
        String data;
        boolean votingBegan = false;
        swappedOnce = false;
        while(true){
            data = sc.nextLine();
            try{
                if(data.length() >= SWAP_CHARACTER.length() &&
                data.substring(0, SWAP_CHARACTER.length()).equals(SWAP_CHARACTER)){
                    if(!votingBegan){
                        jumbled(data);
                    }
                    else{
                        throw new Errors.VotingInProgress();
                    }
                }
                else if(data.equals(END_VOTE)){
                    matchResult = playerKilledAtDay();
                    if(matchResult){
                        return true;
                    }
                    break;
                }
                else if(data.equals(GET_GAME_STATE)){
                    getGameState();
                }
                else if(data.equals(START_GAME)){
                    throw new Errors.GameHasAlreadyStarted();
                }
                else{
                    votingBegan = handleVoteInday(votingBegan, data);
                }
            }
            catch(Errors.VotingInProgress ex){
                out.println(ex.getMessage());
            }
            catch(Errors.GameHasAlreadyStarted ex){
                out.println(ex.getMessage());
            }
        }
        return false;
    }

    public void resetVoteInfo(){
        for(int counter = 0; counter < players.size(); counter++){
            players.get(counter).resetVote();
        }
    }

    public void freeSilencedPlayers(){
        for(int counter = 0; counter < players.size(); counter++){
            if(players.get(counter).playerSilenced()){
                players.get(counter).notSilencedAnymore();
            }
        }
    }

    public void playersWhoAreAwakeInNight(){
        for(int counter = 0; counter < players.size(); counter++){
            if(found(players.get(counter).getRole(), ROLES_ALLOWED_IN_NIGHT) != UNDEFINED && 
            !players.get(counter).playerDead()){
                out.println(players.get(counter).getName() + ": " + players.get(counter).getRole());
            }
        }
    }

    public int playerKilledAtNight(){
        int indexOfKilledPlayer = whoWasPlannedToBeKilled();
        out.println(MAFIA_TRIED_TO_KILL + players.get(indexOfKilledPlayer).getName());
        players.get(indexOfKilledPlayer).extraLifeOrDoctor();
        return indexOfKilledPlayer;
    }
    
    public int eventsAtNight(){
        while(true){
            String data;
            data = sc.nextLine();
            try{
                if(data.equals(START_GAME)){
                    throw new Errors.GameHasAlreadyStarted();
                }
                else if(data.equals(END_NIGHT)){
                    return playerKilledAtNight();
                }
                else if(data.equals(GET_GAME_STATE)){
                    getGameState();
                }
                else if(data.length() > SWAP_CHARACTER.length() &&
                        data.substring(0, SWAP_CHARACTER.length()).equals(SWAP_CHARACTER)){
                    
                        throw new Errors.CantSwapBeforeEndofNight();
                }
                else{
                    handleVoteAtNight(data);
                }
            }
            catch(Errors.GameHasAlreadyStarted ex){
                out.print(ex.getMessage());
            }
            catch(Errors.CantSwapBeforeEndofNight ex){
                out.print(ex.getMessage());
            }
        }
    }
    
    public void nightPlayerGoToSleep(){
        for(int counter = 0; counter < players.size(); counter++){
            players.get(counter).getReadyForNextDay();
        }
    }
    
    public boolean eventsInMatch(){
        int dayCounter = 1;
        boolean matchResult = false;
        int indexOfKilledPlayer = UNDEFINED;
        while(true){
            out.println(DAY + dayCounter);
            if(dayCounter > 1){
                lastNightEvents();
            }
            if(indexOfKilledPlayer != UNDEFINED){
                matchResult = checkWinner(indexOfKilledPlayer, false);
            }
            if(matchResult){
                return true;
            }
            matchResult = eventsInDay();
            if(matchResult){
                return true;
            }
            resetVoteInfo();
            freeSilencedPlayers();
            out.println(NIGHT + dayCounter);
            playersWhoAreAwakeInNight();
            indexOfKilledPlayer = eventsAtNight();
            dayCounter++;
            resetVoteInfo();
            nightPlayerGoToSleep();
        }
    }


    public ArrayList<String> seperateBySpaces(String str){
        ArrayList<String> divided_line = new ArrayList<String>();
        String line = new String();
        int counter = 0;
        while(counter <= str.length()){
            if((counter == str.length()) || (str.charAt(counter) == DELIMETER)){
                divided_line.add(line);
                line = NULL_STRING;
            }
            else{
                line += str.charAt(counter);
            }
            counter++;
        }
        return divided_line;
    }

    
    public void assignRole(String data){
        ArrayList<String> nameAndRole = new ArrayList<String>();
        nameAndRole = seperateBySpaces(data);
        Player player;
        String name = nameAndRole.get(0);
        String role = nameAndRole.get(1);
        Doctor doctor = new Doctor(name);
        Detective detective = new Detective(name);
        Bulletproof bulletproof = new Bulletproof(name);
        Silencer silencer = new Silencer(name);
        Godfather godfather = new Godfather(name);
        Joker joker = new Joker(name);
        Mafia mafia = new Mafia(name);
        Villager villager = new Villager(name);
         
        player = villager;
        if(role.equals(DETECTIVE)){
            player = detective;
        }
        else if(role.equals(BULLETPROOF)){
            player = bulletproof;
        }
        else if(role.equals(DOCTOR)){
            player = doctor;
        }
        else if(role.equals(SILENCER)){
            player = silencer;
        }    
        else if(role.equals(GODFATHER)){
            player = godfather;
        }
        else if(role.equals(JOKER)){
            player = joker;
        }
        else if(role.equals(MAFIA)){
            player = mafia;
        }

        player.setRole(nameAndRole.get(1));
        players.add(player);
        //out.println(players);
    }
    
    public void getGameState(){
        int arr[] = new int[2];
        arr = countMafiaAndVillagers();
        //out.print(arr);
        out.println(MAFIA + " = " + arr[0]);
        out.println(VILLAGER + " = " + arr[1]);
    }

    public void sortBasedOnNames(){
        int counter1, counter2;
        for (counter1 = 0; counter1 < players.size(); counter1++) {
            for (counter2 = 0; counter2 < players.size(); counter2++) {
                if (players.get(counter2).getName().equals(namesOfPlayers.get(counter1))){
                    swap(counter1, counter2);    
                }
            }
        }
    }

    public void startGame(){
        sortBasedOnNames();
        for(int i = 0; i < players.size(); i++)
            out.println(players.get(i).getName() + ": " + players.get(i).getRole());
        out.println(READY_FOR_MATCH);
    }
    
    public void matchFLow(){
        boolean gameEnded = false;
        String order;
        while(true){
            order = sc.nextLine();

            if(order.equals(NULL_STRING)) 
                break;
    
            else if(order.equals(START_GAME)){
                try{
                    if(!anyOneHasRole()){
                        throw new Errors.AllDontHaveRoles();
                    }
                    else if(namesOfPlayers.isEmpty()){
                        throw new Errors.NoGameCreated();
                    }
                    startGame();
                    gameEnded = eventsInMatch();
                    if(gameEnded){
                        break;
                    }
                }
                catch(Errors.NoGameCreated ex){
                    out.print(ex.getMessage());
                }
                catch(Errors.AllDontHaveRoles ex){
                    out.print(ex.getMessage());
                }
            }

            else if(order.equals(GET_GAME_STATE)){
                getGameState(); 
            }

            else if(order.substring(0, ASSIGN_ROLE.length()).equals(ASSIGN_ROLE)){
                try{
                    if(namesOfPlayers.isEmpty()){
                        throw new Errors.NoGameCreated();
                    }
                    assignRole(order.substring(ASSIGN_ROLE.length() + 1,order.length()));
                }
                catch(Errors.NoGameCreated ex){
                    out.print(ex.getMessage());
                }
            }

            else if(order.substring(0, CREATE_GAME.length()).equals(CREATE_GAME)){
                namesOfPlayers = seperateBySpaces(order.substring(CREATE_GAME.length() + 1, order.length()));
            }

        }
    }
}