package forkjoin;

import java.util.concurrent.RecursiveTask;

import static java.lang.Thread.sleep;

public class GetSum extends RecursiveTask<Long> {
    private long from, to;
    public GetSum(long from, long to){
        this.from=from;
        this.to=to;
    }
    @Override
    protected Long compute() {
        long gap = to-from;

        if(gap<=3){
            long tempsum = 0;
            for (long i = from; i <= to; i++){
                tempsum +=i;
            }
            return tempsum;
        }
        long middle = (from+to)/2;
        GetSum sumPre = new GetSum(from, middle);
        sumPre.fork(); //쪼개고
        GetSum sumPost = new GetSum(middle+1, to);
        return sumPost.compute()+sumPre.join(); //합친다
    }
}
