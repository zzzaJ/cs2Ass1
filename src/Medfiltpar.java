
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;


public class Medfiltpar {
    
    static long startTime = 0;
    static final ForkJoinPool fjPool = new ForkJoinPool();
    
    
    
    private static void tick(){
        
            startTime = System.currentTimeMillis();
        
    }
    private static float tock(){
        
        return (System.currentTimeMillis() - startTime) / 1000.0f;
        
    }
    
    static Double[] medFilt(Double[] inArr, int fsize, int lo, int hi){
        
        return fjPool.invoke(new MedFilt(inArr, fsize, 0, inArr.length));
        
    }
    
    public static void main(String [] args){
        
        Double[] dub = {1.23, 0.323, 0.14, 2.44, 1.004, 3.2, 4.2, 1.009, 1.4, 1.5, 1.44};
        System.out.println(Arrays.toString(dub));
        System.out.println(Arrays.toString(medFilt(dub, 3, 0, dub.length)));
        
    }
    
}
