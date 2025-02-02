# ERMicroservices
Proyecto basado en micro-servicios que utiliza el lore de EldenRing para reflejar la comunicación entre servicios. 

![Arquitectura del proyecto](/Users/lucasbahamondestoro/Desktop/SpringProjects/EsquemaProyectoERMicroservices.png)


## 🚀 Características del Proyecto

El sistema sigue una **arquitectura de microservicios** utilizando **Spring Boot y Spring Cloud**, con los siguientes componentes clave:

1. **API Gateway (Spring Cloud Gateway)** → Controla las peticiones de los clientes y distribuye a los microservicios adecuados.
2. **Service Discovery (Eureka o Consul)** → Registra y gestiona los microservicios.
3. **Base de Datos Distribuida** → Algunas bases de datos SQL y otras NoSQL según el servicio.
4. **Mensajería Asíncrona (Kafka/RabbitMQ)** → Para eventos como "Jugador obtiene arma legendaria".
5. **Autenticación con JWT/OAuth2** → Seguridad para jugadores y administradores.

## 📌 **Estructura de Microservicios**
Cada entidad clave del juego se maneja como un **microservicio independiente**, facilitando la escalabilidad y modularidad.

| **Microservicio**       | **Funcionalidad** |
|-------------------------|------------------|
| 🎭 **Personajes**         | Gestiona héroes, NPCs y jefes |
| ⚔️ **Armas y Equipamiento** | Datos sobre armas, armaduras, habilidades |
| 🔥 **Magia y Hechizos**   | Hechizos, encantamientos, invocaciones |
| 🗺️ **Mapa y Regiones**    | Lugares, mazmorras, puntos de interés |
| 📜 **Misiones y Eventos** | Historia, misiones secundarias |
| 🦇 **Bestiario**         | Enemigos y criaturas con estadísticas |
| ⚔️ **PvP y Multijugador** | Conexiones entre jugadores (opcional) |

Ejemplo de comunicación entre servicios usando **Kafka**:
```java
@KafkaListener(topics = "arma-asignada", groupId = "grupo-eldenring")
public void recibirEvento(String mensaje) {
    System.out.println("Evento recibido: " + mensaje);
}
