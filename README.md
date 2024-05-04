#BUILDER
Este repositorio contiene el mecanismo para construir y desplegar una servicio Jenkins completamente configurado listo para ser usado remotamente desde una API.
Se deberá de suministrar:
- la relación de plugins que deben de ser instalados en esta instancia de Jenkins, se podrá hacer en tiempo de construcción del Dockerfile. 
- la configuración de:
  - usuario administrador,
  - usuario  para recuperar la definición del job como código desde un repositorio
  - repositorio donde se encuentra la definición del JOB. 

