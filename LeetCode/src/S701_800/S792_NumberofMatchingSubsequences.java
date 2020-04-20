package S701_800;


import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Create by haifei on 28/12/2019 10:29 PM.
 */
public class S792_NumberofMatchingSubsequences {

  // TLE
  public int numMatchingSubseq(String S, String[] words) {
    int result = 0;

    for (int i = 0; i < words.length; i++) {
      String str = S;
      String[] split = words[i].split("");
      boolean isMatch = true;

      for (int j = 0; j < split.length; j++) {
        if (str.contains(split[j])) {
          if (str.indexOf(split[j]) == str.length() - 1) {
            str = "";
            continue;
          }
          str = str.substring(str.indexOf(split[j]) + 1);
        } else {
          isMatch = false;
          break;
        }
      }

      if (isMatch) {
        result++;
      }
    }

    return result;
  }

  // solution 2
  public int numMatchingSubseq2(String S, String[] words) {

    Map<Character, Deque<String>> map = new HashMap<>();

    for (char c = 'a'; c <= 'z'; c++) {
      map.putIfAbsent(c, new LinkedList<String>());
    }

    for (String word : words) {
      map.get(word.charAt(0)).addLast(word);
    }

    char[] chars = S.toCharArray();

    int count = 0;

    for (char c : chars) {
      Deque<String> queue = map.get(c);
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        String word = queue.removeFirst();
        if (word.length() == 1) {
          count++;
        } else {
          map.get(word.charAt(1)).addLast(word.substring(1));
        }
      }
    }
    return count;
  }

  @Test
  public void test() {

    System.out.println("a".indexOf("a"));

    String[] words = {"a", "bb", "acd", "ace"};
    int result = numMatchingSubseq("abcde", words);

    Assert.assertEquals(3, result);
  }


  @Test
  public void test1() {

    String[] words = {"iowuuudrgzmw", "azfcxtvhkruvuturdicnucvndigovkzrq", "ylmmo",
      "maptilrbfpjxiarmwalhbd", "oqvuahijyefbpqhbejuisksutsowhufsygtwteiqyligsnbqgl",
      "ytldcdlxqbaszbuxsacqwqnhrewhagldzhr", "zeeab", "cqie",
      "pvrazfcxtvhkruvuturdicnucvndigovkzrqiya", "zxnvpluwicurrtshyvevkriudayyysepzq",
      "wyhxltligahroyshfn", "nhrewhagldzhryzdmmrwn", "yqbvokpwovbnplshnzafunqglnpjvwddvdlmjjyzmw"
      , "nhrptuugyfsghluythksqhmxlmggtcbdd", "yligsnbqglqblhpdzzeurtdohdcbjvzgjwylmmoiundjsc",
      "zdrfdofhudqbfnzxnvpluwicurrtshyvevkriudayyysepzq",
      "ncygycdpehteiugqbptyqbvokpwovbnplshnzafun", "gdzutwgjofowhryrubnxkahocqjzww",
      "eppapjoliqlhbrbgh", "qwhgobwyhxltligahroys", "dzutwgjofowhryrubnxkah",
      "rydhxkdhffyytldcdlxqbaszbuxs", "tyqbvokpwovbnplshnzafunqglnpjvwddvdlmjjyzmwwxzjc",
      "khvyjyrydhxkdhffyytldcdlxqbasz", "jajekhvyjyrydhxkdhffyytldcdlxqbaszbuxsacqwqn",
      "ppapjoliqlhbrbghq", "zmwwxzjckmaptilrbfpjxiarm", "nxkahocqjzwwagqidjhwbunvlchoj",
      "ybfxpvqywqjdlyznmojdhbeomyjqptltp", "udrgzmwnxae", "nqglnpjvwddvdlmjjyzmww",
      "swlvtlbmkrsurrcsgdzutwgjofowhryrubn", "hudqbfnzxnvpluwicurr", "xaezuqlsfvchjf",
      "tvibbwxnokzkhndmdhweyeycamjeplec", "olnswlvtlbmkrsurrcsgdzu",
      "qiyastqpmfmuouycodvsyjajekhvyjyrydhxkdhffyyt", "eiqyligsnbqglqblhpdzzeurtdohdcbjvzgjwyl",
      "cgiowuuudrgzmwnxaezuqlsfvchjflocz", "rxric", "cygycdpehteiugqbptyqbvokpwovbnplshnzaf", "g"
      , "surrcsgd", "yzenflfnhrptuugyfsghluythksqh", "gdzutwgjofowhryrubnxkahocqjzwwagqid",
      "ddeoincygycdpeh", "yznmojdhbeomyjqptltpugzceyzenflfnhrptuug", "ejuisks",
      "teiqyligsnbqglqblhpdzzeurtdohdcbjvzgjwylmmoi", "mrwnxhaqfezeeabuacyswollycgio",
      "qfskkpfakjretogrokmxemjjbvgmmqrfdxlkfvycwalbdeumav",
      "wjgjhlrpvhqozvvkifhftnfqcfjmmzhtxsoqbeduqmnpvimagq",
      "ibxhtobuolmllbasaxlanjgalgmbjuxmqpadllryaobcucdeqc",
      "ydlddogzvzttizzzjohfsenatvbpngarutztgdqczkzoenbxzv",
      "rmsakibpprdrttycxglfgtjlifznnnlkgjqseguijfctrcahbb",
      "pqquuarnoybphojyoyizhuyjfgwdlzcmkdbdqzatgmabhnpuyh",
      "akposmzwykwrenlcrqwrrvsfqxzohrramdajwzlseguupjfzvd",
      "vyldyqpvmnoemzeyxslcoysqfpvvotenkmehqvopynllvwhxzr",
      "ysyskgrbolixwmffygycvgewxqnxvjsfefpmxrtsqsvpowoctw",
      "oqjgumitldivceezxgoiwjgozfqcnkergctffspdxdbnmvjago",
      "bpfgqhlkvevfazcmpdqakonkudniuobhqzypqlyocjdngltywn",
      "ttucplgotbiceepzfxdebvluioeeitzmesmoxliuwqsftfmvlg",
      "xhkklcwblyjmdyhfscmeffmmerxdioseybombzxjatkkltrvzq",
      "qkvvbrgbzzfhzizulssaxupyqwniqradvkjivedckjrinrlxgi",
      "itjudnlqncbspswkbcwldkwujlshwsgziontsobirsvskmjbrq",
      "nmfgxfeqgqefxqivxtdrxeelsucufkhivijmzgioxioosmdpwx",
      "ihygxkykuczvyokuveuchermxceexajilpkcxjjnwmdbwnxccl",
      "etvcfbmadfxlprevjjnojxwonnnwjnamgrfwohgyhievupsdqd",
      "ngskodiaxeswtqvjaqyulpedaqcchcuktfjlzyvddfeblnczmh",
      "vnmntdvhaxqltluzwwwwrbpqwahebgtmhivtkadczpzabgcjzx",
      "yjqqdvoxxxjbrccoaqqspqlsnxcnderaewsaqpkigtiqoqopth",
      "wdytqvztzbdzffllbxexxughdvetajclynypnzaokqizfxqrjl",
      "yvvwkphuzosvvntckxkmvuflrubigexkivyzzaimkxvqitpixo",
      "lkdgtxmbgsenzmrlccmsunaezbausnsszryztfhjtezssttmsr",
      "idyybesughzyzfdiibylnkkdeatqjjqqjbertrcactapbcarzb",
      "ujiajnirancrfdvrfardygbcnzkqsvujkhcegdfibtcuxzbpds",
      "jjtkmalhmrknaasskjnixzwjgvusbozslrribgazdhaylaxobj",
      "nizuzttgartfxiwcsqchizlxvvnebqdtkmghtcyzjmgyzszwgi",
      "egtvislckyltpfogtvfbtxbsssuwvjcduxjnjuvnqyiykvmrxl",
      "ozvzwalcvaobxbicbwjrububyxlmfcokdxcrkvuehbnokkzala",
      "azhukctuheiwghkalboxfnuofwopsrutamthzyzlzkrlsefwcz",
      "yhvjjzsxlescylsnvmcxzcrrzgfhbsdsvdfcykwifzjcjjbmmu",
      "tspdebnuhrgnmhhuplbzvpkkhfpeilbwkkbgfjiuwrdmkftphk",
      "jvnbeqzaxecwxspuxhrngmvnkvulmgobvsnqyxdplrnnwfhfqq",
      "bcbkgwpfmmqwmzjgmflichzhrjdjxbcescfijfztpxpxvbzjch",
      "bdrkibtxygyicjcfnzigghdekmgoybvfwshxqnjlctcdkiunob",
      "koctqrqvfftflwsvssnokdotgtxalgegscyeotcrvyywmzescq",
      "boigqjvosgxpsnklxdjaxtrhqlyvanuvnpldmoknmzugnubfoa",
      "jjtxbxyazxldpnbxzgslgguvgyevyliywihuqottxuyowrwfar",
      "zqsacrwcysmkfbpzxoaszgqqsvqglnblmxhxtjqmnectaxntvb",
      "izcakfitdhgujdborjuhtwubqcoppsgkqtqoqyswjfldsbfcct",
      "rroiqffqzenlerchkvmjsbmoybisjafcdzgeppyhojoggdlpzq",
      "xwjqfobmmqomhczwufwlesolvmbtvpdxejzslxrvnijhvevxmc",
      "ccrubahioyaxuwzloyhqyluwoknxnydbedenrccljoydfxwaxy",
      "jjoeiuncnvixvhhynaxbkmlurwxcpukredieqlilgkupminjaj",
      "pdbsbjnrqzrbmewmdkqqhcpzielskcazuliiatmvhcaksrusae",
      "nizbnxpqbzsihakkadsbtgxovyuebgtzvrvbowxllkzevktkuu",
      "hklskdbopqjwdrefpgoxaoxzevpdaiubejuaxxbrhzbamdznrr",
      "uccnuegvmkqtagudujuildlwefbyoywypakjrhiibrxdmsspjl",
      "awinuyoppufjxgqvcddleqdhbkmolxqyvsqprnwcoehpturicf"};
    int result = numMatchingSubseq2(
      "ricogwqznwxxcpueelcobbbkuvxxrvgyehsudccpsnuxpcqobtvwkuvsubiidjtccoqvuahijyefbpqhbejuisksutsowhufsygtwteiqyligsnbqglqblhpdzzeurtdohdcbjvzgjwylmmoiundjscnlhbrhookmioxqighkxfugpeekgtdofwzemelpyjsdeeppapjoliqlhbrbghqjezzaxuwyrbczodtrhsvnaxhcjiyiphbglyolnswlvtlbmkrsurrcsgdzutwgjofowhryrubnxkahocqjzwwagqidjhwbunvlchojtbvnzdzqpvrazfcxtvhkruvuturdicnucvndigovkzrqiyastqpmfmuouycodvsyjajekhvyjyrydhxkdhffyytldcdlxqbaszbuxsacqwqnhrewhagldzhryzdmmrwnxhaqfezeeabuacyswollycgiowuuudrgzmwnxaezuqlsfvchjfloczlwbefksxsbanrektvibbwxnokzkhndmdhweyeycamjeplecewpnpbshhidnzwopdjuwbecarkgapyjfgmanuavzrxricbgagblomyseyvoeurekqjyljosvbneofjzxtaizjypbcxnbfeibrfjwyjqrisuybfxpvqywqjdlyznmojdhbeomyjqptltpugzceyzenflfnhrptuugyfsghluythksqhmxlmggtcbdddeoincygycdpehteiugqbptyqbvokpwovbnplshnzafunqglnpjvwddvdlmjjyzmwwxzjckmaptilrbfpjxiarmwalhbdjiwbaknvcqovwcqiekzfskpbhgxpyomekqvzpqyirelpadooxjhsyxjkfqavbaoqqvvknqryhotjritrkvdveyapjfsfzenfpuazdrfdofhudqbfnzxnvpluwicurrtshyvevkriudayyysepzqfgqwhgobwyhxltligahroyshfndydvffd", words);

    Assert.assertEquals(51, result);
  }
}
