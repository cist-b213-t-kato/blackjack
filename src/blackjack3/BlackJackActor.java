package blackjack3;

import java.util.ArrayList;
import java.util.List;

class BlackJackActor{
    protected List<Integer> cards = new ArrayList<>();
	private String name;
	private ActorType type;

	public enum ActorType{
		YOU, DEALER
	}

	public List<Integer> getCards(){
		return cards;
	}

    public String getName(){
    	return name;
    }

    public BlackJackActor(ActorType type, String name){
    	this.name = name;
    	this.type = type;
    	//それぞれのプレイヤーはゲーム開始時に２枚のカードを引く
        hits();
        hits();
    }

    public ActorType getActorType(){
    	return type;
    }

    public static String toNominal(Integer cardNum){
        String card = cardNum.toString();
        if(card.equals("1")){
            card = "A";
        }else if(card.equals("11")){
            card = "J";
        }else if(card.equals("12")){
            card = "Q";
        }else if(card.equals("13")){
            card = "K";
        }
        return card;
    }

    public void hits(){
        cards.add((int)(Math.random()*13)+1);
    }

    public Integer getTotal(){
        int sum = 0;
        int ace = 0;
        for(int n : cards){
            if(n==1){
                ace++;
            }else if(n>10){
                sum+=10;
            }else{
                sum+=n;
            }
        }

        while(ace>0){
            if(sum + 11 * ace > 21){
            	sum += 1;
            }else{
            	sum += 11;
            }
            --ace;
        }

        return sum;
    }


}