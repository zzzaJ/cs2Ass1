
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Medfiltseq {
    
    static long startTime = 0;
    private static void tick(){
        
            startTime = System.currentTimeMillis();
        
    }
    private static float tock(){
        
        return (System.currentTimeMillis() - startTime) / 1000.0f;
        
    }
    
    public static void main(String [] args){
    
        Scanner scanf = new Scanner(System.in);
    
        String line = scanf.nextLine();
        String infname = line.substring(0, line.indexOf(" "));
        int fsize = Integer.parseInt(line.substring(line.indexOf(" "), line.lastIndexOf(" ")));
        
        if (fsize >= 0 || fsize%2 == 0){
            
            System.out.println("filter size not >= 3 or not odd"); 
            
        }
        
        String outfname = line.substring(line.lastIndexOf(" "));
        int linenum;
        double num; 
    
        try {
            scanf = new Scanner(new File(infname));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Medfiltseq.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Double[] input = new Double[Integer.parseInt(scanf.nextLine().trim())];
        
        while (scanf.hasNextLine()){
            
            line = scanf.nextLine();
            linenum = Integer.parseInt(line.substring(0, line.indexOf(" ")));
            num = Double.parseDouble(line.substring(line.indexOf(" ")));
            input[linenum-1] = num;
        }
        
        Double[] output;
        
        tick();
        output = medFilt(input, fsize);
        float time = tock();
        System.out.println("run took " + time + " seconds");

    }
    
    public static Double[] medFilt(Double[] inputArr, int fsize){
        
        Double[] dub = new Double[inputArr.length];
        Double[] wip = new Double[fsize];
        int bnds;
        
        
        bnds = fsize/2; // 3/2 = 1 , 4/2 = 2 , 5/2 = 2 etc. 
        
        for(int i = 0; i < dub.length; i++){
            
            if(i + 1 <= bnds || dub.length - i <= bnds){
                
                dub[i] = inputArr[i];
                
            }
            else{
                
                for(int j = 0; j < fsize; j++){ //populate wip array with elements 
                
                    wip[j] = inputArr[i-bnds+j];//seems correct
                    
                }
                
                java.util.Arrays.sort(wip);
                dub[i] = wip[bnds];
            }
            
        }

        return dub;
    }
    
}
