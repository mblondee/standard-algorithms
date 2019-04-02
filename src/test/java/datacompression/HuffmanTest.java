package datacompression;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.BitSet;

import static org.junit.Assert.assertEquals;

public class HuffmanTest {

    @Test
    public void test(){

        String testString = "ABRACADABRA!";
        Huffman huffmanIt = new Huffman(testString);
        assertEquals("0111110010110100011111001010", huffmanIt.compress());
        assertEquals(testString, huffmanIt.decompress("0111110010110100011111001010"));

        System.out.println("input bits per character: " + 8);
        System.out.println("decoded bits per character: " + huffmanIt.compress().length()/testString.length());


        String testString1 = "Is this ok?";
        Huffman huffmanIt1 = new Huffman(testString1);
        assertEquals(testString1, huffmanIt1.decompress(new Huffman(testString1).compress()));



    }

    @Test
    public void anderetest(){
        String testString = "ABRACADABRA!";
        Huffman huffmanIt = new Huffman(testString);
        for(byte b: huffmanIt.compressToByte()){
         System.out.println(String.format("%8s", Integer.toBinaryString(b)).replace(" ", "0"));
        }

        try {
            FileOutputStream os = new FileOutputStream(new File("/src/main/resources/huffman.ambetant"));
            os.write(huffmanIt.compressToByte());
            os.close();

            System.out.println("Reading file");

            FileInputStream is = new FileInputStream(new File("/src/main/resources/huffman.ambetant"));
            byte b = (byte) is.read();
            while(b != -1) {
                System.out.println(String.format("%8s", Integer.toBinaryString(b)).replace(" ", "0"));
                b = (byte)is.read();
            }
            is.close();
        } catch (Exception e) {
            System.out.println("lalalalallaa");
        }


    }

    @Test
    public void test1(){
        String test = "0111110010110100011111001010";
        System.out.println(test.length());
        BitSet bs = new BitSet(test.length());
        for(int i = 0; i<test.length(); i++){
            bs.set(i, test.charAt(i) == '1');
        }
        System.out.println(bs);
        for(Byte b : bs.toByteArray()) {
            System.out.println(Integer.toBinaryString(b));
        }
    }

}
