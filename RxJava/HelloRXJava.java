import io.reactivex.rxjava3.core.Observable;

public class HelloRXJava {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Observable<String> source = Observable.create(e -> {
			
			e.onNext("Hello");
			e.onNext("RxJava");
		});
		
		source.subscribe(e -> System.out.print("Observer 1: " + e));
		source.subscribe(e -> System.out.print("Observer 2: " + e));
	}

}
