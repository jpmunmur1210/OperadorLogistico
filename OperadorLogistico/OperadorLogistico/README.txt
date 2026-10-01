OPERADOR LOGISTICO - PARCIAL POO

Proyecto realizado en Java con Swing.

Estructura:
- model: clases de los envíos.
- service: guarda, agrega, retira y lista los envíos.
- controller: conecta la interfaz con el servicio.
- view: ventana gráfica.

Polimorfismo:
Envio es una clase abstracta y Terrestre, Aereo y Fluvial heredan de ella.
Cada clase implementa calcularTarifa() de una forma diferente.

Tarifas usadas:
Terrestre: 1500 por Km + 2000 por Kg
Aereo: 5000 por Km + 4000 por Kg
Fluvial: 800 por Km + 1000 por Kg

Nota:
El enunciado primero menciona "Fluvial", pero la tabla del documento dice "Marítimo".
Para evitar agregar complejidad innecesaria, el programa usa el nombre Fluvial
y toma los valores de la fila Marítimo (800/Km y 1000/Kg).

Para ejecutar:
1. Abrir la carpeta del proyecto en NetBeans, IntelliJ o Eclipse.
2. Ejecutar Main.java.
