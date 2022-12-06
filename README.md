# Ibex35-request
Aplicación JavaSwing según el modelo publica-subscribe basada en las modificaciones a tiempo real sobre el IBEX-35 en java.

Aplicación realizada en lenguaje java, mediante la herramienta NetBeans, utilizando JavaSwing para el diseño gráfico.

Esta aplicación consiste en un modelo cliente servidor donde el cliente podrá lanzar y registrar solicitudes de mosificaciones en las diferentes empresas del IBEX-35, recibiendo una notificación en el momento en el que las condiciones de compra o venta se cumplen según lo indicado. 

La aplicación, cuenta con distintos ficheros java. Los principales, son el fichero "CallbackServer", el cual es el servidor que realiza la consulta de datos de bolsa de manera periódica, y "CallbackClient", que contiene la interfaz gráfica, desde la cual el cliente puede realzir acciones de publica/subscribe para mandar al servidor peticiones de respuesta a modificaciones de compra o venta de empresas. 

Al ejecutar el servidor, se deberá indicar el puerto que se encontrará (ej: 1234). 
Al ejecutar el cliente en los campos de host name y número de puerto, se introducirá "localhost" y el puerto del servidor.
