/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informationretrieval;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author user
 * Nama : Nabila Febriyanti
 * NIM : 09021281823071
 */
public class UtsInformationRetrieval {
    
    public static String[] extractDictionary(String path) {
        File f = new File(path);
        List<String> list = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String text;
            while ((text = br.readLine()) != null){
                list.add(text);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        String[] result = new String[list.size()];
        list.toArray(result);
        return result;
    }
    
    public static String textCleaning(String raw) {
        String result = raw.replaceAll("[^a-zA-Z ]", ""); // menghilangkan angka dan simbol
        result = result.replaceAll("\\s{2,}", " "); // menghilangkan whitespaces
        return result;
    }
    
    public static String[] textTokenize(String text) {
        return text.split(" ");
    }
    
    public static String[] tokenstopwordRemoval(String[] tokens) {
        String stopw_path = "C:\\Users\\user\\Documents\\ID-Stopwords-master\\id.stopwords.02.01.2016.txt";
        String[] stopwords = extractDictionary(stopw_path);
        List<String> list = new ArrayList<String>();
        for (String token : tokens) {
            if(!(Arrays.asList(stopwords).contains(token))) {
                list.add(token);
            }
        }
        String[] result = new String[list.size()];
        list.toArray(result);
        return result;
    }
    
    public static String[] tokenStemming(String[] tokens) { //stemmer Tala berbasis aturan (rule based)
        List<String> list = new ArrayList<String>();
        String path = "C:\\Users\\user\\Documents\\ID-Stopwords-master\\kata-dasar.txt";
        String[] dictionary = extractDictionary(path);
        for (String token : tokens) {
            if(!(Arrays.asList(dictionary).contains(token))) {
                // penghilangan kata sandang
                token = token.replaceAll("lah$", ""); // cluster 1
                token = token.replaceAll("kah$", "");
                token = token.replaceAll("pun$", "");
                token = token.replaceAll("nya$", ""); // cluster 2
                token = token.replaceAll("ku$", "");
                token = token.replaceAll("mu$", "");
                if (!(Arrays.asList(dictionary).contains(token))) {
                    String n_token = token;
                    // penghilangan imbuhan depan / awalan 1
                    n_token = n_token.replaceAll("^meng", ""); // cluster 3
                    n_token = n_token.replaceAll("^menya", "s");
                    n_token = n_token.replaceAll("^menyi", "s");
                    n_token = n_token.replaceAll("^menyu", "s");
                    n_token = n_token.replaceAll("^menye", "s");
                    n_token = n_token.replaceAll("^menyo", "s");
                    n_token = n_token.replaceAll("^meny", "s");
                    n_token = n_token.replaceAll("^men", "");
                    n_token = n_token.replaceAll("^mema", "p");
                    n_token = n_token.replaceAll("^memi", "p");
                    n_token = n_token.replaceAll("^memu", "p");
                    n_token = n_token.replaceAll("^meme", "p");
                    n_token = n_token.replaceAll("^memo", "p");
                    n_token = n_token.replaceAll("^mem", "");
                    n_token = n_token.replaceAll("^me", "");
                    n_token = n_token.replaceAll("^peng", "");
                    if(!("penyakit".equals(n_token))) {
                        n_token = n_token.replaceAll("^penya", "s");
                    } 
                    n_token = n_token.replaceAll("^penyi", "s");
                    n_token = n_token.replaceAll("^penyu", "s");
                    n_token = n_token.replaceAll("^penye", "s");
                    n_token = n_token.replaceAll("^penyo", "s");
                    n_token = n_token.replaceAll("^peny", "s");
                    n_token = n_token.replaceAll("^pen", "");
                    n_token = n_token.replaceAll("^pema", "p");
                    n_token = n_token.replaceAll("^pemi", "p");
                    n_token = n_token.replaceAll("^pemu", "p");
                    n_token = n_token.replaceAll("^peme", "p");
                    n_token = n_token.replaceAll("^pemo", "p");
                    n_token = n_token.replaceAll("^pem", "");
                    n_token = n_token.replaceAll("^di", "");
                    n_token = n_token.replaceAll("^ter", "");
                    n_token = n_token.replaceAll("^ke", "");

                    if(n_token.equals(token) && (!(Arrays.asList(dictionary).contains(token)))) { // tidak ada perubahan awalan 1
                        // menghilangkan awalan 2
                        token = token.replaceAll("^ber", "");
                        token = token.replaceAll("^bel", "");
                        token = token.replaceAll("^be", "");
                        token = token.replaceAll("^per", "");
                        token = token.replaceAll("^pel", "");
                        token = token.replaceAll("^pe", "");

                        // menghilangkan akhiran
                        if (!("ternakan".equals(token))) {
                            token = token.replaceAll("kan$", "");
                        }
                        token = token.replaceAll("an$", "");

                        if (!("tani".equals(token))) {
                            token = token.replaceAll("i$", "");
                        }
                    }
                    else{
                        if(!(Arrays.asList(dictionary).contains(token))) {
                            token=n_token;
                            // menghilangkan akhiran
                            if (!("ternakan".equals(token))) {
                                token = token.replaceAll("kan$", "");
                            }
                            token = token.replaceAll("an$", "");

                            if (!("tani".equals(token))) {
                                token = token.replaceAll("i$", "");
                            }

                            if((!(token.equals(n_token)))  && (!(Arrays.asList(dictionary).contains(token)))) { // ada perubahan
                                // menghilangkan awalan 2
                                token = token.replaceAll("^ber", "");
                                token = token.replaceAll("^bel", "");
                                token = token.replaceAll("^be", "");
                                token = token.replaceAll("^per", "");
                                token = token.replaceAll("^pel", "");
                                token = token.replaceAll("^pe", "");
                            }
                        }
                    }
                }
            }
            
            list.add(token);
        }
        String[] result = new String[list.size()];
        list.toArray(result);
        return result;
    }
    
    public static void main(String[] args) {
        /*Deskripsi Program
        * Program ini melakukan pra-pemrosesan teks dengan tahapan sebagai berikut:
        * Cleaning (whitespaces and symbols), case folding, tokenisasi, stopword removal dan stemming
        * Program ini menerima teks berbentuk String dan memberikan hasil akhir berupa token
        */
        System.out.println("Nabila Febriyanti (NIM 09021281823071) UTS Information Retrieval");
        String doc_path = "C:\\Users\\user\\Documents\\dokumen.txt";
        File doc = new File(doc_path);
        String docs=  "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(doc));
            String text;
            while((text = br.readLine()) != null) {
                docs+=(text);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        System.out.println("Teks Mentah / Raw\n"+docs+"\n");
        String clean_text = textCleaning(docs); // membersihkan teks
        System.out.println("Teks bersih dari simbol dan angka\n"+clean_text+"\n");
        clean_text = clean_text.toLowerCase(); // menyamakan huruf
        System.out.println("Lowercased\n"+clean_text+"\n");
        
        String[] tokens = textTokenize(clean_text); // tokenisasi
        for (String token : tokens) {
            System.out.print("\'"+token+"\'"+", ");
        }
        
        System.out.println("\n\nStopword removed"); // menghilangkan stopword
        String[] sw_removed = tokenstopwordRemoval(tokens);
        for (String token : sw_removed) {
            System.out.print("\'"+token+"\'"+", ");
        }
        
        System.out.println("\n\nStemmed"); // menarik kata dasar
        String[] stemmed = tokenStemming(sw_removed);
        for (String token : stemmed) {
            System.out.print("\'"+token+"\'"+", ");
        }
    }
}
