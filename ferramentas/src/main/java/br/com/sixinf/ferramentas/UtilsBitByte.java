/**
 * 
 */
package br.com.sixinf.ferramentas;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author maicon
 *
 */
public class UtilsBitByte {

	private static final DateFormat JSON_DF = new SimpleDateFormat("Z");

	/**
     * 
     * @param str
     * @param bytes
     * @param offset
     * @return
     */
    public static int indexStringOf(String str, byte[] bytes, int offset) {
        if (offset < 0) {
            return -1;
        }

        int iStr = 0;

        for (int i = offset; i < bytes.length; i++) {
            // se encontrar o primeiro caracter, compara o restante
            if (((char) bytes[i]) == str.charAt(iStr)) {
                boolean encontrou = true;
                for (int j = i; j < (i + str.length()); j++) {
                    if (((char) bytes[j]) != str.charAt(iStr++)) {
                        encontrou = false;
                        iStr = 0;
                        break;
                    }
                }
                if (encontrou) {
                    return i;
                }
            }
        }

        return -1;

    }
    
    /**
     * 
     * @param is
     * @return
     * @throws IOException
     */
    public static byte[] fazLeituraStreamEmByteArray(InputStream is) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[4096];
        int lidos;
        while ((lidos = is.read(buf)) > -1){
            baos.write(buf, 0, lidos);
        }
        baos.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();
        return bytes;
    }
    
    
    /**
     * Converts a 4 byte array of unsigned bytes to an long
     *
     * @param b an array of 4 unsigned bytes
     * @return a long representing the unsigned int
     */
    public static long unsignedIntToUnsignedLongLittleEndian(byte[] b) {
        long l = 0;
        l |= b[3] & 0xFF;
        l <<= 8;
        l |= b[2] & 0xFF;
        l <<= 8;
        l |= b[1] & 0xFF;
        l <<= 8;
        l |= b[0] & 0xFF;
        return l;
    }
    
    /**
     * Converts a 4 byte array of unsigned bytes to an long
     *
     * @param b an array of 4 unsigned bytes
     * @return a long representing the unsigned int
     */
    public static long unsignedIntToUnsignedLongLittleEndian(byte b0, byte b1, byte b2, byte b3) {
        long l = 0;
        l |= b3 & 0xFF;
        l <<= 8;
        l |= b2 & 0xFF;
        l <<= 8;
        l |= b1 & 0xFF;
        l <<= 8;
        l |= b0 & 0xFF;
        return l;
    }

    /**
     * Converts a two byte array to an integer
     *
     * @param b a byte array of length 2
     * @return an int representing the unsigned short
     */
    public static int unsignedShortToUnsignedIntLittleEndian(byte[] b) {
        int i = 0;
        i |= b[1] & 0xFF;
        i <<= 8;
        i |= b[0] & 0xFF;
        return i;
    }
    
    
    /**
     * Converts a two byte array to an integer
     *
     * @param two bytes to int
     * @return an int representing the unsigned short
     */
    public static int unsignedShortToUnsignedIntLittleEndian(byte b0, byte b1) {
        int i = 0;
        i |= b1 & 0xFF;
        i <<= 8;
        i |= b0 & 0xFF;
        return i;
    }
    
    /**
     * Converts a two byte array to an integer
     *
     * @param two bytes to int
     * @return an int representing the unsigned short
     */
    public static short toShortLittleEndian(byte b0, byte b1) {
    	short s = 0;
        s |= b1 & 0xFF;
        s <<= 8;
        s |= b0 & 0xFF;
        return s;
    }
    
    /**
     * Converts a two byte array to an integer
     *
     * @param b - byte
     * @return an int representing the unsigned
     */
    public static short signedByteToUnsignedInt(byte b) {
        short s = 0;
        s |= b & 0xFF;
        return s;
    }
    
    /**
     * Converts a two byte array to an integer
     *
     * @param two bytes to int
     * @return an int representing the unsigned short
     */
    public static int toIntLittleEndian(byte b0, byte b1, byte b2, byte b3) {
    	int i = 0;
        i |= b3 & 0xFF;
        i <<= 8;
        i |= b2 & 0xFF;
        i <<= 8;
        i |= b1 & 0xFF;
        i <<= 8;
        i |= b0 & 0xFF;
        return i;
    }
    
    /**
     * Converts a 6 byte TTime to java Date
     *
     * @param b a byte array of length 6
     * @return a Date representing Time
     */
    public static Date tTimeToJavaDate(byte[] b) {
        int ano = 2000 + signedByteToUnsignedInt(b[5]);
        int mes = signedByteToUnsignedInt(b[4]);
        int dia = signedByteToUnsignedInt(b[3]);
        int hora = signedByteToUnsignedInt(b[2]);
        int minuto = signedByteToUnsignedInt(b[1]);
        int segundo = signedByteToUnsignedInt(b[0]);
        
        Calendar c = GregorianCalendar.getInstance();
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes - 1);
        c.set(Calendar.DAY_OF_MONTH, dia);
        c.set(Calendar.HOUR_OF_DAY, hora);
        c.set(Calendar.MINUTE, minuto);
        c.set(Calendar.SECOND, segundo);
        
        return c.getTime();
    }
    
    /**
     * 
     * @param bytes
     * @return
     */
    public static String converteDadosBinariosParaStringHexa(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        int i = 0;
        while (i < bytes.length) {
            String str = Integer.toHexString(bytes[i++] & 0xFF);
            hex.append(str.length() >= 2 ? str : "0" + str);
        }
        return hex.toString().toUpperCase();
    }

    /**
     * 
     * @param dadosHexa
     * @return
     */
    public static byte[] converterStringHexaParaBinario(String dadosHexa) {
        byte[] bytes = new byte[dadosHexa.length() / 2];
        for (int offset = 0; offset < dadosHexa.length(); offset += 2) {
            Character c1 = dadosHexa.charAt(offset);
            Character c2 = dadosHexa.charAt(offset + 1);
            bytes[offset / 2] = (byte) (((Character.digit(c1, 16) << 4) + Character.digit(c2, 16)) & 0xFF);
        }
        return bytes;
    }
    
    
    public static String toJSonDate(Date date) {    	
		Long timeMillis = date.getTime();		
		
    	return "\\/Date(" + timeMillis + JSON_DF.format(date) +  ")\\/";
    }
    
    /**
	 * 
	 * @param filename
	 * @return
	 */
	public static File tmpFile(String filename) {
		return new File(System.getProperty("java.io.tmpdir"),
				filename != null ? filename : "NetGate.tmp");
	}
		        

	
}
