# QA Prueba Técnica — Automatización Web y API

Proyecto de automatización de pruebas desarrollado con **Java 17**, **Serenity BDD**, **Screenplay pattern** y **Cucumber**, cubriendo pruebas web sobre [selenium.dev](https://selenium.dev) y pruebas de API sobre [reqres.in](https://reqres.in).

---

## Stack tecnológico

| Herramienta | Versión |
|---|---|
| Java | 17 |
| Maven | 3.9+ |
| Serenity BDD | 4.1.20 |
| Cucumber | 7.18.0 |
| JUnit 5 | 5.10.2 |
| WebDriverManager | 5.8.0 |
| Chrome | Último estable |

---

## Estructura del proyecto

```
qa-prueba-tecnica/
├── src/test/
│   ├── java/com/qa/prueba/
│   │   ├── runner/
│   │   │   └── CucumberTestSuite.java       # Entry point JUnit 5
│   │   ├── stepdefinitions/
│   │   │   ├── WebStepDefinitions.java      # Steps pruebas web
│   │   │   └── ApiStepDefinitions.java      # Steps pruebas API
│   │   └── screenplay/
│   │       ├── tasks/                       # Acciones del actor
│   │       ├── questions/                   # Preguntas sobre el estado
│   │       ├── ui/                          # Page Objects (Targets)
│   │       └── models/                      # POJOs para deserialización API
│   └── resources/
│       ├── features/
│       │   ├── web/selenium_web.feature     # TC1, TC2, TC3
│       │   └── api/reqres_api.feature       # TC4, TC5, TC6
│       ├── serenity.conf                    # Configuración Serenity
│       └── junit-platform.properties
└── pom.xml
```

---

## Casos de prueba

### Web — selenium.dev
| ID | Descripción |
|---|---|
| TC1 | Valida que la home carga correctamente y muestra el título esperado |
| TC2 | Verifica que la navegación a Documentation funciona |
| TC3 | Valida el flujo de búsqueda y que los resultados son relevantes |

### API — reqres.in
| ID | Descripción |
|---|---|
| TC4 | GET /users?page=2 — Lista usuarios, valida status 200 y campos requeridos |
| TC5 | POST /users — Crea usuario, valida status 201, nombre, job, id y createdAt |
| TC6 | PUT /users/2 — Actualiza usuario, valida status 200, nombre, job y updatedAt |

---

## Requisitos previos

- Java 17+
- Maven 3.9+
- Google Chrome instalado (WebDriverManager descarga el driver automáticamente)

---

## Configuración del entorno

1. Clonar el repositorio:
```bash
git clone <url-del-repositorio>
cd qa-prueba-tecnica
```

2. Verificar Java y Maven:
```bash
java -version   # debe mostrar 17+
mvn -version    # debe mostrar 3.9+
```

---

## Ejecución de pruebas

### Ejecutar todas las pruebas
```bash
mvn clean verify
```

### Ejecutar solo pruebas web
```bash
mvn clean verify -Dcucumber.filter.tags="not @api"
```

### Ejecutar solo pruebas de API
```bash
mvn clean verify -Dcucumber.filter.tags="not @web"
```

### Modo headless (sin abrir el navegador)
Modificar `src/test/resources/serenity.conf`:
```
headless.mode=true
```

---

## Reporte Serenity

Después de ejecutar `mvn clean verify`, el reporte se genera en:
```
target/site/serenity/index.html
```

Abrir en el navegador:
```bash
open target/site/serenity/index.html        # macOS
xdg-open target/site/serenity/index.html   # Linux
start target/site/serenity/index.html       # Windows
```

El reporte incluye:
- Resultado por escenario y paso
- Capturas de pantalla en fallos (pruebas web)
- Detalle de requests/responses (pruebas API)
- Métricas de cobertura y duración
