package org.himalay.msgs.runtime ;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public  class ComplexbyteArrayEOS extends BinStruct {
    // members
    // data
    ByteArray data ;

    public ComplexbyteArrayEOS () // throws Exception
    {
        init();
    }

    private void init()
    {
        // Initialize data
        data= new ByteArray();data.setSizeType("EOS");
    }


    public int readNoHeader(DataInputStream istream) throws IOException 
    {
        int retVal= 0;
        // read data
        {retVal+=data.read(istream); }

        return retVal;
    }
    
    public int read(DataInputStream istream) throws IOException 
    {
        int retVal= 0;
        // read data
        {retVal+=data.read(istream); }

        return retVal;
    }


    public int write(DataOutputStream ostream) throws IOException 
    {
        { /** fix dependent sizes for data**/  }
    
        int retVal= 0;
        // write data
        {retVal += data.write(ostream);}

        return retVal;
    }
    
    public int dump(DumpContext dc) throws IOException 
    {
        dc.indent();dc.getPs().print("ByteArrayWrapper_EOS\n");
    dc.increaseIndent();
        int retVal= 0;
        // write data
        dc.indent();dc.getPs().print("data: "+data.getSize()+"(0x"+Integer.toHexString(data.getSize())+")\n");this.data.dump(dc);
dc.decreaseIndent();
        return retVal;
    }


        // Getter for data
    public ByteArray getData()
    {
        return data ;
    }

    
    // Setter for data
    public void setData(ByteArray val)
    {
        this.data= val;
    }


    public void setData(byte[] val)
    {
        this.data.setData(val);
    }
    
    
    public int getSize() throws IOException
    {
       DataOutputStream dos= new DataOutputStream(new NullStream());
       return this.write(dos);
    }
    
}

// End of code