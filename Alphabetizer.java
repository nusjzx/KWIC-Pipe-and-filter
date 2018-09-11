import java.util.Arrays;

public class Alphabetizer extends Filter<String, Object[]> {

	public Alphabetizer(Pipe<String> inPipe, Pipe<Object[]> outPipe) {
		super(inPipe, outPipe);
	}
	
	 @Override
    public void filter() {
        Object[] lines = inPipe.toArray();
        Arrays.sort(lines);
        outPipe.push(lines);
    }
}
