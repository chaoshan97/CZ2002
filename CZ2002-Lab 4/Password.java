import java.security.MessageDigest;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
class Password implements Serializable{
    // association with username
    // encryption and checking takes place
    private String hashed_pass;
    // primary key
    private String username;
    // domain = 1 for student and domain = 0 for admin
    private int domain;

    public Password(){ this.hashed_pass = ""; this.username = "";}


    public Password(String username, String raw, int domain){
        this.username = username;
        this.hashed_pass = hash(raw);
        this.domain = domain;

    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.hashed_pass = hash(password);
    }

    public void setDomain(int domain){
        this.domain = domain;
    }

    public int getDomain(){
        return this.domain;
    }

    public String getPassword(){
        return this.hashed_pass;
    }

    public String getUsername(){
        return this.username;
    }

    private String hash(String raw){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(raw.getBytes(StandardCharsets.UTF_8));
            return (bytesToHex(encodedhash));
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }


    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    // can use it for polymorphism and LSP
    @Override
    public boolean equals(Object p1){
        Password p2 = (Password) p1;
        if (this.hashed_pass.compareTo(p2.getPassword()) == 0 && (this.username.toUpperCase()).compareTo(p2.getUsername().toUpperCase())==0 && this.domain == p2.getDomain())
            return true;
        else
            return false;
    }

}