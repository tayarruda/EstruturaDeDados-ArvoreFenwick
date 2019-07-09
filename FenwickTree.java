package lab3;

public class FenwickTree {
    int value;
    int leftsize;
    FenwickTree left;
    FenwickTree right;
    
    FenwickTree (int value){
    	this.value=value;
    	this.leftsize=0;
    	this.left=null;
    	this.right=null; 
    } 
    
    FenwickTree(int leftsize, FenwickTree left, FenwickTree right){
    	this.value=left.value +right.value;
    	this.leftsize=leftsize;
    	this.left= left;
    	this.right=right;    
    }
    
    FenwickTree(int value, int leftsize, FenwickTree left, FenwickTree right){
    	this.value=value;
    	this.leftsize=leftsize;
    	this.left= left;
    	this.right=right;    
    }
    
    public String toString(){
        if(this.left==null && this.right==null){
            return "["+ this.value + "," + this.leftsize+"]";
        }
        else if(this.left==null){
            return "["+ this.value+","+ this.leftsize+","+ this.right.toString()+"]";
        }
        else if(this.right==null){
            return "["+ this.value +","+ this.leftsize +","+ this.left.toString()+"]";
            
        }
        else{
            return "["+ this.value+","+ this.leftsize+","+ this.right.toString()+","+ this.left.toString()+"]";
  
        }
    }
    
    static FenwickTree allZeros(int n){
        if (n==0) return null;
        if (n==1) return new FenwickTree(0);
        int m = n/2;
        return new FenwickTree(0, n-m, allZeros(n-m), allZeros(m));
    }
    
    public int size(){
        if(this.left==null && this.right==null){
            return 1;
        }
        return this.leftsize+ this.right.size(); 
    }
 
    public void increment(int i, int delta){
        this.value += delta;
        if(this.left==null && this.right==null) return ;
        else if (this.leftsize<i){
            this.left.increment(i,delta) ;
        }
        else{
            this.right.increment(i, delta);
        }
    }
    
    public int prefixSum(int upto){
        if(this.left==null)
        {
            return 0;
        }
        else if (this.leftsize< upto)
        {
          return this.value - this.right.value + this.right.prefixSum(upto-this.leftsize);  
        }
        else if (upto==this.leftsize)
        {
           return this.value; 
        }    
        else if (this.leftsize> upto)
        {
           return this.left.prefixSum(upto);  
        }
        else
        return 1;
    }
}