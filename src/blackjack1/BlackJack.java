package blackjack1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BlackJack {

    public static void main(String[] args) throws IOException{
        new BlackJack();
    }

    public BlackJack() throws IOException{
        BlackJackActor you = new BlackJackActor("you");
        BlackJackActor dealer = new BlackJackActor("dealer");
        dealer.setVisible(false);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = "";

        while(true){
            if(you.getTotal()<21){
                System.out.println();
                dealer.shows();
                you.shows();
                System.out.print("h:ヒット  s:スタンド  >> ");
                inputStr = br.readLine();
            }else if(you.getTotal()==21 && dealer.getTotal()==21){
                System.out.println();
                dealer.shows();
                you.shows();
                System.out.println("ブラックジャック");
                System.out.println("引き分け");
                return;
            }else if(you.getTotal()==21){
                System.out.println();
                dealer.shows();
                you.shows();
                System.out.println("ブラックジャック");
                System.out.println("あなたの勝ちです！");
                return;
            }else if(you.getTotal()>21){
                System.out.println();
                dealer.shows();
                you.shows();
                System.out.println("バースト");
                System.out.println("あなたの負けです！");
                return;
            }

            if(inputStr.equals("h")){
                you.hits();
            }else if(inputStr.equals("s")){
                break;
            }else{
                System.out.println("不正な値");
                continue;
            }
        }

        while(true){
            System.out.println();
            dealer.shows();
            you.shows();

            if(dealer.getTotal()>21){
                System.out.println("あなたの勝ちです！");
                return;
            }else if(dealer.getTotal()>you.getTotal()){
                System.out.println("あなたの負けです！");
                return;
            }else if(dealer.getTotal()<you.getTotal()){
                System.out.println("ディーラー：ヒット");
                dealer.hits();
            }else if(dealer.getTotal().equals(you.getTotal())){
                System.out.println("引き分け");
                return;
            }


        }

    }


}
