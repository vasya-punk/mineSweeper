public class Main {
    public static final String DEFAULT_FIELD = "**********\n.....*....\n...*....*.\n";

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        String mineField = DEFAULT_FIELD;
        if(args.length > 0){
            mineField = args[0];
        }

        StringBuffer mfb = new StringBuffer(mineField);

//         for test
//        for (int i=0;i<20;i++){
//            mfb.append(mfb);
//        }

        MineSweeper ms = new MineSweeperImpl();
        ms.setMineField(mfb.toString());
        System.out.println(ms.getHintField());

        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
    }
}

