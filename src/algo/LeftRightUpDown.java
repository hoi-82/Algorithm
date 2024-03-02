package algo;

public class LeftRightUpDown {
    public static String lrudFc(int n,String[] input) {
        int x = 1;
        int y = 1;
        for(String w : input) {
            switch(w) {
                case "R":
                    y+=1;
                    break;
                case "L":
                    y-=1;
                    break;
                case "U":
                    x-=1;
                    break;
                case "D":
                    x+=1;
                    break;
                default:
                    break;
            }

            if(x == 0) x=1;
            else if(x > n) x=n;

            if(y == 0) y=1;
            else if(y > n) y=n;
        }

        return x + " " + y;
    }

    public static void main(String[] args) throws Exception {
        String[] input = {"R","R","R","U","D","D"};
        int n = 5;
        System.out.println(lrudFc(n, input));
    }
}