package example;


public class B  {


    public void mth1(int i) {
if (A.listMethod.containsKey("B.mth1[int i]")) {
   int value = A.listMethod.get("B.mth1[int i]")+1;
   A.listMethod.put("B.mth1[int i]",value);
} else {
   A.listMethod.put("B.mth1[int i]", 1);
}
        System.out.println("B.mth1(int i)");

        C c = new C(i);
        int result = c.mth1();

        System.out.println("result = " + result);
    }

    public void mth2() {
if (A.listMethod.containsKey("B.mth2[]")) {
   int value = A.listMethod.get("B.mth2[]")+1;
   A.listMethod.put("B.mth2[]",value);
} else {
   A.listMethod.put("B.mth2[]", 1);
}
        System.out.println("B.mth2()");
    }

}
