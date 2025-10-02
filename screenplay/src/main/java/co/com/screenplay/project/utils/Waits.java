package co.com.screenplay.project.utils;

public class Waits {

    private Waits (){}
    public static void waiting (int waiting){
        try{
            Thread.sleep(waiting * 1000L);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
