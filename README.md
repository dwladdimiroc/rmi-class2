RMI - Segunda clase
==========

Ejemplo simple creado con Netbeans 8.0, en el cual está tanto el cliente como servidor de forma independiente. Para su ejecución es posible compilar de forma independiente o abriendo el proyecto en Netbeans.

Dentro del proyecto Cliente-RMI está la conexión realizada con el Servidor-RMI, de tal manera que ambos deben poseer la misma IP y puertos para su funcionamiento. Por lo tanto, se deberá ejecutar primero la aplicación del servidor y posterior la del cliente.

El ejemplo trata de poseer dos interfaces distintas, de tal manera que una controle la interacción con el Usuario y otra con el Tablero. Dentro del menú se podrán ver nuevas características, aparte de agregar y ver usuarios, si no también editar un dato del servidor, y luego actualizar el botón según el dato del servidor. De esta manera, se poseerá un modelo de programación asíncrono, donde se deberá actualizar manualmente los datos que se desean mostrar.
