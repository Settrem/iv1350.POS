package se.kth.iv1350.cashregister.util;

public class ConsoleLogger implements Logger{

    @Override
    public void log(String message) {
        System.out.println(message);
    }

}
