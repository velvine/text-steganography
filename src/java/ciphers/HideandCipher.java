/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ciphers;

import java.util.Random;

/**
 *
 * @author user
 */
public class HideandCipher {
    public  String seekMessage(String s)
    {
        int i, j;
        String word[] = s.split(" ");
        BasicOperation b1 = new BasicOperation();
        String bin;
        bin = "";
        for (i = 0; i < word.length; i++)
        {
            if(word[i].equals("")){
                bin += " ";
                System.out.println(" ");
            }
            else{
                bin =bin+(char)b1.binaryToDecimal(Integer.parseInt(word[i]));
//                System.out.println((char)b1.binaryToDecimal(Integer.parseInt(word[i])));
            }
        }
        return (bin);
    }
   public  String hidingMessageBin(String s)
    {
        int i, j;
        char asc[] = new char[s.length()];
        for (i = 0; i < s.length(); i++)
        {
            asc[i] = (char) ((int) s.charAt(i));
            
        }
        BasicOperation b1 = new BasicOperation();
        String bin;
        bin = "";
        for (i = 0; i < asc.length; i++)
        {
            
            int temp = (int) (asc[i]);
            int len = b1.decimalToBinary(temp);
            if (len == 7 || (len == 6 && temp != 32))
            {
                for (j = 0; j <= len; j++)
                {
                    bin = bin + b1.binaryArrayAtPosition(j);
                }
            }
            bin += " ";
        }
        return (bin);
    }
     public  String StegSeek(String key,String filec){
        int inputlen = key.length(); 
        String word[] = filec.split(" ");
        String sk="";
        int wordlen=word.length;
        int currentword=0;
        for(int n = 0; n < inputlen ; n++){
            char x=key.charAt(n);
            char b;
            if(getcurrentword(currentword,word)!=-1){
                int nowcurrentw=getcurrentword(currentword,word);
                if(getcurrentword(nowcurrentw,word)!=-1){
                    currentword=getcurrentword(nowcurrentw,word);
                }else{
                    currentword=nowcurrentw;
                }
            }
            if(getseekfilebin(currentword,word,x)=='1' || getseekfilebin(currentword,word,x)=='0' || getseekfilebin(currentword,word,x)==' '){
                b=getseekfilebin(currentword,word,x);
                sk=sk+b;
            }else{
                currentword = (currentword==wordlen-1) ? (0) : (currentword+1);
                if(getseekfilebin(currentword,word,x)=='1' || getseekfilebin(currentword,word,x)=='0' || getseekfilebin(currentword,word,x)==' '){
                    b=getseekfilebin(currentword,word,x);
                        sk=sk+b;
                }
            }   
            currentword = (currentword==wordlen-1) ? (0) : (currentword+1);
        }
         return sk;
   }
    public  String StegKey(String input,String cover){
        int inputlen = input.length(); 
        String word[] = cover.split(" ");
        String sk="";
        int wordlen=word.length;
//        System.out.println("SK="+wordlen);
        int currentword=0;
        for(int n = 0; n < inputlen ; n++){
            char x=input.charAt(n);
            if(getcurrentword(currentword,word)!=-1){
                int nowcurrentw=getcurrentword(currentword,word);
                if(getcurrentword(nowcurrentw,word)!=-1){
                    currentword=getcurrentword(nowcurrentw,word);
                }else{
                    currentword=nowcurrentw;
                }
            }
            char s=word[currentword].charAt(0);
            char e=word[currentword].charAt(word[currentword].length()-1);
            if(e==',' || e=='.' || e=='?' || e=='!'){
                e=word[currentword].charAt(word[currentword].length()-2);
            }
            if(x=='0'){
                sk=sk+s;
            }
            else if(x=='1'){
                sk=sk+e;
            }
            else{
                if(x==' '){
                    sk=sk+' ';
                }
                else{
                    currentword = (currentword==wordlen-1) ? (0) : (currentword+1);
                }
            }
            currentword = (currentword==wordlen-1) ? (0) : (currentword+1);
        }
         return sk;
   }
    public  int getcurrentword(int currentw,String word[] ){
        int currentword=currentw;
        int wordlen=word.length;
        char ss=word[currentword].charAt(0);
        char ee=word[currentword].charAt(word[currentword].length()-1);
        if(ee==',' || ee=='.' || ee=='?' || ee=='!'){
            ee=word[currentword].charAt(word[currentword].length()-2);
        }
            if(ss==ee){
                currentword = (currentword==wordlen-1) ? (0) : (currentword+1);
                return currentword;
            }
            else{
                return -1;
            }
    }
    //This method generates a binary seek text
    public  char getseekfilebin(int currentw,String[] word,char x){
        int currentword=currentw;
        char b;
        char s=word[currentword].charAt(0);
        char e=word[currentword].charAt(word[currentword].length()-1);
        if(e==',' || e=='.' || e=='?' || e=='!'){
            e=word[currentword].charAt(word[currentword].length()-2);
        }
        else{
            e=word[currentword].charAt(word[currentword].length()-1);
        }
        if(x==s){
            b='0';
        }
        else if(x==e){
            b='1';
        }
        else{
            b=x;
        }
        return b;
    }
    //This method generates a random characters for OTP
    public  String RandomAlpha(int len){
      Random r = new Random();
      String key = "";
      for(int x=0;x<len;x++)
         key = key + (char) (r.nextInt(26) + 'A');
      return key;
   }
   //This Method takes a stego plain text with the encryption key and generates a cipher text 
   public  String OTPEncryption(String text,String key){
      String alphaU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      String alphaL = "abcdefghijklmnopqrstuvwxyz";
      
      int len = text.length();
      
      String sb = "";
      for(int x=0;x<len;x++){
         char get = text.charAt(x);
         char keyget = key.charAt(x);
         if(Character.isUpperCase(get)){
            int index = alphaU.indexOf(get);
            int keydex = alphaU.indexOf(Character.toUpperCase(keyget));
            
            int total = (index + keydex) % 26;
            
            sb = sb+ alphaU.charAt(total);
         }
         else if(Character.isLowerCase(get)){
            int index = alphaL.indexOf(get);
            int keydex = alphaL.indexOf(Character.toLowerCase(keyget));
            
            int total = (index + keydex) % 26;
            
            sb = sb+ alphaL.charAt(total);
         }
         else{
            sb = sb + get;
         }
      }
      
      return sb;
   }
   //This Method takes a stego encrypted text with the decryption key and generates a plain text 
    public  String OTPDecryption(String text,String key){
      String alphaU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      String alphaL = "abcdefghijklmnopqrstuvwxyz";
      
      int len = text.length();
      
      String sb = "";
      for(int x=0;x<len;x++){
         char get = text.charAt(x);
         char keyget = key.charAt(x);
         if(Character.isUpperCase(get)){
            int index = alphaU.indexOf(get);
            int keydex = alphaU.indexOf(Character.toUpperCase(keyget));

            int total = (index - keydex) % 26;
            total = (total<0)? total + 26 : total;
            
            sb = sb+ alphaU.charAt(total);
         }
         else if(Character.isLowerCase(get)){
            int index = alphaL.indexOf(get);
            int keydex = alphaL.indexOf(Character.toLowerCase(keyget));
            
            int total = (index - keydex) % 26;
            total = (total<0)? total + 26 : total;
            
            sb = sb+ alphaL.charAt(total);
         }
         else{
            sb = sb + get;
         }
      }
      return sb;
   }
}
