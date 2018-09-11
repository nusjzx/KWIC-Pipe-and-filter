import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Input extends Filter<Object, String> implements Callable{

	private Scanner scanner;
	
	public Input(Pipe<String> outPipe, String filepath) {
		super(null, outPipe);
		File file = new File(filepath);
		try {
			this.scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	void filter() {
		String line = scanner.nextLine();
		outPipe.push(line);
		
	}
	
	@Override
    public Object call() {
        while(scanner.hasNext()){
            filter();
        }
        return null;
    }
}
