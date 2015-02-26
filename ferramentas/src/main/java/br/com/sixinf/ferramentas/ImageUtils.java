/**
 * 
 */
package br.com.sixinf.ferramentas;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.log4j.Logger;

/**
 * @author maicon
 *
 */
public class ImageUtils {
	
	public static void gravaImagemComTamanhoCorrigido(
			InputStream imageInputStream, int comprimentoMaximo, int alturaMaxima, File arquivoDestino) 
					throws IOException {
		
		if (!arquivoDestino.exists())
        	arquivoDestino.createNewFile();
		
		FileImageOutputStream imageOutput = null;		
        try {
        	
        	imageOutput = new FileImageOutputStream(arquivoDestino);
        	
        	BufferedImage originalImage = ImageIO.read(imageInputStream);
        	
        	if (originalImage.getHeight() > alturaMaxima ||
        			originalImage.getWidth() > comprimentoMaximo) {
        		
	            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
	            
	            BufferedImage resizeImageJpg = 
	            		resizeImage(originalImage, type, comprimentoMaximo, alturaMaxima);
	            
	            ByteArrayOutputStream os = new ByteArrayOutputStream();
	            ImageIO.write(resizeImageJpg, "jpg", os);
	            
	            imageOutput.write(os.toByteArray());
        	} else {
        		ByteArrayOutputStream os = new ByteArrayOutputStream();
	            ImageIO.write(originalImage, "jpg", os);
        		imageOutput.write(os.toByteArray());
        	}
            
            imageOutput.close();
            
        } catch (Exception e) {
        	Logger.getLogger(ImageUtils.class).error("Erro ao fazer conversão do arquivo de imagem", e);            
        }
	}
	
	/**
	 * 	 
	 * @param originalImage
	 * @param type
	 * @param comprimentoMaximo
	 * @param alturaMaxima
	 * @return
	 */
	private static BufferedImage resizeImage(BufferedImage originalImage, int type, 
			int comprimentoMaximo, int alturaMaxima) {
		
        BufferedImage resizedImage = new BufferedImage(
        		comprimentoMaximo, alturaMaxima, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, comprimentoMaximo, alturaMaxima, null);
        g.dispose();
 
        return resizedImage;
    }

}
