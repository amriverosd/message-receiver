# message-receiver

Este proyecto expone un conjunto de servicios para calcular la posición y mensajes de un emisor con base en un conjunto de 3 satelites.

El algoritmo que se usó para el calculo de las coordenadas es conocido como trilateración, el cual a traves de un conjunto de 2 o más coordenadas permite establecer un punto de origen. 

![alt text](https://www.researchgate.net/profile/Eva-Garcia-15/publication/228705728/figure/fig1/AS:484009307316234@1492408219682/Trilateracion-La-distancia-a-tres-balizas-nodos-blancos-permite-a-un-sensor-nodo.png)

## Pre-requisitos

Tener instalado Java y Docker en la computadora.

La aplicación usa Redis para persistir los datos del nivel 3, sin embargo, siguiendo los pasos de instalación no es necesario instalarlo. 

## Instalación 

Descargar el repositorio git: 

```html
https://github.com/amriverosd/message-receiver.git
```

Construir la imagen del proyecto: 

```html
sudo docker-compose build
```

Ejecutarlo: 

```html
sudo docker-compose up
```
Para finalizarlo:

```html
sudo docker-compose down
```

## Descripcion de servicios: 

Los servicios estan agrupados en dos niveles y pueden ser accedidos mediante swagger la siguiente URL en local: 

```html
http://localhost:8080/swagger-ui.html
```
### Nivel 2: 

El servicio creado es: 

```html
/v1/services/topsecret
```
Como input recibe un conjunto de satelites con sus respectiva distancia al emisor, nombre y arreglo de mensajes enviados, si el mensaje no es valido se recibe un string vacio, por ejemplo: 

```html
{
  "satellites": [
    {
      "name": "skywalker",
      "distance":115.5,
      "message": [
        "", "es", "", "", "secreto"
      ]
    }, 
    {
      "name": "sato",
      "distance":142.7,
      "message": [
        "este", "", "un", "", ""
      ]
    },
    {
      "name": "kenobi",
      "distance":100.0,
      "message": [
        "este", "", "", "mensaje", ""
      ]
    }
  ]
}
```

La respuesta es el mensaje completo más el calculo de coordenadas X e Y:

```html
{
    "message": "este es un mensaje secreto ",
    "position": {
        "x": -58.315252587160536,
        "y": -69.55141837182653
    }
}
```

si no es posible validarlo se envia un error con la descripcion del mensaje:

```html
{
    "status": "NOT_FOUND",
    "message": "Incomplete message in position: 4"
}
```
### Nivel 3: 

Se exponen varios servicios para hacer analisis parciales de la información que es almaceanda en un redis: 

```html
POST: /v1/services/topsecret_split/{satelliteName}
```

Permite el almacenamiento parcial de la información proveniente de un satelite:
```html
{
  "distance": 0,
  "message": [
    "string"
  ]
}
```

```html
DELETE: /v1/services/topsecret_split/{satelliteName}
```

Permite eliminar la información proveniente de un satelite en la BD:
```html
{
  "distance": 0,
  "message": [
    "string"
  ]
}
```

```html
GET: /v1/services/topsecret_split
```

Con base en la información almacenada determina la posición y mensaje de ser posible. 


## Aplicación en linea

Si desea probar las funcionalidades sin descargar el codigo, la aplicación se encuentra disponible en: 

```html
https://test-7oyxf34xma-uc.a.run.app/swagger-ui.html
```
Recuerde actualizar la URL en la parte superior derecha al ambiente productivo. 

