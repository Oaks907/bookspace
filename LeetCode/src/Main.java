import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(100L);
        }

        System.out.println("start");
        List<Long> parallelList = list.parallelStream().map(aLong -> {
            try {
                System.out.print("== ");
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return aLong + 1;})
          .collect(Collectors.toList());
        System.out.println("end");
        parallelList.stream().forEach(aLong -> {
            if (aLong == 100) {
                System.out.print("false ");
            } else {
                System.out.print(aLong + " ");
            }
        });
    }
}
