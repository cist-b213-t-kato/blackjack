package blackjack3;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	public final List<Observer> observers = new ArrayList<>();

	public void notifyObservers(){
		for(Observer observer : observers){
			//observerさん！俺（Subject）の状態を見てくれ！
			observer.update();
		}
	}

}
