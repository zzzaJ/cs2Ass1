
import java.util.concurrent.RecursiveAction;


public class MedFilt extends RecursiveAction{
    
        int lo;
        int hi;
        Double[] inArr;
        int fsize;
        static final int SEQUENTIAL_CUTOFF=500; //to determine num threads: length of input arr / Seq_Cutoff
        int bnds;
        
        Double[] outArr;
        /**
         * Medfilt object constructor, object used to apply median filter using threads.
         * @param inArr input array
         * @param outArr output array (filtered)
         * @param fsize filter size
         * @param lo lowest index in array
         * @param hi highest index in array
         */
        MedFilt (Double[] inArr, Double[] outArr, int fsize, int lo, int hi){
            
            this.inArr = inArr;
            this.lo = lo;
            this.hi = hi;
            this.fsize = fsize;
            this.outArr = outArr;
            
            bnds = fsize/2;
            
        }
    /**
     * Overridden method to handle creation of new threads, and the code they will execute
     */    
    protected void compute(){
        if((hi-lo) < SEQUENTIAL_CUTOFF ){ //if the array subset is less than the sequential cut off then start median filter operations within the thread
            
            Double[] wip = new Double[fsize];
            
            for(int i = lo; i < hi; i++){//loop through elements from lo -> hi in inArr adding to outArr, filtering them if possible
                
                if(i + 1 <= bnds || outArr.length - i <= bnds){
                
                outArr[i] = inArr[i];
                
            }
            else{
                
                for(int j = 0; j < fsize; j++){ //populate wip array with elements 
                
                    wip[j] = inArr[i-bnds+j];
                    
                }
                
                java.util.Arrays.sort(wip);//sorts the array
                outArr[i] = wip[bnds];//adds median to output array at the same index
            }
                
            }
            
            
        }
        else{
            
            MedFilt left = new MedFilt(inArr, outArr, fsize, lo, (hi+lo)/2);//create new thread, with bottom half of the array subset
            MedFilt right = new MedFilt(inArr, outArr, fsize, (hi+lo)/2, hi);//create new thread, with top half of the array subset
            
            
            left.fork();//realease the thread left
            right.compute();//run the right thread
            left.join();//a join on the left thread, ensuring simultanious completion time.
            
        }
        
    }
    

    
   
}
