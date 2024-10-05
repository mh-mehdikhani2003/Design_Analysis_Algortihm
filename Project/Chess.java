

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Chess {

    static int[] Rokh_x_kond(int[][] area_x,int n){
        int[] x_position = new int[n];
        boolean[] x_bool = new boolean[n];

        boolean ans ;
        for (int i = 0; i < n; i++) {
            ans = false;
            for (int j = area_x[i][0]; j <= area_x[i][1]; j++) {
                if(!x_bool[j-1]){
                    x_position[area_x[i][2]] = j;ans = true;x_bool[j-1] = true;break;
                }
            }
            if(!ans){
                System.out.println("impossible");System.exit(0);
            }
        }
        return x_position;
    }

    static int[] Rokh_y_kond(int[][] area_x,int n){
        int[] y_position = new int[n];
        boolean[] y_bool = new boolean[n];
        boolean ans ;
        for (int i = 0; i < n; i++) {
            ans = false;
            for (int j = area_x[i][0]; j <= area_x[i][1]; j++) {
                if(!y_bool[j-1]){
                    y_position[area_x[i][2]] = j;ans = true;y_bool[j-1] = true;break;
                }
            }
            if(!ans){
                System.out.println("impossible");System.exit(0);
            }
        }
        return y_position;
    }
    static int[] Rokh_x_tond(int[][] area_x,int n){
        int[] x_position = new int[n];
        boolean[] x_bool = new boolean[n];

        boolean ans ;
        for (int i = 0; i < n; i++) {
            ans = false;
            if(i == 0){
                for (int j = area_x[i][0]; j <= area_x[i][1]; j++) {
                    if(!x_bool[j-1]){
                        x_position[area_x[i][2]] = j;ans = true;x_bool[j-1] = true;break;
                    }
                }
            }else {
                for (int j = Math.max(area_x[i][0],x_position[i-1]); j <= area_x[i][1]; j++) {
                    if(!x_bool[j-1]){
                        x_position[area_x[i][2]] = j;ans = true;x_bool[j-1] = true;break;
                    }
                }
            }

            if(!ans){
                System.out.println("impossible");System.exit(0);
            }
        }
        return x_position;
    }

    static int[] Rokh_y_tond(int[][] area_x,int n){
        int[] y_position = new int[n];
        boolean[] y_bool = new boolean[n];
        boolean ans ;
        for (int i = 0; i < n; i++) {
            ans = false;
            if(i == 0){
                for (int j = area_x[i][0]; j <= area_x[i][1]; j++) {
                    if(!y_bool[j-1]){
                        y_position[area_x[i][2]] = j;ans = true;y_bool[j-1] = true;break;
                    }
                }
            }else {
                for (int j = Math.max(area_x[i][0],y_position[i-1]); j <= area_x[i][1]; j++) {
                    if(!y_bool[j-1]){
                        y_position[area_x[i][2]] = j;ans = true;y_bool[j-1] = true;break;
                    }
                }
            }
            if(!ans){
                System.out.println("impossible");System.exit(0);
            }
        }
        return y_position;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] area_x = new int[n][3];
        int[][] area_y = new int[n][3];

        for (int i = 0; i < n; i++) {
            area_x[i][0] = scanner.nextInt();
            area_y[i][0] = scanner.nextInt();
            area_x[i][1] = scanner.nextInt();
            area_y[i][1] = scanner.nextInt();
            area_x[i][2] = i;area_y[i][2] = i;
        }
        Arrays.sort(area_x, Comparator.comparingDouble(o -> o[1]));
        Arrays.sort(area_y, Comparator.comparingDouble(o -> o[1]));
        int[] xPos;
        int[] yPos;
        if(n<10000){
            xPos = Rokh_x_kond(area_x,n);
            yPos = Rokh_y_kond(area_y,n);
        }else {
            xPos = Rokh_x_tond(area_x,n);
            yPos = Rokh_y_tond(area_y,n);
        }
        for (int i = 0; i < n; i++) {
            System.out.println(xPos[i]+" "+yPos[i]);
        }

    }
}
