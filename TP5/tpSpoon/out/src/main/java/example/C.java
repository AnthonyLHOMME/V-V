package example;

public class C  {
    private int i;

    public C(int i) {
        System.out.println("C.C(int i)");
        this.i = i;
    }

    public int mth1() {
		String tab = "";
		for (int i_ = 0; i_ < A.nbTab; i_++) {
			tab = tab+" |	";
		}
		vv.spoon.logger.LogWriter.out(tab+"C.mth1[]",false);
		A.nbTab++;
       System.out.println("C.mth1()");
		A.nbTab--;
        return 100/i;
    }
}
