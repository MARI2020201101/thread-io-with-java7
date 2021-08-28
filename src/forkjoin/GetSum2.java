package forkjoin;

import java.util.concurrent.RecursiveTask;

import static java.lang.Thread.sleep;

public class GetSum2 extends RecursiveTask<Long> {
    private long from, to;
    public GetSum2(long from, long to){
        this.from=from;
        this.to=to;
    }
    @Override
    protected Long compute() {
        long gap = to-from;
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("-------------From "+from + " ~ to "+to );
        if(gap<=3){
            long tempsum = 0;
            for (long i = from; i <= to; i++){
                tempsum +=i;
            }
            log("From "+from + " ~ to "+to +" = tempSum =>"+ tempsum);
            return tempsum;
        }
        long middle = (from+to)/2;
        GetSum2 sumPre = new GetSum2(from, middle);
        log("From "+from + " ~ to "+to +" = sumPre =>"+ sumPre);
        sumPre.fork();
        GetSum2 sumPost = new GetSum2(middle+1, to);
        log("From "+from + " ~ to "+to +" = sumPost =>"+ sumPost);
        return sumPost.compute()+sumPre.join();
    }

    private void log(String message){
        String threadName = Thread.currentThread().getName();
        System.out.println("threadName [ "+threadName +" ] message : "+message);
    }
}
