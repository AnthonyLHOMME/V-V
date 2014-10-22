package example;


public class A  {

	public static int nbTab = 0;
    public static void main(String[] args) {
		String tab = "";
		for (int i_ = 0; i_ < A.nbTab; i_++) {
			tab = tab+" |	";
		}
		vv.spoon.logger.LogWriter.out(tab+"A.main[java.lang.String[] args]",false);
		A.nbTab++;

        System.out.println("A.main(String[] args)");

        A a = new A();
        a.mth1(Integer.parseInt(args[0]));

		A.nbTab--;
    }

    public void mth1(int count) {
		String tab = "";
		for (int i_ = 0; i_ < A.nbTab; i_++) {
			tab = tab+" |	";
		}
		vv.spoon.logger.LogWriter.out(tab+"A.mth1[int count]",false);
		A.nbTab++;
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

		A.nbTab--;
    }

}
