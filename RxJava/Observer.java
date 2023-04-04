import io.reactivex.rxjava3.core.Observable;

public class Observer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Observable<String> source = Observable.just("Audi", "Mercedes", "Opel", "Nissan");
		
		source.subscribe( i -> System.out.print(i), Throwable :: printStackTrace, () -> System.out.println("Complete"));
		
		System.out.println();
		
		//first lambda is onNext, second is onError and third is onComplete
		
		
	}

}
