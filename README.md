# demo1

Minimal demo application with a Spring Boot backend and a Vite + React frontend.

This README covers the quick start commands, where code lives, and a couple of small notes about the dev proxy and tests.

## Layout

- `src/main/java` — Spring Boot backend (controllers, services, domain, persistence)
- `src/test/java` — backend unit tests
- `client/` — Vite + React frontend

## Quick start

Requirements: Java 17, Maven, Node.js (for the client), and npm or bun for the client tooling.

1) Start the backend

```bash
# from project root
./mvnw spring-boot:run
```

The backend listens on port 8080 by default.

2) Start the frontend in development (Vite)

```bash
cd client
# install deps
npm install
# start dev server
npm run dev
```

Note: The client is configured with a dev proxy in `client/vite.config.ts`. Requests to `/api/*` are proxied to `http://localhost:8080/*` (the `/api` prefix is stripped). For example:

- `fetch('/api/orders')` -> `http://localhost:8080/orders`

If you prefer to keep `/products` and `/orders` without the `/api` prefix, adjust the proxy or call those paths directly when running the client from the same origin.

## Running tests

Run backend tests with Maven:

```bash
./mvnw test
```

Frontend tests / build:

```bash
cd client
# after installing dependencies
npm run build
```

## Important notes
- If `npm install` fails with certificate errors (UNABLE_TO_VERIFY_LEAF_SIGNATURE), ensure Node trusts your system/root CA or temporarily adjust `npm config set strict-ssl false` (not recommended for long-term).
- The project contains example controllers for orders and products. The client has a basic `App` and a `Products` screen under `client/src/screens/Products.tsx`.

## Next steps
- Add more UI to the Products screen (create/update/delete) and convert controller endpoints to accept JSON bodies with DTOs if desired.