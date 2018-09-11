import java.util.concurrent.Callable;

public class CircularShifter extends Filter<String, String> implements Callable<Object> {

	public CircularShifter(Pipe<String> inPipe, Pipe<String> outPipe) {
		super(inPipe, outPipe);
	}

	@Override
	public void filter() {
		if (!inPipe.isEmpty()) {
			String line = inPipe.pull();

			String[] words = line.split("\\s+");
			if (words.length > 1) {
				for (int i = 0; i < words.length; i++) {
					String shiftedSentence = String.join(" ", words);
					outPipe.push(shiftedSentence);
					shift(words);
				}
			} else {
				outPipe.push(line);
			}
		}
	}

	private void shift(String[] s) {
		String temp = s[0];
		for (int i = 0; i < s.length - 1; i++) {
			s[i] = s[i + 1];
		}
		s[s.length - 1] = temp;
	}

	@Override
	public Object call() {
		while (inPipe.peek() == null || !inPipe.peek().equals("`")) {
			filter();
		}
		return null;
	}
}
