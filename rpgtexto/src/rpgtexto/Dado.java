package rpgtexto;

import java.util.Random;

public class Dado {

    public int rolarDado(){
        Random random = new Random();

        return random.nextInt(20) + 1;
    }


}
