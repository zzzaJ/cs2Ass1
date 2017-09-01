
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;
import java.util.logging.Logger;


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
        Double[] outArr = new Double[inArr.length];
        fjPool.invoke(new MedFilt(inArr, outArr, fsize, 0, inArr.length));
        return outArr;
        
    }
    
    public static void main(String [] args){
        
        System.gc();
        Scanner scanf = new Scanner(System.in);
    
        String line = scanf.nextLine();
        String infname = line.substring(0, line.indexOf(" "));
        int fsize = Integer.parseInt(line.substring(line.indexOf(" ")+1, line.lastIndexOf(" ")));
        
        if (fsize < 3 || fsize%2 == 0){
            
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
        
        int numlines = Integer.parseInt(scanf.nextLine().trim());
        
        Double[] input = new Double[numlines];
        
        while (scanf.hasNextLine()){
            
            line = scanf.nextLine();
            linenum = Integer.parseInt(line.substring(0, line.indexOf(" ")));
            num = Double.parseDouble(line.substring(line.indexOf(" ")));
            input[linenum-1] = num;
        }
        
        
        Double[] output;
        
        
        //timing operations
        tick();
        output = medFilt(input, 3, 0, input.length);
        float time = tock();
        System.out.println("run took " + time + " seconds");
        
        PrintWriter writer = null;
        
        try{
            
            writer = new PrintWriter(outfname, "utf-8");
            writer.println(numlines);
            for(int i = 0; i < output.length; i++){
                
                writer.println((i+1) + " " + output[i]);
                
            }
            
            
        } catch(IOException ex){
            
            Logger.getLogger(Medfiltseq.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        finally{
            
            writer.close();
            
        }
        

    }
        
    }
    

