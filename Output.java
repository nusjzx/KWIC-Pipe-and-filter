import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Output extends Filter<Object[], Object>{

	private BufferedWriter writer;
	public Output(Pipe<Object[]> inPipe, String filepath) {
		super(inPipe, null);
		try {
			writer = new BufferedWriter(new FileWriter(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void filter() {
		Object[] lines = inPipe.pull();
		try {
			for(Object line : lines) {
					writer.write((String)line);
					writer.newLine();
				
			}
			writer.close();  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
