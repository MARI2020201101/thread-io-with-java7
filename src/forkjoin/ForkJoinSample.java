package forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinSample {
    private static final ForkJoinPool mainPool = new ForkJoinPool();
    public static void main(String[] args) {
        ForkJoinSample sample = new ForkJoinSample();
       // sample.calculate();
        sample.calculate2();
    }

    private void calculate() {
        long from = 0;
        long to = 10;

        GetSum getSum = new GetSum(from, to);
        Long result = mainPool.invoke(getSum); //일해라! ( fork-join 메서드 외부에서 호출)
        System.out.println("Fork join : total sum => " + from + " ~ " + to + " = result : "+result);
    }
    private void calculate2() {
        long from = 0;
        long to = 10;

        GetSum2 getSum = new GetSum2(from, to);
        long result = mainPool.invoke(getSum);
        System.out.println("Fork join : total sum => " + from + " ~ " + to + " = result : "+result);
    }
}
