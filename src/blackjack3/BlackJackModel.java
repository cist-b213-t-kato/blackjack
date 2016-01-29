package blackjack3;


public class BlackJackModel extends Subject{

    public enum GameState{
    	PLAYERTURN, DEALERTURN, END;
    }
    private GameState gameState = GameState.PLAYERTURN;
    private BlackJackActor player;
    private BlackJackActor dealer;

    public BlackJackActor getPlayer(){
    	return player;
    }

    public BlackJackActor getDealer(){
    	return dealer;
    }

    public GameState getGameState(){
    	return gameState;
    }

    public void setGameState(GameState gameState){
    	this.gameState = gameState;
    }

    public BlackJackModel() {
        player = new BlackJackActor(BlackJackActor.ActorType.YOU, "you");
        dealer = new BlackJackActor(BlackJackActor.ActorType.DEALER, "dealer");
    }

	public void start() {
        while(gameState != GameState.END){
        	run();
        }
	}

    public void run(){

    	notifyObservers();

        switch(gameState){

        case PLAYERTURN:
            if(player.getTotal()<21){
                player.hits();
            }else{
                gameState = GameState.END;
            }
        	break;

        case DEALERTURN:
    	default:
            if(dealer.getTotal()<=player.getTotal()){
                dealer.hits();
            }else{
                gameState = GameState.END;
            }
        	break;

        }

    }


}
