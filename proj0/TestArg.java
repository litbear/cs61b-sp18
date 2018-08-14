class TestArg{
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please supply 2 arguments.");
            return;
		}	
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        System.out.println(T);
        System.out.println(dt);
    }
}