package example;

public class C  {
    private int i;

    public C(int i) {
        System.out.println("C.C(int i)");
        this.i = i;
    }

    public int mth1() {
if (A.listMethod.containsKey("C.mth1[]")) {
   int value = A.listMethod.get("C.mth1[]")+1;
   A.listMethod.put("C.mth1[]",value);
} else {
   A.listMethod.put("C.mth1[]", 1);
}
       System.out.println("C.mth1()");
        return 100/i;
    }
}
