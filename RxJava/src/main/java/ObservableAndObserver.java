import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.internal.operators.observable.ObservableCreate;

public class ObservableAndObserver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Observable<Integer> source = new ObservableCreate<Integer>(new ObservableOnSubscribe<Integer>() {

			@Override
			public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
				// TODO Auto-generated method stub
				try {
					emitter.onNext(10);
					emitter.onNext(11);
					emitter.onComplete();
				}
				catch(Throwable t) {
					emitter.onError(t);
				}

			}
			
		});
		
		Observer<Integer> observer = new Observer<Integer>(){

			@Override
			public void onSubscribe(@NonNull Disposable d) {
				// TODO Auto-generated method stub
				System.out.print("OnSubscribe");
				
			}

			@Override
			public void onNext(@NonNull Integer t) {
				// TODO Auto-generated method stub
				System.out.print("On Next: " + t);
				
			}

			@Override
			public void onError(@NonNull Throwable e) {
				// TODO Auto-generated method stub
				e.printStackTrace();
				
			}

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				System.out.print("Completed");
				
			}
			
		};
		
		source.subscribe(observer);

	}

}
