package org.himalay.msgs.runtime ;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Created(date = "Fri Aug 24 17:34:37 EDT 2012")
public  class ComplexUI16 extends BinStruct {
    // members
    // value
    int value ;

    public ComplexUI16 () // throws Exception
    {
        init();
    }

    private void init()
    {
        // Initialize value
        
    }


    public int readNoHeader(DataInputStream istream) throws IOException 
    {
        int retVal= 0;
        // read value
        {value=istream.readUnsignedShort(); retVal+=2;}

        return retVal;
    }
    
    public int read(DataInputStream istream) throws IOException 
    {
        int retVal= 0;
        // read value
        {value=istream.readUnsignedShort(); retVal+=2;}

        return retVal;
    }


    public int write(DataOutputStream ostream) throws IOException 
    {
        
    
        int retVal= 0;
        // write value
        ostream.writeShort(value); retVal +=2;

        return retVal;
    }
    
    public int dump(DumpContext dc) throws IOException 
    {
        int retVal= 0;
        dc.increaseIndent();
        // write value
        dc.indent();dc.getPs().println(value+"(0x"+ Integer.toHexString(value)+")") ;
        dc.decreaseIndent();
        return retVal;
    }


        // Getter for value
    public int getValue()
    {
        return value ;
    }

    
    // Setter for value
    public void setValue(int val)
    {
        this.value= val;
    }


    public int getSize() throws IOException
    {
       DataOutputStream dos= new DataOutputStream(new NullStream());
       return this.write(dos);
    }
    
}

// End of code