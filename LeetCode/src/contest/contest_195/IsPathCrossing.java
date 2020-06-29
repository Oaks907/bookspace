package contest.contest_195;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.junit.Test;

/**
 * Create by haifei on 28/6/2020 11:01 AM.
 */
public class IsPathCrossing {

    public boolean isPathCrossing(String path) {

        char[] pathArr = path.toCharArray();
        TempNode node = new TempNode();
        TempNode pre = node;
        Set<TempNode> set = new HashSet<>();;
        set.add(node);
        for (char item : pathArr) {

            TempNode temp = new TempNode();
            temp.x = pre.x;
            temp.y = pre.y;

            switch (item) {
                case 'N':
                    temp.y += 1;
                    break;
                case 'S':
                    temp.y -= 1;
                    break;
                case 'E':
                    temp.x -= 1;
                    break;
                case 'W':
                    temp.x += 1;
                    break;
            }

            if (set.contains(temp)) {
                System.out.println(set);
                return true;
            }
            set.add(temp);
            pre = temp;
        }
        System.out.println(set);
        return false;
    }

    public class TempNode {
        int x;
        int y;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TempNode tempNode = (TempNode) o;
            return x == tempNode.x && y == tempNode.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "TempNode{" + "x=" + x + ", y=" + y + '}';
        }
    }

    @Test
    public void test() {
        boolean result = isPathCrossing("WSSESEEE");
    }
}
