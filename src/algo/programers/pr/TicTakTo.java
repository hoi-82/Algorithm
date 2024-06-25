package algo.programers.pr;

public class TicTakTo {
    class Solution {
        public int solution(String[] board) {
            int answer = 1;

            int oc = 0;
            int xc = 0;

            int lineOCount = 0;
            int lineXCount = 0;

            boolean firstFlag = true;
            for(String line : board) {
                if(isLineComplete(line, Unit.O)) lineOCount++;
                else if(isLineComplete(line, Unit.X)) lineXCount++;

                for(int i=0; i<3; i++) {
                    if(line.charAt(i) == Unit.O.getUnit()) oc++;
                    else if(line.charAt(i) == Unit.X.getUnit()) xc++;
                }
            }

            if(xc > oc) answer = 0;
            if(lineOCount > 0 && lineXCount > 0) answer = 0;

            return answer;
        }

        private boolean isLineComplete(String line, Unit unit) {
            return line.charAt(0) == unit.getUnit() && line.charAt(1) == unit.getUnit() && line.charAt(2) == unit.getUnit();
        }

        private enum Unit {
            O('O'),
            X('X');

            private char unit;

            Unit(char unit) {
                this.unit = unit;
            }

            public char getUnit() {
                return this.unit;
            }
        }
    }
}
