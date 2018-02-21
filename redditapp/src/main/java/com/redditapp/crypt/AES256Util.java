/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.crypt;

import java.util.Base64;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import com.redditapp.properties.Properties;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author derek
 */
@Named
public class AES256Util {
    @Inject private Properties properties;
    private String key;
        
    public AES256Util() {
        
    }
    
    @PostConstruct
    private void init() {
        key = getKey();
    }
    
    public EncryptedPassword encrypt(String password) {
        final String salt = KeyGenerators.string().generateKey();
        BytesEncryptor encryptor = Encryptors.stronger(key, salt);
        byte[] encryptedBytes = encryptor.encrypt(password.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        return new EncryptedPassword(encryptedText, salt);
    }
    
    public String decrypt(EncryptedPassword encryptedPassword) {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword.getPassword());
        BytesEncryptor decryptor = Encryptors.stronger(key, encryptedPassword.getSalt());
        byte[] decryptedBytes = decryptor.decrypt(encryptedBytes);
        return new String(decryptedBytes);
    }
    
    private String getKey() {
        String hash = null;
        
        try(FileInputStream fis = new FileInputStream(properties.getKeyfileProperties().getKeyfilePath())) {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] dataBytes = new byte[1024];
            int nread = 0;
            while((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
            byte[] mdbytes = md.digest();
            
            //convert bytes to hex format
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<mdbytes.length;i++) {
                sb.append(Integer.toHexString(0xFF & mdbytes[i]));
            }
            hash = sb.toString();
        }
        catch(IOException ex) {
            System.out.println("keyfile not found");
            ex.printStackTrace();
        }
        catch(NoSuchAlgorithmException ex) {
            System.out.println("SHA-512 algorithm not found. Is JCE installed?");
            ex.printStackTrace();
        }
        return hash;
    }
}
