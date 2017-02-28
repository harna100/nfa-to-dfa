public class Main {

	public static void main(String[] args) {
		FileParser fp = new FileParser(args[0]);
		fp.parseFile();

		Converter converter = new Converter(fp);
		converter.convert();
		System.out.println("converter = " + converter);
		String newFileName = args[0].substring(0,args[0].lastIndexOf(".")) + ".DFA";
		OutputWriter outputWriter = new OutputWriter(converter, newFileName);
		outputWriter.writeFile();

	}
}