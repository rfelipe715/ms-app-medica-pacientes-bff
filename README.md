# ms-app-medica-pacientes-bff

Capa **BFF** (Backend for Frontend) del módulo **Pacientes**. Es la puerta de entrada del módulo para el cliente (a través del API Gateway): valida el contrato de entrada (`@Valid`) y delega en `ms-app-medica-pacientes-bs`. No accede a la base de datos.

| | |
|---|---|
| **Puerto** | `8181` |
| **Patrón** | Controller → Service → Client (Feign) |
| **Ruta base** | `/api/v1/pacientes` |
| **Llama a** | `pacientes-bs` (8081) · `citas-bff` (8090) |
| **Swagger** | `http://localhost:8181/swagger-ui.html` — agregado también al Gateway como pestaña **Pacientes** |

## Ejecución

```bash
# Con todo el ecosistema (recomendado), desde app-medica-et-fullstack-1/
docker compose up --build

# Individual
./mvnw spring-boot:run     # mvnw.cmd en Windows
./mvnw test
```
