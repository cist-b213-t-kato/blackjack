package blackjack3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import blackjack3.BlackJackModel.GameState;

public class BlackJackVC implements Observer{

	private BlackJackModel model;

	public BlackJackVC(BlackJackModel model){
		this.model = model;
		model.observers.add(this);
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
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String inputStr = "";
				try {
					inputStr = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(!inputStr.equals("h") && !inputStr.equals("s")){
                    message = "もういちど入力してください";
                }else{

                	if(inputStr.equals("h")){
                    }else if(inputStr.equals("s")){
                    	model.setGameState(GameState.DEALERTURN);
                    }
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

    public void show(BlackJackActor actor){
        List<Integer> cards = actor.getCards();
        System.out.print(actor.getName() + ": ");
        for(int i=0; i<cards.size(); i++){
        	if(i > 0){
        		System.out.print(" ");
        	}
            String card = BlackJackActor.toNominal(cards.get(i));
            if(i==cards.size()-1 && model.getGameState()==GameState.PLAYERTURN
            		&& actor.getActorType().equals(BlackJackActor.ActorType.DEALER)){
                System.out.print("*");
            }else{
                System.out.print(card);
            }
        }
        if(model.getGameState()==GameState.PLAYERTURN
        		&& actor.getActorType().equals(BlackJackActor.ActorType.DEALER)){
            System.out.println();
        }else{
            System.out.println(" ["+actor.getTotal()+"]");
        }
    }

}
