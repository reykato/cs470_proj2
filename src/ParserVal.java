/*
 * File: ParserVal.java
 * File Created: Monday, 5th February 2024 1:05:10 pm
 * Last Modified: Wednesday, 21st February 2024 7:04:05 pm
 * Author: Tyler Lindsay (tylerl@psu.edu)
 * -----
 * Class: CMPSC 470
 * Instructor: Dr. Hyuntae Na
 */

public class ParserVal
{
    public int ival;
    public double dval;
    public String sval;
    public Object obj;

    public ParserVal()
    {
    }
    public ParserVal(int val)
    {
      ival=val;
    }
    public ParserVal(double val)
    {
      dval=val;
    }
    public ParserVal(String val)
    {
      sval=val;
    }
    public ParserVal(Object val)
    {
      obj=val;
    }
}
