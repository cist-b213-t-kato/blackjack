package blackjack3;

import blackjack3.BlackJackActor.ActorType;

public class BlackJackModel extends Subject {

	public enum GameState {
		PLAYERTURN, DEALERTURN, END,
	}

	public enum UserInput {
		NONE, HIT, STAND,
	}

	private GameState gameState = GameState.PLAYERTURN;
	private UserInput userInput = UserInput.NONE;
	private BlackJackActor player;
	private BlackJackActor dealer;

	public BlackJackActor getPlayer() {
		return player;
	}

	public BlackJackActor getDealer() {
		return dealer;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setUserInput(UserInput input) {
		userInput = input;
	}

	public void init() {
		player = new BlackJackActor(ActorType.YOU, "you");
		dealer = new BlackJackActor(ActorType.DEALER, "dealer");
		// それぞれのプレイヤーはゲーム開始時に２枚のカードを引く
		player.hits();
		player.hits();
		dealer.hits();
		dealer.hits();
	}

	public void start() {
		init();
		while (!gameState.equals(GameState.END)) {
			run();
		}
	}

	public void run() {

		notifyObservers();

		switch (gameState) {

		case PLAYERTURN:
			if (player.getTotal() >= 21) {
				gameState = GameState.END;
			} else if (userInput == UserInput.HIT) {
				player.hits();
			} else if (userInput == UserInput.STAND) {
				gameState = GameState.DEALERTURN;
			}
			break;

		case DEALERTURN:
		default:
			if (dealer.getTotal() <= player.getTotal()) {
				dealer.hits();
			} else if (dealer.getTotal() > player.getTotal()) {
				gameState = GameState.END;
			}
			break;

		}

	}

}
