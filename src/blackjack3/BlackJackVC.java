package blackjack3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import blackjack3.BlackJackActor.ActorType;
import blackjack3.BlackJackModel.GameState;
import blackjack3.BlackJackModel.UserInput;

public class BlackJackVC implements Observer{

	private BlackJackModel model;

	public BlackJackVC(BlackJackModel model){
		this.model = model;
		model.addObserver(this);
	}

	@Override
	public void update() {
		BlackJackActor player = model.getPlayer();
		BlackJackActor dealer = model.getDealer();

        System.out.println();
        show(dealer);
        show(player);

        String message = "";
        switch(model.getGameState()){

        case PLAYERTURN:
            if(player.getTotal()<21){
                System.out.print("h:ヒット  s:スタンド  >> ");
                String inputStr = input();
                if(inputStr.equals("h")){
                	model.setUserInput(UserInput.HIT);
                }else if(inputStr.equals("s")){
                	model.setUserInput(UserInput.STAND);
                }else{
                    message = "もういちど入力してください";
                    model.setUserInput(UserInput.NONE);
                }
            }else if(player.getTotal()==21 && dealer.getTotal()==21){
                message = ("ブラックジャック\n引き分け");
            }else if(player.getTotal()==21){
                message = ("ブラックジャック\nあなたの勝ちです！");
            }else if(player.getTotal()>21){
                message = ("バースト\nあなたの負けです！");
            }

        	break;

        case DEALERTURN:
		default:
            if(dealer.getTotal()<=player.getTotal()){
            	message = ("ディーラー：ヒット");
            }else if(dealer.getTotal()>21){
            	message = ("ディーラーがバーストしました\nあなたの勝ちです！");
            }else if(dealer.getTotal()>player.getTotal()){
            	message = ("あなたの負けです！");
            }
        	break;
        	
        }

        System.out.println(message);

	}
	
	public String input(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = "";
		try {
			inputStr = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputStr;
	}

    public void show(BlackJackActor actor){
        List<Integer> cards = actor.getCards();
        System.out.print(actor.getName() + ": ");
        
        System.out.print(BlackJackActor.toNominal(cards.get(0)));
        for(int i=1; i<cards.size(); ++i){
            if(canShow(actor)){
                String card = BlackJackActor.toNominal(cards.get(i));
                System.out.print(" " + card);
            }else{
                System.out.print(" *");
            }
        }

        if(canShow(actor)){
            System.out.println(" ["+actor.getTotal()+"]");
        }else{
            System.out.println();
        }
        
    }
    
    public boolean canShow(BlackJackActor actor){
    	return !(model.getGameState().equals(GameState.PLAYERTURN)
        		&& actor.isType(ActorType.DEALER));
    }

}
