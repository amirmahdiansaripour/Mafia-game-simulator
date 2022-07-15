// package Player;
// import Globals.Globals;
// import Errors.*;
import static java.lang.System.*;

class VoteInfoPerPlayer implements Globals{
    public Player votedTo;
    public int numOfVotesToMe;
    public VoteInfoPerPlayer(){
        votedTo = null;
        numOfVotesToMe = 0;
    }
}

public abstract class Player implements Globals{
    protected boolean removedFromMatch;
	protected int extraLife;
	protected boolean silenced;
	protected boolean dead;
	protected boolean saved;
	protected String name;
	protected String role;
	protected String groupName;
	protected VoteInfoPerPlayer voteInfo;

    public Player(String name_){
        name = name_;
        dead = false;
        silenced = false;
        voteInfo = new VoteInfoPerPlayer();
        saved = false;
        removedFromMatch = false;
        extraLife = 0;
    }

    public void printPlayerData(){
        out.println(name + " : " + role);
    }


    public Player(Player P){
        name = P.name;
        dead = P.dead;
        voteInfo = new VoteInfoPerPlayer();
        voteInfo = P.voteInfo;
        silenced = P.silenced;
        saved = P.saved;
        removedFromMatch = P.removedFromMatch;
        extraLife = P.extraLife;
    }

    public abstract void performRoleInNight(Player actedOn);
    public abstract void getReadyForNextDay();

    public void changeData(String name_){
        name = name_;
    }

    public String getName(){
        return name;
    }

    public void saveLife(){
        saved = true;
    }

    public void setRole(String role_){
        role = role_;
    }

    public String getRole(){
        return role;
    }

    public void silenceSomeone(){
        silenced = true;
    }

    public String getGroup(){
        return groupName;
    }

    public boolean playerRemoved(){
        return removedFromMatch;
    }

    public boolean playerDead(){
        return dead;
    }

    public boolean playerSilenced(){
        return silenced;
    }

    public void playerIsRemoved(){
        removedFromMatch = true;
    }

    public void incVotes(){
        voteInfo.numOfVotesToMe++;
    }

    public void decrementVotes(){
        voteInfo.numOfVotesToMe--;
    }

    public int getNumOfVotes(){
        return voteInfo.numOfVotesToMe;
    }

    public void playerIsKilled(){
        dead = true;
    }

    public boolean votesAtleastOnce(){
        if(voteInfo.votedTo == null)
            return false;
        else
            return true;
    }

    public Player getMyPrevVote(){
        return voteInfo.votedTo;
    }

    public void performRoleInDay(Player actedOn){
        vote(actedOn);
    }

    public void extraLifeOrDoctor(){
        if(saved){
            saved = false;
            return;
        }
        else if(extraLife > 0){
            extraLife = 0;
            return;
        }
        dead = true;
    }

    public void notSilencedAnymore(){
        silenced = false;
    }

    public void resetVote(){
        voteInfo.numOfVotesToMe = 0;
        voteInfo.votedTo = null;
    }

    public void vote(Player votedTo){
        try{
            if(playerSilenced()){
                throw new Errors.VoterIsSilenced();
            }
            if(votedTo.playerDead()){
                throw new Errors.VoteeAlreadyDead();
            }
            if(votesAtleastOnce()){
                Player prev_candid = getMyPrevVote();
                prev_candid.decrementVotes();
            }
            votedTo.incVotes();
            voteInfo.votedTo = votedTo;
        }
        catch(Errors.VoteeAlreadyDead ex){
            out.println(ex.getMessage());
        }
        catch(Errors.VoterIsSilenced ex){
            out.println(ex.getMessage());
        }
    }
}
