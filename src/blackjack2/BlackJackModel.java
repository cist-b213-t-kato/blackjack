package blackjack2;

import java.io.IOException;

public class BlackJackModel extends Subject{

    public enum GameState{
    	PLAYERTURN,
    	DEALERTURN,
    	END
    	;
    }
    private GameState gameState = GameState.PLAYERTURN;
    private String inputStr = "";
    private BlackJackActor player;
    private BlackJackActor dealer;
    private String message = "";

    public BlackJackActor getPlayer(){
    	return player;
    }
    public BlackJackActor getDealer(){
    	return dealer;
    }

    public void setMessage(String message){
    	this.message = message;
    }
    public String getMessage(){
    	return message;
    }

    public GameState getGameState(){
    	return gameState;
    }

    public BlackJackModel() throws IOException{
        player = new BlackJackActor("you");
        dealer = new BlackJackActor("dealer");
        dealer.setVisible(false);
    }

    public void setInputString(String input){
    	this.inputStr = input;
    }

    public void run() throws IOException{

    	message = "";

        switch(gameState){

        case PLAYERTURN:
            if(player.getTotal()<21){
            	if(inputStr.equals("h")){
                    player.hits();
                }else if(inputStr.equals("s")){
                	gameState = GameState.DEALERTURN;
                	break;
                }else if(!"".equals(inputStr)){
                    message = ("もういちど入力してください");
                }
            }
            if(player.getTotal()==21 && dealer.getTotal()==21){
                dealer.setVisible(true);
                message = ("ブラックジャック\n引き分け");
                gameState = GameState.END;
            }else if(player.getTotal()==21){
                dealer.setVisible(true);
                message = ("ブラックジャック\nあなたの勝ちです！");
                gameState = GameState.END;
            }else if(player.getTotal()>21){
                dealer.setVisible(true);
                message = ("バースト\nあなたの負けです！");
                gameState = GameState.END;
            }
        	notifyObservers();

        	break;

        case DEALERTURN:
            dealer.setVisible(true);
            if(dealer.getTotal()<=player.getTotal()){
            	message = ("ディーラー：ヒット");
                dealer.hits();
            }
            if(dealer.getTotal()>21){
            	message = ("ディーラーがバースト\nあなたの勝ちです！");
                gameState = GameState.END;
            }else if(dealer.getTotal()>player.getTotal()){
            	message = ("あなたの負けです！");
                gameState = GameState.END;
            }
        	notifyObservers();

        	break;
		default:
			break;
        }

    }


}
