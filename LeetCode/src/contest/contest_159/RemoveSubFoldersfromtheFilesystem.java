package contest.contest_159;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by haifei on 20/10/2019 10:58 AM.
 */
public class RemoveSubFoldersfromtheFilesystem {

  public List<String> removeSubfolders(String[] folder) {

    Arrays.sort(folder);

    String prefix = null;
    List<String> stringList = new ArrayList<>();
    List<String> result = new ArrayList<>();

    for (int i = 0; i < folder.length; i++) {

      String str = folder[i];
      result.add(str);

      if (prefix == null) {
        stringList.add(str);
        prefix = str.substring(0, 2);
        continue;
      }

      if (str.contains(prefix)) {
        for (int j = 0; j < stringList.size(); j++) {
          if (str.contains(stringList.get(j))) {
            result.remove(result.size() - 1);
            break;
          }
        }
        stringList.add(str);
      } else {
        stringList.clear();
        prefix = str.substring(0, 2);
        stringList.add(str);
      }
    }

    return result;
  }

  @Test
  public void test() {
    String[] arr = {"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"};
    List<String> result = removeSubfolders(arr);

    System.out.println(result);

    Assert.assertEquals(3, result.size());
  }

  @Test
  public void test1() {
    String[] arr = {"/a/b/c", "/a/b/ca", "/a/b/d"};
    List<String> result = removeSubfolders(arr);

    System.out.println(result);

    Assert.assertEquals(3, result.size());
  }

}
