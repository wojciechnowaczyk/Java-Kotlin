import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

public class MultiCasting {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ConnectableObservable<Long> source = Observable.interval(1, TimeUnit.SECONDS).publish();
		
		source.connect();
		
		source.subscribe(System.out::println);
		
		Thread.sleep(10000);
		
		source.subscribe(System.out::println);
		
		Thread.sleep(10000);
	}

}
