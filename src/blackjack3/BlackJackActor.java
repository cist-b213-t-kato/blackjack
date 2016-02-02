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
    
    public boolean isType(ActorType type){
    	return this.type.equals(type);
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
    }

    public static String toNominal(int cardNum){
        if(cardNum == 1){
            return "A";
        }else if(cardNum == 11){
            return "J";
        }else if(cardNum == 12){
            return "Q";
        }else if(cardNum == 13){
            return "K";
        }
        return String.valueOf(cardNum);
    }

    public void hits(){
        cards.add((int)(Math.random()*13)+1);
    }

    public Integer getTotal(){
        int sum = 0;
        int ace = 0;
        for(int n : cards){
            if(n==1){
            	ace += 1;
            }else if(n>10){
                sum += 10;
            }else{
                sum += n;
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