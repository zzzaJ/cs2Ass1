
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;


public class MedFilt extends RecursiveAction{
    
        int lo;
        int hi;
        Double[] inArr;
        int fsize;
        static final int SEQUENTIAL_CUTOFF=3;
        int bnds;
        
        Double[] outArr;
        
        MedFilt (Double[] inArr, Double[] outArr, int fsize, int lo, int hi){
            
            this.inArr = inArr;
            this.lo = lo;
            this.hi = hi;
            this.fsize = fsize;
            this.outArr = outArr;
            
            bnds = fsize/2;
            
        }

    protected void compute(){
        if((hi-lo) < SEQUENTIAL_CUTOFF ){
            
            Double[] wip = new Double[fsize];
            
            for(int i = lo; i < hi; i++){//loop through elements from lo -> hi in inArr adding to outArr
                
                if(i + 1 <= bnds || outArr.length - i <= bnds){
                
                outArr[i] = inArr[i];
                
            }
            else{
                
                for(int j = 0; j < fsize; j++){ //populate wip array with elements 
                
                    wip[j] = inArr[i-bnds+j];//seems correct
                    
                }
                
                java.util.Arrays.sort(wip);
                outArr[i] = wip[bnds];
            }
                
            }
            
            
        }
        else{
            
            MedFilt left = new MedFilt(inArr, outArr, fsize, lo, (hi+lo)/2);
            MedFilt right = new MedFilt(inArr, outArr, fsize, (hi+lo)/2, hi);
            
            
            left.fork();
            right.compute();
            left.join();
            
        }
        
    }
    
    protected Double[] getOutArr(){
        return outArr;
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
