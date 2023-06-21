package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * <b>Test class for the program's prototype</b><br><br>
 * Use this class to run the tests.<br>
 * Automatically calls the method which gives information about the tests' results.
 * @author Martin
 */
public class ProtoTest {
	
	public static void main(String[] args) throws FileNotFoundException {
		ProtoTest test = new ProtoTest();
		test.Initialize();
		test.Run();
	}
	
	/**The file that contains the runnable commands.*/
	private File commandsFile;
	/**The file that contains the expected output after the run.*/
	static File expectedOutFile;
	/**The file that contains the generated output after the run.*/
	static File generatedOutFile;
	
	/**Proto class*/
	private Prototype proto;
	
	private static File wd;
	
	private Scanner sc;
	
	/**
	 * Initializes the files' path
	 */
	public void Initialize() {
		//TO-DO: Give the files' path!
		proto = new Prototype();
		
		wd = new File(System.getProperty("user.dir"), "src/src");
		commandsFile = new File(wd, "commands.txt");
		expectedOutFile = new File(wd, "expected.txt");
		
		String[] setLog = {"log", "on", "generated.txt"};
		proto.Log(setLog);
		generatedOutFile = Prototype.GetLogFile();
	}
	
	
	/**
	 * Runs the tests and gives the results back.
	 * @throws FileNotFoundException 
	 */
	public void Run() throws FileNotFoundException {
		if(commandsFile.exists()) {
			sc = new Scanner(commandsFile);
		}
		
		//TO-DO: Run tests here
		Test_2_1();
		Test_2_2();
		Test_2_3();
		Test_2_4();
		Test_2_5();
		Test_2_6();
		Test_2_7();
		Test_2_8();
		Test_2_9();
		Test_2_10();
		Test_2_11();
		Test_2_12();
		Test_2_13();
		Test_2_14();
		Test_2_15();
		Test_2_16();
		Test_2_17();
		
		String result = ProtoTest.TestCalculator.Calculate(generatedOutFile, expectedOutFile);
		System.out.println(result);
		sc.close();
	}
	
	
	//TO-DO: Write tests here - each test has a method
	// ------ IDE -----
	//----------------
	//public void Method() { ... Tesztet tartalmazó algoritmust ... }
	public void Test_2_1() throws FileNotFoundException {
		sc = new Scanner(commandsFile);
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
	}
	
	public void Test_2_2() {
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Move(cmd);
	}
	
	public void Test_2_3() {
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Move(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
	}
	
	public void Test_2_4() {
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Move(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
	}
	
	
	public void Test_2_5(){
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutAg(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Move(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
	}
	
	public void Test_2_6() {
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutEq(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Move(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PickUp(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
	}
	
	public void Test_2_7() {
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutAg(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Move(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Craft(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
	}
	
	public void Test_2_8() {
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddEq(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddAgCraft(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Anoint(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
	}
	
	public void Test_2_9() {
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddEq(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddAgCraft(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Anoint(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
	}
	
	public void Test_2_10() {
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddAgEff(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddEq(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.StealEq(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
	}
	
	public void Test_2_11() {
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddEq(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Drop(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
	}
	
	public void Test_2_12() {
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddAgEff(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
	}
	
	public void Test_2_13() {
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddAgEff(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddAgEff(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
	}
	
	public void Test_2_14() {
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddEq(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddAgEff(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Attack(cmd);
	}
	
	public void Test_2_15() {
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddEq(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddEq(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddEq(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutEq(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PickUp(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
	}
	
	public void Test_2_16() {
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.AddEq(cmd);
		cmd = sc.nextLine().split(" ");
		proto.NewRound(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.StealEq(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
	}
	
	public void Test_2_17() {
		String[] cmd = sc.nextLine().split(" ");
		proto.NewGame(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutViro(cmd);
		cmd = sc.nextLine().split(" ");
		proto.PutAg(cmd);
		cmd = sc.nextLine().split(" ");
		proto.SetMat(cmd);
		cmd = sc.nextLine().split(" ");
		proto.SetMat(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Move(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Craft(cmd);
		cmd = sc.nextLine().split(" ");
		proto.Ls(cmd);
	}
	
	/**
	 * <b> Class for the tests' results.</b><br><br>
	 * Use this class to see if all the tests have succeeded.
	 * @author Martin
	 */
	private static class TestCalculator{
		
		
		/**
		 * Checks if all the tests have succeeded.
		 * @param generated - the file that contains the generated output
		 * @param expected - the file that contains the expected output
		 * @return the string which contains the results.
		 */
		public static String Calculate(File generated, File expected) {
			int succeededRows = 0;
			int numberOfRows = 0;	// the number of rows that the file contains
			
				try {
				FileReader fr_generated = new FileReader(generatedOutFile);
				FileReader fr_expected = new FileReader(expectedOutFile);
				BufferedReader br_gen = new BufferedReader(fr_generated);
				BufferedReader br_ex = new BufferedReader(fr_expected);
				
				String line_gen;
				String line_ex;
				
				int index = 0;
				
				boolean success = true;
				
				while(br_gen.ready()) {
					
					line_gen = br_gen.readLine();
					line_ex = br_ex.readLine();
					index++;
					numberOfRows = index;
					if(line_gen.compareTo(line_ex)==0) {
						succeededRows = numberOfRows;
					}
					else {
						success = false;
						break;
					}	
				}
				
				fr_generated.close();
				fr_expected.close();
				}
				catch(IOException e) {};
			
			return "The result: " + succeededRows + "/" + numberOfRows;
			}
		}
	}
