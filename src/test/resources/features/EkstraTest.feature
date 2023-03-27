Feature: HomePage searchbox'tan arama islemleri
@beymen
  Scenario: Kullanıcı bir ürün araması yaptıktan sonra ürün üzerinde hover ederek
  beden secimi yapmadan sepete ekle butonuna tiklarsa "Beden Seçiniz" yazısı gözükmeli

    * beymen sitesi acilir
    * anasayfanin acildigi kontrol edilir
    * arama kutucuguna gomlek kelimesi girilir ve entere basilir
    * rastgele bir urun uzerinde beklenir
    * sepete ekle butonuna tiklanir
    * beden seciniz uyarisinin gorundugu dogrulanir