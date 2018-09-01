//Simulation.java
//Patrick Stumps && Jesus Munoz
//pstumps@ucsc.edu && jmunoz10@ucsc.edu
//CS12M
//Simulates processors for jobs

import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

public class Simulation {
	
	public static Job getJob(Scanner in) {
		String[] s = in.nextLine().split(" ");
		int a = Integer.parseInt(s[0]);
		int d = Integer.parseInt(s[1]);
		return new Job(a, d);
	}
	
	public static void main (String[] args) throws IOException{
		
		Queue trcStorage = new Queue();
		
		if(args.length<1||args.length>1) {
			System.out.print("Usage: Simulation inputfile");
			System.exit(1);
		}
		
		Scanner sc = new Scanner(new File(args[0]));
		PrintWriter rpt = new PrintWriter(new FileWriter(args[0]+".rpt"));
		PrintWriter trc = new PrintWriter(new FileWriter(args[0]+".trc"));
		
		String numJobs = sc.nextLine();
		int m = Integer.parseInt(numJobs);
		
		while(sc.hasNext()) {
			Job job = getJob(sc);
			//Storage.enqueue(job);
			trcStorage.enqueue(job);
		}
		
		trc.println("Trace file: "+args[0]+".trc");
		trc.println(m+ " Jobs:");
		trc.println(trcStorage.toString());
		trc.println();
		
		rpt.println("Report file: "+args[0]+".rpt");
		rpt.println(m+ " Jobs:");
		rpt.println(trcStorage.toString());
		rpt.println();
		rpt.println("***********************************************************");
		
		for(int n = 1; n<m; n++) { // main for loop
			int time = 0;
			int totWait=0;
			int maxWait=0;
			double avgWait=0.0;
			
			//initialize processor Queue
			Queue[] proQueue = new Queue[n+1];
			for(int i=0; i<=n; i++) {
				proQueue[i] = new Queue();
			}
			//Copy jobs
			for(int i=0; i<m; i++) {
				Job jb = (Job)trcStorage.dequeue();
				jb.resetFinishTime();
				proQueue[0].enqueue(jb);
				trcStorage.enqueue(jb);
			}
			
			trc.println("*****************************");
			if (n==1) {
				trc.println(n+" processor:");
			}
			else {
				trc.println(n+" processors:");
			}
			trc.println("*****************************");
			trc.println("time=" +time);
			trc.println("0: "+trcStorage.toString());
			for(int i=1; i<=n; i++) {
				trc.println(i+": "+proQueue[i]);
			}
			//proQueue[0] = Storage;
			//begin main while loop
			Queue Storage = new Queue();
			Storage = proQueue[0];
			while(Storage.length() !=m || ((Job)Storage.peek()).getFinish() == -1) {
				int[] q = new int[proQueue.length];
				
				//if (time == 0) {
				//	trc.println("time=0");
				//	for(int i=0; i<=n; i++) {
				//		trc.println(i+": "+proQueue[i]);
				//	}
				//	trc.println();
				//}
				
				if(time == 0 && !Storage.isEmpty()) {
					time = ((Job)Storage.peek()).getArrival();
				}
				else if(!Storage.isEmpty()) {
					Job comp = (Job)Storage.peek();
					if(comp.getFinish() == -1) { //loop exit control
						q[0]=comp.getArrival();
					}
				}
				
				for(int i=1; i<proQueue.length; i++) { //initialize array according to finish times
					if(!proQueue[i].isEmpty() && time !=0) 
						q[i] = ((Job)proQueue[i].peek()).getFinish();		
				}
				
				for(int i = q.length-1; i>0; i--) { // sort finish times by amount of time
					for(int j = 1; j<=i; j++) {
						if(q[j]<q[j-1]) {
							int t = q[j];
							q[j] = q[j-1];
							q[j-1] = t;
						}
					}
				}
				
				for(int i=0; i<q.length; i++) { //ensures times will match jobs
					if(q[i] !=0) {
						time = q[i];
						break;
					}
				}
				
				for(int i=1; i<=n; i++) {
					if(!proQueue[i].isEmpty() && ((Job) proQueue[i].peek()).getFinish() == time){ //if processor queue is not empty and job is finished, put job back in storage
						Storage.enqueue(proQueue[i].dequeue());
						if(proQueue[i].length()>0 && ((Job)proQueue[i].peek()).getFinish() == -1) { //changes the * to finish time
							((Job)proQueue[i].peek()).computeFinishTime(time);
						}
					}
				}
				
				if(proQueue.length-1 == 1 && !Storage.isEmpty() && ((Job)Storage.peek()).getArrival() == time) {
					proQueue[1].enqueue(Storage.dequeue()); //take job from storage and give out to processors
				}
				else {
					if(!Storage.isEmpty() && ((Job)Storage.peek()).getArrival() == time) {
						int[] r = new int[proQueue.length-1]; //This array is for determining which processors get which jobs
						for(int i=0; i<r.length; i++) {
							r[i] = proQueue[i+1].length();
						}
						int idx = 0;
						int s = r[0];
						for(int i=1; i<r.length; i++) {
							if(r[i]<s) {
								s = r[i];
								idx = i;
							}
						}proQueue[idx+1].enqueue(Storage.dequeue()); // so if there is a processor open, it will leave storage and join the appropriate processor
					}
				}
				
				for(int i=1; i<proQueue.length; i++) {
					if(!proQueue[i].isEmpty() && ((Job)proQueue[i].peek()).getFinish() == -1) {
						((Job)proQueue[i].peek()).computeFinishTime(time);
					}
				}
				
				//trc.println();
				//trc.println("0: :"+Storage.toString());
				//if(((Job)proQueue[1].peek()).getArrival() != ((Job)Storage.peek()).getArrival()){ // I was sure that this would fix the glitch with ex3, but its not.
				trc.println("time="+time);
				
				for(int i=0; i<=n; i++) {
					trc.println(i+": "+proQueue[i]);
				}
				trc.println();
				//}
			}
			
			int[] times = new int[Storage.length()];
			for(int i=0; i<times.length; i++) {
				times[i] = ((Job)Storage.peek()).getWaitTime(); // total wait = addition of all wait times
				totWait += times[i];
				Storage.enqueue(Storage.dequeue());
			}
			//DecimalFormat d = new DecimalFormat("#.##");
			maxWait = times[times.length-1];
			avgWait = ((double)totWait/m);
			if(n==1) {
				rpt.print(n+" processor: totalWait="+totWait+", maxWait="+maxWait+ ", averageWait=");
				rpt.printf("%.2f", avgWait);
				rpt.println();
			}
			else {
				rpt.print(n+" processors: totalWait="+totWait+", maxWait="+maxWait+ ", averageWait=");
				rpt.printf("%.2f", avgWait);
				rpt.println();
			}
		}
		sc.close();
		rpt.close();
		trc.close();
	}
}
