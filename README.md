# Painting Competition - Core Criptográfico

Proyecto base con **Arquitectura Hexagonal (Puertos y Adaptadores)** y **Maven** para el sistema del concurso de pintura con privacidad, no repudio y confidencialidad.

---

## Estructura de Directorios

```
paintingCompetition/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
                └── paintingcompetition/
                    ├── Main.java                          <-- Punto de entrada de la aplicación
                    ├── domain/                            <-- CAPA DE DOMINIO (Reglas de negocio puras)
                    │   ├── model/
                    │   │   ├── Obra.java                  <-- Entidad Obra
                    │   │   ├── Usuario.java               <-- Entidad Usuario
                    │   │   └── RolUsuario.java            <-- Enum PINTOR, JUEZ, ORGANIZADOR
                    │   └── ports/
                    │       └── out/
                    │           └── MotorCriptograficoPort.java <-- Interfaz puerto de salida
                    └── infrastructure/                    <-- CAPA DE INFRAESTRUCTURA (Detalles técnicos)
                        └── adapters/
                            ├── in/
                            │   └── console/
                            │       └── MenuPrincipalConsola.java <-- Adaptador entrada (CLI / Scanner)
                            └── out/
                                └── crypto/                <-- Adaptadores salida (Cajas negras del equipo)
                                    ├── ModuloConfidencialidadAES.java  <-- Integrante 1 (AES-GCM / ECDH)
                                    ├── ModuloRegistroECDSA.java        <-- Integrante 2 (ECDSA / Firmas)
                                    └── ModuloAnonimatoRSA.java         <-- Integrante 3 (Firma Ciega RSA)
```

---

## División del Trabajo (3 Integrantes)

###  Integrante 1: Confidencialidad y Acuerdo de Claves
* **Archivo:** `ModuloConfidencialidadAES.java`
* **Tecnologías:** AES en modo GCM (Galois/Counter Mode) y ECDH (Elliptic Curve Diffie-Hellman).
* **Tareas / Métodos a implementar:**
  - `generarClaveAES(int longitudBits)`
  - `generarIV(int longitudBytes)`
  - `cifrarAES_GCM(byte[] datosEnClaro, SecretKey claveSecreta, byte[] iv)`
  - `descifrarAES_GCM(byte[] datosCifrados, SecretKey claveSecreta, byte[] iv)`
  - `generarParLlavesECDH()`
  - `derivarClaveCompartidaECDH(PrivateKey clavePrivadaLocal, PublicKey clavePublicaRemota)`

---

###  Integrante 2: Registro, Identidad y No Repudio
* **Archivo:** `ModuloRegistroECDSA.java`
* **Tecnologías:** ECDSA (Elliptic Curve Digital Signature Algorithm) con SHA-256.
* **Tareas / Métodos a implementar:**
  - `generarParLlavesECDSA()`
  - `firmarECDSA(byte[] mensaje, PrivateKey clavePrivada)`
  - `verificarFirmaECDSA(byte[] mensaje, byte[] firma, PublicKey clavePublica)`

---

###  Integrante 3: Privacidad y Anonimato de Obras
* **Archivo:** `ModuloAnonimatoRSA.java`
* **Tecnologías:** Protocolo de Firma Ciega (Blind Signatures) de David Chaum con RSA.
* **Tareas / Métodos a implementar:**
  - `generarParLlavesRSA()`
  - `generarFactorCegado(RSAPublicKey clavePublicaRSA)`
  - `cegarMensaje(byte[] mensaje, BigInteger factorCegado, RSAPublicKey clavePublicaRSA)`
  - `firmarCegadoRSA(byte[] mensajeCegado, RSAPrivateKey clavePrivadaRSA)`
  - `descegarFirmaRSA(byte[] firmaCegada, BigInteger factorCegado, RSAPublicKey clavePublicaRSA)`
  - `verificarFirmaRSA(byte[] mensajeOriginal, byte[] firmaDescegada, RSAPublicKey clavePublicaRSA)`

---

## Compilación y Ejecución

Compilar el proyecto:
```bash
mvn clean compile
```

Ejecutar la aplicación:
```bash
mvn exec:java -Dexec.mainClass="com.paintingcompetition.Main"
```
O directamente con `java`:
```bash
java -cp "target/classes;%USERPROFILE%/.m2/repository/org/bouncycastle/bcprov-jdk18on/1.78.1/bcprov-jdk18on-1.78.1.jar" com.paintingcompetition.Main
```
