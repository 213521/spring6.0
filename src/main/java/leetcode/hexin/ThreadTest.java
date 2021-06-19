package leetcode.hexin;

import java.util.function.IntConsumer;

/**
 * @author ezrealhexin
 * @date 2021-06-19 20:24
 **/
public class ThreadTest {


}


/**
 * 我们提供了一个类：
 * public class Foo {
 * public void first() { print("first"); }
 * public void second() { print("second"); }
 * public void third() { print("third"); }
 * }
 * 三个不同的线程 A、B、C 将会共用一个 Foo 实例。
 * 一个将会调用 first() 方法
 * 一个将会调用 second() 方法
 * 还有一个将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 */
class Foo {
    public int c = 1;

    public Foo() {

    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {
        while (c != 1) {
            wait();
        }
        printFirst.run();
        c++;
        notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {

        while (c != 2) {
            wait();
        }
        printSecond.run();
        c++;
        notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        while (c != 3) {
            wait();
        }
        printThird.run();
    }
}


/**
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 * <p>
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * <p>
 * 请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
 * <p>
 * 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
 * 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
 * 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
 * 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
 */
class FizzBuzz {
    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    private int num = 1;

    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (num <= n) {
            if (num % 3 == 0 && num % 5 != 0) {
                printFizz.run();
                num += 1;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (num <= n) {
            if (num % 3 != 0 && num % 5 == 0) {
                printBuzz.run();
                num += 1;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (num <= n) {
            if (num % 3 == 0 && num % 5 == 0) {
                printFizzBuzz.run();
                num += 1;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (num <= n) {
            if (num % 3 != 0 && num % 5 != 0) {
                printNumber.accept(num);
                num += 1;
                notifyAll();
            } else {
                wait();
            }
        }
    }
}

