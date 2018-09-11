abstract public class Filter<S, T>{
	protected Pipe<S> inPipe;
    protected Pipe<T> outPipe;
    
    public Filter(Pipe<S> inPipe, Pipe<T> outPipe) {
        this.inPipe = inPipe;
        this.outPipe = outPipe;
    }
    
    abstract void filter();
}
