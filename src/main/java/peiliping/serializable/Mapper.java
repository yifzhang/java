package peiliping.serializable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class Mapper {

    public static void main(String[] args) {
        
        String json = "{\"d\":\"4\",\"b\":\"2\",\"c\":\"3\",\"vg\":\"1\"}";
        
        Gson G = new Gson();
        
        Do d = G.fromJson(json, Do.class);
        
        System.out.println(d.getA());
        System.out.println(d.getB());
        System.out.println(d.getC());
        System.out.println(d.getD());
        
    }

    public class Do {
        @Getter
        @Setter
        @SerializedName("vg")
        private String a;
        @Getter
        @Setter
        private String b;
        @Getter
        @Setter
        private String c;
        @Getter
        @Setter
        private String d;
    }
}
