

import java.util.Scanner;
class point {
    public long x;
    public long y;

    point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    // A utility function to find the
// distance between two points
    public static float dist(point p1, point p2) {
        return (float) Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
                (p1.y - p2.y) * (p1.y - p2.y)
        );
    }
}


public class Main {
    private static point[] points  ;
    private static point[] strips  ;
    public static int Size;
    public static void mergesort_y(point[] intArray, int start , int end) {
        if (end - start +1 > 1 ){
            mergesort_y(intArray, start,start+((end - start +1)/2)-1);
            mergesort_y(intArray, start+((end - start +1)/2), end);
            merge_y(intArray, start,start+((end - start +1)/2)-1,start+((end - start +1)/2), end);
        }
    }
    public static void merge_y(point[] intArray, int start1, int end1, int start2, int end2){
        point[] sample = new point[end1 - start1 + end2 - start2 +2];
        int counter1 = start1;
        int counter2  = start2;
        int counter3 = 0;
        while ((counter3!=end1 - start1 + end2 - start2 +1) && (counter1 <= end1) && (counter2 <= end2)){
            if(intArray[counter1].y <= intArray[counter2].y){
                sample[counter3] = intArray[counter1];
                counter1++;
                counter3++;
            }else {
                sample[counter3] = intArray[counter2];
                counter2++;
                counter3++;
            }
        }
        if (counter1 <= end1 ){
            while ((counter3 <= end1 - start1 + end2 - start2 +1) && (counter1 <= end1)){
                sample[counter3] = intArray[counter1];
                counter1++;
                counter3++;
            }
        }else if(counter2 <= end2){
            while ((counter3 <= end1 - start1 + end2 - start2 +1) && (counter2 <= end2)){
                sample[counter3] = intArray[counter2];
                counter2++;
                counter3++;
            }
        }
        System.arraycopy(sample,0,intArray,start1,end1-start1+end2-start2+2);
    }
    public static void mergesort(point[] intArray, int start , int end) {
        if (end - start +1 > 1 ){
            mergesort(intArray, start,start+((end - start +1)/2)-1);
            mergesort(intArray, start+((end - start +1)/2), end);
            merge(intArray, start,start+((end - start +1)/2)-1,start+((end - start +1)/2), end);
        }
    }
    public static void merge(point[] intArray, int start1, int end1, int start2, int end2){
        point[] sample = new point[end1 - start1 + end2 - start2 +2];
        int counter1 = start1;
        int counter2  = start2;
        int counter3 = 0;
        while ((counter3!=end1 - start1 + end2 - start2 +1) && (counter1 <= end1) && (counter2 <= end2)){
            if(intArray[counter1].x <= intArray[counter2].x){
                sample[counter3] = intArray[counter1];
                counter1++;
                counter3++;
            }else {
                sample[counter3] = intArray[counter2];
                counter2++;
                counter3++;
            }
        }
        if (counter1 <= end1 ){
            while ((counter3 <= end1 - start1 + end2 - start2 +1) && (counter1 <= end1)){
                sample[counter3] = intArray[counter1];
                counter1++;
                counter3++;
            }
        }else if(counter2 <= end2){
            while ((counter3 <= end1 - start1 + end2 - start2 +1) && (counter2 <= end2)){
                sample[counter3] = intArray[counter2];
                counter2++;
                counter3++;
            }
        }
        System.arraycopy(sample,0,intArray,start1,end1-start1+end2-start2+2);
    }
    public static point[] ClosestPair(point[] intArray, int size){
        point[] pair = new point[2];
        if (size == 2){
            if(intArray[0].x < intArray[1].x){
                return intArray;
            }else {
                pair[0] = intArray[1];
                pair[1] = intArray[0];
            }
        }else if(size > 3){

            long mid = intArray[(size/2)-1].x;

            point[] L1 = new point[(int)(size/2)];
            int counter_L1 = 0;
            int counter_L2 = 0;
            point[] L2 = new point[size - (size/2)];
            //splitting O(n)
            for (int i = 0; i < size ; i++) {
                if(intArray[i].x <= mid){
                    L1[counter_L1] = intArray[i];counter_L1++;
                }else {
                    L2[counter_L2] = intArray[i];counter_L2++;
                }
            }
            point[] dl = ClosestPair(L1, L1.length);
            point[] dr = ClosestPair(L2,  L2.length);
            if (point.dist(dl[0],dl[1]) < point.dist(dr[0],dr[1])){
                pair[0] = dl[0];pair[1] = dl[1];
            }else {
                pair[0] = dr[0];pair[1] = dr[1];
            }
            float d = point.dist(pair[0],pair[1]);
            int count = 0;
            //building strip by O(2N)
            for (int i = 0; i < size; i++) {
                if (Math.abs(intArray[i].x - mid)<= d  ){
                    count++;
                }
            }
            point[] strip = new point[count];
            count = 0 ;
            for (int i = 0; i < size; i++) {
                if (Math.abs(intArray[i].x - mid)<= d  ){
                    strip[count] = intArray[i];
                    count++;
                }
            }
            //built strip
            strips = strip;
            mergesort_y(strips, 0, strips.length-1);
            int count2 ;
            float min = d;
            for (int i = 0; i < strips.length; i++) {
                count2 = 1;
                for (int j = i+1 ; j <strips.length && count2<=6; j++) {
                    if (strips[i].x - mid <= 0 && strips[j].x - mid >= 0){
                        if (point.dist(strips[i], strips[j]) < min){
                            min = point.dist(strips[i], strips[j]);
                            pair[0] = strips[i];pair[1] = strips[j];
                        }
                        count2++;
                    }else if (strips[i].x - mid > 0 && strips[j].x - mid <= 0){
                        if (point.dist(strips[i], strips[j]) < min){
                            min = point.dist(strips[i], strips[j]);
                            pair[0] = strips[j];pair[1] = strips[i];
                        }
                        count2++;
                    }
                }
            }
        }else if(size ==3){

            point[] smallest = new point[2];

            //find the smallest
            if(point.dist(intArray[0],intArray[1])< point.dist(intArray[2],intArray[1])) {
                if(point.dist(intArray[2],intArray[0])< point.dist(intArray[0],intArray[1])) {
                    if(intArray[0].x < intArray[2].x){
                        smallest[0] = intArray[0];
                        smallest[1] = intArray[2];
                    }else {
                        smallest[1] = intArray[0];
                        smallest[0] = intArray[2];
                    }
                } else {
                    if(intArray[0].x < intArray[1].x){
                        smallest[0] = intArray[0];
                        smallest[1] = intArray[1];
                    }else {
                        smallest[1] = intArray[0];
                        smallest[0] = intArray[1];
                    }
                }
            } else {
                if(point.dist(intArray[2],intArray[1])< point.dist(intArray[2],intArray[0])) {
                    if(intArray[1].x < intArray[2].x){
                        smallest[0] = intArray[1];
                        smallest[1] = intArray[2];
                    }else {
                        smallest[1] = intArray[1];
                        smallest[0] = intArray[2];
                    }
                } else {
                    if(intArray[0].x < intArray[1].x){
                        smallest[0] = intArray[0];
                        smallest[1] = intArray[1];
                    }else {
                        smallest[1] = intArray[0];
                        smallest[0] = intArray[1];
                    }
                }
            }
            pair = smallest;
        }
        return pair;
    }
    public static void main(String[] args) {
        int n;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        Size = n;
        long X,Y;
        points =  new point[n];
        for(int i=0; i<n; i++)
        {
            X = sc.nextLong();
            Y = sc.nextLong();
            points[i] = new point(X,Y);
        }
        mergesort(points, 0,points.length-1);
        point[] ans = ClosestPair(points, n);
        System.out.println(ans[0].x+" "+ans[0].y+" "+ans[1].x+" "+ans[1].y);
    }
}


