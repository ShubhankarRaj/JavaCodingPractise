package SCJPMon;



	import java.util.*;
	import java.io.*;

	public class MDemp{

		public static void main(String args[]){

			//Today is 02/07/2008
			Date dt1=new Date();
			Date dt2=new Date(1000);
			boolean b1=dt1.before(dt2);
			boolean b3=new File("script1.js").isFile();
			boolean b4=false;
			int x=0;

			do{
				x++;
				switch(x){
				case 0: System.out.print("A "); break;
				case 1: System.out.print("B "); break;
				case 2: System.out.print("C "); break;
				case 3: System.out.print("D "); 
				if(b1){ b4=true;} break;
				case 4: System.out.print("E ");
				if(!b1){ b4=true;} break;
				}

				if(b4){ break; }

			}while(b3=true);

		}

	}

