package resimsifreleme;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class ResimSifreleme {

     public static void main(String[] args) throws IOException {
         // TODO code application logic here
         Random r=new Random();
         
         // Anahtar Kısmı
         
          
         BufferedImage picture = ImageIO.read(new File("Orjinal.jpg"));
          BufferedImage cikti = ImageIO.read(new File("Orjinal.jpg"));
          
        int[][] anahtar=new int[picture.getWidth()][picture.getHeight()];
         for (int i = 0; i < picture.getWidth(); i++) {
             for (int j = 0; j < picture.getHeight(); j++) {
                 float a=r.nextFloat()*1000;
                 anahtar[i][j]=(int)a%255;
             }
         }
         
         
         
         //Binary Çevirme kısmı
         int sayi2=0; 
         int red,green,blue;
         String[][][][] s1=new String[picture.getWidth()][picture.getHeight()][3][1];
         String[][][] s2=new String[picture.getWidth()][picture.getHeight()][1];
         for (int i = 0; i < picture.getWidth(); i++) {
             for (int j = 0; j < picture.getHeight(); j++) {                 
                 red=new Color(picture.getRGB(i, j)).getRed();
                 green=new Color(picture.getRGB(i, j)).getGreen();
                 blue=new Color(picture.getRGB(i, j)).getBlue();
                 sayi2=anahtar[i][j];
                  
                  s1[i][j][0][0]=Integer.toBinaryString(red);
                  s1[i][j][0][0]="00000000000"+s1[i][j][0][0];
                  s1[i][j][1][0]=Integer.toBinaryString(green);
                  s1[i][j][1][0]="00000000000"+s1[i][j][1][0];
                  s1[i][j][2][0]=Integer.toBinaryString(blue);
                  s1[i][j][2][0]="00000000000"+s1[i][j][2][0];
                  s2[i][j][0]=Integer.toBinaryString(sayi2);
                  s2[i][j][0]="00000000000"+s2[i][j][0];
             } 
             
             
         } 
         //Karşılaştırma 
         StringBuilder sd=new StringBuilder(""); 
          for (int i = 0; i < picture.getWidth(); i++) {
              for (int j = 0; j < picture.getHeight(); j++) {
                  for (int l = 0; l < 3; l++) {
                      for (int k = 0; k < 8; k++) {
                          if(s2[i][j][0].charAt((s2[i][j][0].length()-1)-k)==s1[i][j][l][0].charAt((s1[i][j][l][0].length()-1)-k))
                          {
                              sd=new StringBuilder(s1[i][j][l][0]);
                              sd.setCharAt(s1[i][j][l][0].length()-k-1,'0');
                              s1[i][j][l][0]=sd.toString();
                          }
                          else{
                          
                              sd=new StringBuilder(s1[i][j][l][0]);
                              sd.setCharAt(s1[i][j][l][0].length()-k-1,'1');
                              s1[i][j][l][0]=sd.toString();
                          }
                          
                      }
                  }
              }
         }
           
          //binaryden int çevirme
          for (int i = 0; i < picture.getWidth(); i++) {
             for (int j = 0; j < picture.getHeight(); j++) {                 
                  
                  red=Integer.parseInt(s1[i][j][0][0],2); 
                  green=Integer.parseInt(s1[i][j][1][0],2);
                  blue=Integer.parseInt(s1[i][j][2][0],2);
                 cikti.setRGB(i, j, new Color(red,green,blue).getRGB());
             } 
             
         }
         
         //Yazdırma Kısmı
       
         ImageIO.write(cikti,"jpg",new File("sifreli.jpg"));     
    }   
}