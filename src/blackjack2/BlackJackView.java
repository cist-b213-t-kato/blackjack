package blackjack2;

import java.util.List;

public class BlackJackView implements Observer{

	private BlackJackModel model;

	public BlackJackView(BlackJackModel model){
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
        System.out.println(model.getMessage());

	}

    public static void show(BlackJackActor actor){
        List<Integer> cards = actor.getCards();
        String card;
        System.out.print(actor.getName() + ": ");
        for(int i=0; i<cards.size(); i++){
            card = BlackJackActor.toNominal(cards.get(i));
            if(i==cards.size()-1 && actor.getVisible()==false){
                System.out.print("*");
            }else{
                System.out.print(card+" ");
            }
        }
        if(actor.getVisible()){
            System.out.println("["+actor.getTotal()+"]");
        }else{
            System.out.println();
        }
    }


}
