package blackjack1;


import java.util.ArrayList;
import java.util.List;

class BlackJackActor{
    protected List<Integer> cards = new ArrayList<>();

	private String name;
	private boolean visible = true;

    public String getName(){
    	return name;
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

    public void shows(){
        System.out.print(getName() + ": ");
        String card = "";
        for(int i=0; i<cards.size(); i++){
            card = cards.get(i).toString();
            if(card.equals("1")){
                card = "A";
            }else if(card.equals("11")){
                card = "J";
            }else if(card.equals("12")){
                card = "Q";
            }else if(card.equals("13")){
                card = "K";
            }
            if(i==cards.size()-1 && visible==false){
                System.out.print("*");
            }else{
                System.out.print(card+" ");
            }
        }
        if(visible==true){
            System.out.println("["+getTotal()+"]");
        }else{
            System.out.println();
        }
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