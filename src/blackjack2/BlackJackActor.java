package blackjack2;

import java.util.ArrayList;
import java.util.List;

class BlackJackActor{
    protected List<Integer> cards = new ArrayList<>();
	private String name;
	private boolean visible = true;

	public List<Integer> getCards(){
		return cards;
	}

    public String getName(){
    	return name;
    }

    public boolean getVisible(){
    	return visible;
    }

    public BlackJackActor(String name){
    	this.name = name;
    	//ゲーム開始時にそれぞれのプレイヤーは
    	//２枚のカードを引く
        hits();
        hits();
    }

    public void setVisible(boolean visible){
    	this.visible = visible;
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
        Integer sum = 0;
        int count=0;
        for(Integer n : cards){
            if(n==1){
                count++;
            }else if(n>10){
                sum+=10;
            }else{
                sum+=n;
            }
        }

        while(count-->0){
            sum += (sum<=10)?11:1;  //sum += (sum+11<=21)?11:1;
        }

        return sum;
    }


}