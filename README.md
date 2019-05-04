# Voizilla - próbka kodu z wykorzystaniem vozilla
## [PLIK APK](https://drive.google.com/open?id=1oJ7ocWDju-9rI9dDCOLuFztlYgQn6gv1)

### Architektura i język
Wykorzystany został wzorzec MVP z uwagi na łatwe testowanie i możliwość łatwej implementacji funkcji do istniejącego projektu.   
Tylko klient został wygenerowany w javie, reszta kodu jest napisana w kotlinie.

### Klient Restowy
Klient został wygenerowany z wykorzystaniem [OpenAPI](https://github.com/OAI/OpenAPI-Specification) w konfiguracji z [Retrofit2](http://square.github.io/retrofit/) i [RXJava2](https://github.com/ReactiveX/RxJava)

### Ekran główny
![Ekran główny](https://lh3.googleusercontent.com/N4pKHmtko-lmCnkd49AwKJ5DehI3ls3apPbSu8ixFKe0H4lEA0rvlGTPfspGqsx736RTVgyPeJyKG487tT93KP26K9sUvq8iDlwZTyujR1_djeKrwyUGRTv7MccLcfc-MeB727OZ7-5ajLzhS3temfafrO8yd_Rrb1ZIyKsXTMJt7Bj10zxhwxzeeIVGhRE5TjYy5ndcmgyAc71DDDVWbwZi_28Ggim86Cb1FbMZ23BG-Qaehyq8kxqz1FgoGRBgPh4wkW0LO4ipEqwnb5egvZ0PLskv_G00PykIeuzy4SUFU5jEEF0xi3enEIGYqXwRPWMRBoQv1h26-WgavDc56PiumKAkpZHP_-SB1YvR3-iEMPPjCnNoY2bWFv8pFRgOb_sGVR8Whv59eTUAZ0gGwx78gIedS6WVV3m4aLp0qpMcy7PC3Ene2ZSxUA64aW6lPjwaLH478Rh3YqMzJHIBfnvL0UddsNLTZlzvNKbVaTDczXxwyk6f4fVcuH0CIyC5XpPeNPG-w1leqe_ie0C57ioDsTPHe3YEQduE4Fmzki4rqIXKUTDxLbknYs-VsUc3imwc6jnbm3-X87O1YIDBDsO_xZZmzgkV5D14jXaqEzgIycgXBD9bFZ9lWch7WrItR_ReAM9PJDADAK6VMtuHcvm8Rs1PnWWJ=w554-h983-no)   
Z uwzględnieniem filtrów na mapie wyświetlają się obiekty:
1. Vehicle
  * Marker zielony - AVAILABLE
  * Marker czerwony - pozostałe
2. Charger - fioletowy marker
3. Zone
  * niebieskie - dozwolone
  * czerwone - wykluczone
4. POI - żółty marker
5. Parking - niebieski marker z napisaną liczbą miejsc dostępnych/liczbą wszystkich miejsc

Każdy marker ma swój tytuł będący opisem lub nazwą obiektu

### Ekran filtrów
![Ekran filtrów](https://lh3.googleusercontent.com/L6b9On6Mh8-Y6G6Sve7MgUPrt_RPEYsZG2VMXNKFhA5R74goChdySllgJmkDaFC4_0mq3cr90tGrzN9ArXl8XztH6cwJmFyfG_cVti2W_96b3kkfZKx_ZeqoYLyj4_qSTJs_1pQFwxJmht42Ci4SbviBUF7aSHR2ivH_XhiurG4_kXXvISE2UgTvwLA8I36wj6ZABtwAUtBHrJr1qeewccnHNaqiwWpnAtWnHWkm0VmVCvU5769acNRI_gQXrggldL3m-RghXpXWTH52rYhQ-2HJI3zsUO1MmD1qoZ7xyWEuI2_G5mcbWbt4TKrr-d-gNWj26oin6f67xiwHqaNMIyb6kXTgO7sEjbkNTGk-DvqLBWyV2ETE9chthc1sSEbJgwOseJfBeLDkWpmUcPBcyxaFHYQ1vdlUQ4yYnOa-viTx5xZoo6qx2lxiHtA-vafbny-IQOl6-hXjfXFYOS4aPVLannuJsyIBWLKfs7lYAL6KXPkl9sJ3cBivuGmOQjPzQA5GrzwZIcr8C1pDPhowWWsXeAzmyDTLY9Vvj9hLlqrh_Jzx_-6JCmd3OQhlcICokxzlap3WLIKuZbtyFLBNA6tdefcYSWSusQn-q78ZZmGxiisEI35H7aOXOus4sehPptsVG84IHi476kX4dH8ZeG_ywATgfuIW=w554-h983-no)   
Ekran filtrów pozwala na konfigurację wszystkich filtrów z /map/filters   
Filtry użytkownika przechowywane są za pomocą SharedPreferences.   
Po wyjściu z ekranu filtrów klient wysyła zapytanie do API z już zmienionymi filtrami.

### Ekran wybranego pojazdu
![Ekran pojazdu](https://lh3.googleusercontent.com/qHDv50Af-yuHFIVoN8fGMDtV3r-ttNc4TV0_6K6ikS5iW4FkAL1YMecEuVghgHHVnpSg3ReaMEeJxoSK9_Q7JECNitKjDdvK8XhqX7VOK9Y84zAmbBezKd_RKyj9SCn1romadtijTuXoRRDvCLw5_pypLEr_ZsSmTExIp5t7CROiMePTtDviw8jbJZ2XtGz_k_SnoyBUCzCgIU5bJpvQZati2S69or94GdbGNgFjIfgv9IlSQmeuwVXb4zKjLQMOLltK8fiNbx0QyK8dT0xAfi70_KqzdQemHOmi05BKydy7lmB8ynN4grWpLIu-dNMZTD2z5E71lCYL0xnf8oYNmUPW6Iy8sUZzzYRYN4C7a9iHc5SBIy_g09oJuRhxsw4rshVUsfLrkGcUO53QC2EbkLWt8fJAx1pSBvRKA8JmNEH2sU412Gf8VzJRVRJn-vKYWrH01wjn1L2Wqc180A0j5P9icszeelRGQa2oR_aJYEX0xs27t-D74n3aq73BbNf_KIT_75YtfAjm99-dbxG7sBMu-QfZJ-l6JwQpLVSnLIXZSTKMwBy-nPqjEjpxM080YP0xJgDVDC31hNZhgCcuJK12Du4i44Sao-9-W583F0jRpOcr4AtxAm-VjPWZyWjU2lzdnMfFhzaLpZQWYAv7XbD7mJ9vV-T5=w554-h983-no)   
Z ekranu głównego można przejść na ekran pojazdu klikając na marker pojazdu.   
Ekran wyświetla dane z obiektu Vehicle i za pomocą [Picasso](http://square.github.io/picasso/) wyświetla zdjęcie pojazdu

### Testy
Testy napisane zostały przy pomocy [Mockito](http://site.mockito.org/)  
Testy polegają na sprawdzeniu komunikacji pomiędzy presenterem a widokiem

### Walidacja wejścia i brak połączenia z internetem
Jeżeli widok przekazuje do prezentera zaznaczenie filtru lub naciśnięcie na obiekt, o których prezenter nie wie, wtedy widok zostaje o tym powiadomiony.   
W razie braku połączenia z internetem użytkownik zostaje o tym powiadomiony Snackbarem i może pośrednio znów wysłać request klikając na przycisk na Snackbarze.


### Logowanie przy pomocy Firebase - [Crashlytics](https://firebase.google.com/docs/crashlytics/)
![Crashlytics](https://lh3.googleusercontent.com/qZGleuEI0o46gqW3k2j-dAHOveGgifvSwIdCOlCbSunvpB7qmmovEATuCjnNmoJGCz17n9EP4zcjjYYwDwXAJIYq1DYwUFIAwwdY0bBRCmHSW6Nk2cSVr6crfKidaUIwoUhbQIE9OJHqzPUQTtr69FNlZSql7bpItCrCwoSUFRddqORiGxW0Epj3WHbl_p9pByPVwPbI1_16bUCgE1LaTnNZSdmMHCnA25JPXWrhy10B8jPbFtjzbeEWsnNEPwkOEBUGBK3KA2iOOnEBF8FrE6N_B7aaw-pTAXB9ej87vlEFMpQN4D2HoGAcvMYq5l6sa3d1xBzcpy6BZEusNOT8PCeF_iJReiYXNFPQcilU-k5hGh35EUkJqi7nPi4G7PjmNh4HG0jMsgL1C9IeYTOW1KK2Vsy3Ze62PAF5bmwQQ3jGBtPZB-6aQDgfoYfBs0WvPm8a9dImM1cibfGfa6c5021aMf3Qne0F2TfRTRUgbMrnKiaySRqPceGEuaB0kZWXTuxfQHpI287NgOsfUzBk6wn73aq1k12MMdbdUQ1JUYBf7tUXEG5o0eGERKxokLejk-jYVrSMba5WzgqWzcRfzVFDE2o2qPDfFOX5XbpaTix-RMb6FRy3ZTclQmHS0H7TkdeTfdPhl3HdnzmCIGLhr6PU5xev5zyw=w1317-h692-no)
Do aplikacji zaimplemntowane zostało crashlytics aby logować crashe aplikacji. Przycisk CRASH w głównym menu wywołuje wysypanie się aplikacji, które widać w firebase