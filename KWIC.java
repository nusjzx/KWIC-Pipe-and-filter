import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KWIC {
	public static void main(String[] args) {
		Pipe<String> p1 = new Pipe<>();
        Pipe<String> p2 = new Pipe<>();
        Pipe<Object[]> p3 = new Pipe<>();
        
        Input input = new Input(p1, "input.txt");
        CircularShifter circularShifter = new CircularShifter(p1, p2);
        Alphabetizer alphabetizer = new Alphabetizer(p2, p3);
        Output output = new Output(p3, "output.txt");
		 
//        concurrent for input and circularShifter
        ExecutorService executorService = Executors.newCachedThreadPool();
    
        List<Callable<Object>> calls = new ArrayList<Callable<Object>>();
        calls.add(input);
        calls.add(circularShifter);
        
        try {
			executorService.invokeAll(calls);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        executorService.shutdown();

        alphabetizer.filter();
        output.filter();
	}
}
	