package blackjack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import blackjack2.BlackJackModel.GameState;

public class BlackJackController {

    public static void main(String[] args) throws IOException{

        BlackJackModel model = new BlackJackModel();
        new BlackJackView(model);
        do{
        	model.run();
        	if(GameState.PLAYERTURN.equals(model.getGameState())
        			&& model.getPlayer().getTotal()<21){
                System.out.print("h:ヒット  s:スタンド  >> ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String inputStr = br.readLine();
                model.setInputString(inputStr);
        	}
        }while(!GameState.END.equals(model.getGameState()));
    }
}
