import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Random random=new Random();
        System.out.print("Введите размерность матриц (nxn): ");
        int n=sc.nextInt();
        int[][] matrix1=new int[n][n];
        int[][] matrix2=new int[n][n];
        int[][] matrix3=new int[n][n];
        int[][] matrix4=new int[n][n];
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<n;j++)
            {
                matrix1[i][j]=random.nextInt(10,50);
            }
        }
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<n;j++)
            {
                matrix2[i][j]=random.nextInt(10,50);
            }
        }
        System.out.println("------------------------- Работа 4 потоков -------------------------");
        long time =System.currentTimeMillis();
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                String name=Thread.currentThread().getName();
                System.out.println("----"+name+"----");
                int b=matrix2[0].length;
                int c= matrix1.length / 4;
                for (int i=0; i<=c;i++)
                {
                    for (int j=0;j<b;j++)
                    {
                        for (int k=0;k<matrix2.length;k++)
                        {
                            matrix3[i][j]=matrix3[i][j] + matrix1[i][k]*matrix2[k][j];
                        }
                    }
                }
            }
        });
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                String name=Thread.currentThread().getName();
                System.out.println("----"+name+"----");
                int b=matrix2[0].length;
                int c= (matrix1.length / 2) +1;
                int d= (matrix1.length /4) + 1;
                for (int i=d; i<c;i++)
                {
                    for (int j=0;j<b;j++)
                    {
                        for (int k=0;k<matrix2.length;k++)
                        {
                            matrix3[i][j]+=matrix1[i][k]*matrix2[k][j];
                        }
                    }
                }
            }
        });
        Thread thread3=new Thread(new Runnable() {
            @Override
            public void run() {
                String name=Thread.currentThread().getName();
                System.out.println("----"+name+"----");
                int a=matrix1.length;
                int b=matrix2[0].length;
                int c= ((3 * (matrix1.length)) / 4) +1;
                int d= (matrix1.length /2) + 1;
                for (int i=d; i<c;i++)
                {
                    for (int j=0;j<b;j++)
                    {
                        for (int k=0;k<matrix2.length;k++)
                        {
                            matrix3[i][j]+=matrix1[i][k]*matrix2[k][j];
                        }
                    }
                }
            }
        });
        Thread thread4=new Thread(new Runnable() {
            @Override
            public void run() {
                String name=Thread.currentThread().getName();
                System.out.println("----"+name+"----");
                int a=matrix1.length;
                int b=matrix2[0].length;
                int c= ((3 * (matrix1.length)) / 4) +1;
                for (int i=c; i<a; i++)
                {
                    for (int j=0;j<b;j++)
                    {
                        for (int k=0;k<matrix2.length;k++)
                        {
                            matrix3[i][j]+=matrix1[i][k]*matrix2[k][j];
                        }
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
//        for (int i=0;i<n;i++)
//        {
//            for (int j=0;j<n;j++)
//            {
//                System.out.print("n["+i+"]["+j+"]: "+matrix3[i][j]+" ");
//            }
//        }
        if(thread1.isAlive()) {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if(thread2.isAlive()) {
            try {
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if(thread3.isAlive()) {
            try {
                thread3.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if(thread4.isAlive()) {
            try {
                thread4.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        double timeEnd=System.currentTimeMillis()-time;
        System.out.println("\n------------------------- Работа потока Main -------------------------");
        long time2 =System.currentTimeMillis();
        for(int i=0;i<n;i++)
        {
            for (int j=0;j<n;j++)
            {
                for (int k=0;k<n;k++)
                {
                    matrix4[i][j]=(matrix4[i][j]+ matrix1[i][k]*matrix2[k][j]);
                }
            }
        }
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<n;j++)
            {
                //System.out.print("n["+i+"]["+j+"]: "+matrix4[i][j]+" ");
            }
        }
        System.out.println("\n\n4 потока: "+(timeEnd)+ "ms");
        System.out.println("Поток Main: "+((double) System.currentTimeMillis()-time2)+"ms");
    }
}