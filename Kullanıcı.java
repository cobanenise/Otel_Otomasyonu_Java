
package otelotomasyonu;


public class Kullanıcı {
    private boolean durum;
    private String kullanıcıadi;
    private KullaniciGiris sonraki_islem;
    
    
    
    public void KullaniciGiris (String kulllanıcıadi){
        this.kullanıcıadi=kullanıcıadi;
    }
    public void Giriscevapla(){
        if(durum)
            System.out.println("otelotomasyonu.Kullanıcı.Giriscevapla()");
            }
  //  System.out.println(kullanıcıadı+"Onaylandı");

    public boolean isDurum() {
        return durum;
    }

    public void setDurum(boolean durum) {
        this.durum = durum;
    }

    public String getKullanıcıadi() {
     
        return kullanıcıadi;
    }

    public void setKullanıcıadi(String kullanıcıadi) {
        this.kullanıcıadi = kullanıcıadi;
    }

    public KullaniciGiris getSonraki_islem() {
        return sonraki_islem;
    }

    public void setSonraki_islem(KullaniciGiris sonraki_islem) {
        this.sonraki_islem = sonraki_islem;
    }
    
    
    
    
}
