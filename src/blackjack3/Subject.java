package blackjack3;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	private List<Observer> observers = new ArrayList<>();

	public void addObserver(Observer observer){
		observers.add(observer);
	}
	
	public void notifyObservers(){
		for(Observer observer : observers){
			//observerさん！俺（Subject）の状態を見てくれ！
			observer.update();
		}
	}

}
