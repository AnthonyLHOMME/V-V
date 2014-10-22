package example;


public class B  {


    public void mth1(int i) {
		String tab = "";
		for (int i_ = 0; i_ < A.nbTab; i_++) {
			tab = tab+" |	";
		}
		vv.spoon.logger.LogWriter.out(tab+"B.mth1[int i]",false);
		A.nbTab++;
        System.out.println("B.mth1(int i)");

        C c = new C(i);
        int result = c.mth1();

        System.out.println("result = " + result);
		A.nbTab--;
    }

    public void mth2() {
		String tab = "";
		for (int i_ = 0; i_ < A.nbTab; i_++) {
			tab = tab+" |	";
		}
		vv.spoon.logger.LogWriter.out(tab+"B.mth2[]",false);
		A.nbTab++;
        System.out.println("B.mth2()");
		A.nbTab--;
    }

}
