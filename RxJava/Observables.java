import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class Observables {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//create
		Observable<Integer> source = Observable.create(
				e -> {
					e.onNext(11);
					e.onNext(12);
					e.onNext(13);
					e.onComplete();
				}
		);
		
		source.subscribe(System.out::println);
		
		//just
		Observable<Integer> just = Observable.just(1, 2, 3, 4);
		
		just.subscribe(System.out::println);
		
		//fromIterable
		
		List<String> list = List.of("Audi", "Mercedes", "Opel");
		
		Observable<String> fromIterable = Observable.fromIterable(list);
		
		fromIterable.subscribe(System.out::println);
	}

}
