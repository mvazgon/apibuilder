# APIBUILDER
## Como usar esta app Contenerizada
El presente DOCKERFILE admite dos variables de entorno que en tiempo de ejecución podemos definir para personalizar el comportamiento:
- **URLBUILDER** definimos la url del builder, esto es la URL del HOOK del JOB de Jenkins para invocarlo remotamente.
- **DATAPATH** definimos el path del directorio donde se almacenará el json enviado a la API, en caso de recibir un ok a la petición.
## TODO 
En un futuro se deberá añadir un token, debidamente encriptado, para comunicarse con la URL del BUILDER.  