import algo.programers.pcce.PCCESol10;

public class Main {
    public static void main(String[] args) {
        PCCESol10 solution = new PCCESol10();

        int[][] data = {{1, 20300104, 100, 80},{2, 20300804, 847, 37},{3, 20300401, 10, 8}};
        String ext = "date";
        int val_ext = 20300501;
        String sort_by = "remain";

        int[][] result = solution.solution(data, ext, val_ext, sort_by);

        System.out.print("[");
        for(int i=0; i<result.length; i++) {
            System.out.print(" [");
            for(int j=0; j<result[i].length; j++) {
                System.out.print(result[i][j] + ", ");
            }
            System.out.print("],");
        }
        System.out.print("]");
    }
}