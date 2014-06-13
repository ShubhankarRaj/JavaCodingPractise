class GarbageColl{
int a;
int b;

  public void setData(int c,int d){
    a=c;
    b=d;
  }
  public void showData(){
    System.out.println("Value of a = "+a);
    System.out.println("Value of a = "+b);
  }
  public static void main(String args[]){
    GarbageColl s1 = new GarbageColl();
    GarbageColl s2 = new GarbageColl();
    s1.setData(1,2);
    s2.setData(3,4);
    s1.showData();
    s2.showData();
    GarbageColl s3;
    s3=s2;
    s3.showData();
    s2=null;
    s3.showData();
    s3=null;
    s3.showData();
  }
}
