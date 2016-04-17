# StudentsAndTeachers
Programozási Technológia 2 tárgy JDBC-s beadandója (minta)

##Első lépések
### JavaDB elindítása
  + A NetBeans Services ablakában el kell indítani a JavaDB-t, amihez majd kapcsolódni fog az alkalmazás.
    + Services
    + Databases
    + JavaDB jobb klikk
    + Start Server

### 2. Adatbázis létrehozása
  + JavaDB jobb klikk
    + Create Database
    + Ekkor meg kell adni az adatbázis nevét és egy username/password párost
  + Ezeket át kell írni a következő két helyen:
    + hu.elte.progtech2.gyak06.sat.db.dao.DefaultDao 16, 22, 23.sor
    + hu.elte.progtech2.gyak06.sat.db.init.DatabaseInitializer 18, 45, 46.sor
  + Ezek után jobb klikk az adatbázisra majd Connect

### 3. Az alkalmazás futtatható

### 4. JavaDB hibaelhárítás
Amennyiben nem indul el a JavaDB a Start Server utasításra, át kell állítani, hogy a GlassFish Servert használja. Ennek a módja a következő:
  + Tools
  + Servers
  + Add Server
  + GlassFish Server
  + I have read... checkbox kipipálása és Download Now
  + Finish
Ezután át kell állítani, hogy a JavaDB ezt használja
  + JavaDB jobb klikk
  + Java DB Installion-t kell átírni
  + Windows-on tipikusan C:\Users\username\GlassFish_Server\javadb
