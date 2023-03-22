
//class Pascal{
//	public static void main(String args[])
//	{
//		int a[][] = new int [6][6];
//		for (int r = 0;r <=6; r++)
//		{
//			for(int c = 0; c <=6; c++ )
//			{
//				if (r == c+1|| c==1)
//					a[r][c] = c+1;
//				else if (r==c || c ==0)
//					a[r][c]= 1;
//				else
//					a[r][c] = a[r-1][c-1] + a[r-1][c];
//			}
//		}
//		for (int r = 0;r <6; r++)
//		{
//			for(int c = 0; c <6; c++ )
//			{
//				System.out.print(a[r][c]);
//			}
//		}
//	}
//}


import java.io.*;
public class Pascal
 {
   public static void main(String args[])
   {
     BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     try
       {
         System.out.print("Enter number of rows for pascal triangle :");
         int rows=Integer.parseInt(br.readLine());
         for(int i=0;i<rows;i++)
            {
               int num=1;
//               System.out.format("%"+(rows-i)*2+"s"," ");
               for(int j=0;j<=i;j++)
                 {
                   System.out.format("%4d",num);
                   num=num*(i-j)/(j+1);
                 }
             System.out.println();
            }
        }
        catch(Exception e)
            {
              System.out.println(""+e);
            }
    }
}