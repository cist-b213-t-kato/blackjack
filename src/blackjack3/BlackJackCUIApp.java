package blackjack3;

public class BlackJackCUIApp {
    public static void main(String[] args) {

        BlackJackModel model = new BlackJackModel();
        new BlackJackVC(model);
    	model.start();

    }
}
