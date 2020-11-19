package CC_02_Carrera;

public class CC_02_Carrera{
	
	static int n = 0;
	
	static class incrementador extends Thread {

		public void run(){ 
			
			n++;
			
			}
		}
	
	static class decrementador extends Thread {

		public void run(){
			
			n--;
			
			}
		}

	public static void main(String[] args) throws InterruptedException{
		
		int m = 20;
		
		Thread incrementador[] = new Thread[m];
		Thread decrementador[] = new Thread[m];
		
		for (int i = 0; i < m; i++) {
			
            incrementador[i] = new incrementador();
            incrementador[i].start();
            decrementador[i] = new decrementador();
            decrementador[i].start();
        }
		
	
		for (int j = 0; j < m; j++) {
            
            try {  
            	
                incrementador[j].join(); 
                decrementador[j].join(); 
                
            } 
            catch (InterruptedException e) 
            {                
                e.printStackTrace();
            }
            
        }
		
        System.out.println(" n vale : " +n);

	}

}
