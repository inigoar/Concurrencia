package CC_01_Threads;

public class CC_01_Threads extends Thread{

    int n;

    private CC_01_Threads (int i){

        this.n = i;

    }
    
    public void run(){

    	int T = 2000;
    	 
        System.out.println("Hello from Thread " + n);

        try {

            Thread.sleep(T);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        System.out.println("Thread " + n + " has finished");

    }

    public static void main(String[] args) {

        int N = 15;

        CC_01_Threads Nthread[] = new CC_01_Threads[N];

        for (int i = 0; i < N; i++){

            Nthread[i] = new CC_01_Threads(i);
            Nthread[i].start();

        }

        for (int j = 0; j < N; j++){

            try {

                Nthread[j].join();

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

        System.out.println("All Threads have finished");

    }
    
}

