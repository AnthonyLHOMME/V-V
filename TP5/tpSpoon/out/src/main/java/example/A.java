package example;
import java.util.HashMap;
import java.util.Map;


public class A  {

	public static Map<String, Integer> listMethod = new HashMap<String, Integer>();
    public static void main(String[] args) {
if (A.listMethod.containsKey("A.main[java.lang.String[] args]")) {
   int value = A.listMethod.get("A.main[java.lang.String[] args]")+1;
   A.listMethod.put("A.main[java.lang.String[] args]",value);
} else {
   A.listMethod.put("A.main[java.lang.String[] args]", 1);
}

        System.out.println("A.main(String[] args)");

        A a = new A();
        a.mth1(Integer.parseInt(args[0]));

		for (String key: listMethod.keySet()) vv.spoon.logger.LogWriter.out(key+": "+listMethod.get(key),false);
    }

    public void mth1(int count) {
if (A.listMethod.containsKey("A.mth1[int count]")) {
   int value = A.listMethod.get("A.mth1[int count]")+1;
   A.listMethod.put("A.mth1[int count]",value);
} else {
   A.listMethod.put("A.mth1[int count]", 1);
}
        System.out.println("A.mth1(int count)");

        B b = new B();
        for(int i = 0; i < count; i++) {
            try {
                b.mth1(i);
                b.mth2();
            } catch (Exception e) {
                System.err.println("error in A.mth1(int count)");
            }
        }

    }

}
