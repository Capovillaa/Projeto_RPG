package rpgtexto;

import java.util.Random;

public class Dado {

    static public int rolarDado(){
        Random random = new Random();

        return random.nextInt(20) + 1;
    }

    static public boolean caraOuCoroa(){
        Random random = new Random();
        return random.nextBoolean();
    }


}
