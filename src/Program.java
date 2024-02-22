/*
 * File: Program.java
 * File Created: Tuesday, 6th February 2024 10:00:12 pm
 * Last Modified: Wednesday, 21st February 2024 7:04:23 pm
 * Author: Tyler Lindsay (tylerl@psu.edu)
 * -----
 * Class: CMPSC 470
 * Instructor: Dr. Hyuntae Na
 */

public class Program {
    public static void main(String[] args) throws Exception
    {
        java.io.Reader r;

        //r = new java.io.StringReader
        //("int main()\n"
        //+"{\n"
        //+"    return 0;\n"
        //+"}\n"
        //);
        //
        //  args = new String[] { "proj1-minic-tokenizer\\src\\test1.minc" };

        if(args.length <= 0)
            return;
        r = new java.io.FileReader(args[0]);

        Compiler compiler = new Compiler(r);
        compiler.Compile();
    }
}
