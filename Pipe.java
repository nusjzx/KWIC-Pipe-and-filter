import java.util.concurrent.ConcurrentLinkedQueue;

public class Pipe<T> {
	protected ConcurrentLinkedQueue<T> buffer;
	
	public Pipe() {
		buffer = new ConcurrentLinkedQueue<>();
	}
	
	protected synchronized Boolean isEmpty(){
        return buffer.isEmpty();
    }
	
	protected synchronized void push(T input){
        buffer.offer(input);
    }

    protected synchronized T pull(){
        return buffer.poll();
    }

    protected synchronized T peek(){
        return buffer.peek();
    }
    
    protected synchronized Object[] toArray(){
        return buffer.toArray();
    }
}
