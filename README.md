# Matcha
A "Dating Website" inspired in the 42 School "Matcha" project

## NOTE

This project is inspired by the 42 School project called “Matcha.” While the original project requires the use of a micro-framework, I chose to use a full-featured framework instead.

This decision is not due to a lack of ability to work without a framework, but rather because I do not intend to submit this project at 42. My goal is to follow an industry-standard approach rather than rely on a less widely adopted solution.

The original project requirements can be found here:
[Link to the project requirements](https://cdn.intra.42.fr/pdf/pdf/188690/en.subject.pdf)

In the following sections, I explain the overall approach I followed, the stack I chose to work with, and why these technologies made sense for this project.

## Tech Stack

- **Backend Language:** Java
- **Backend Framework:** Spring Boot
- **Testing:** JUnit & Mockito
- **Message Broker:** Apache Kafka
- **Search Engine:** Elasticsearch
- **Database:** PostgreSQL
- **Cache:** Redis
- **Real-time:** WebSocket (Spring WebSocket)
- **API Gateway:** Spring Cloud Gateway
- **Containerization:** Docker & Docker Compose
- **CI/CD:** Jenkins
- **Frontend:** Tailwind CSS, HTML, Angular, Bootstrap
- **Client to Server Communication:** REST API
- **Service to Service Communication:** gRPC


## Stack Choice

### Java & Spring Boot

I aim to specialize in this stack because it's a industry-grade stack that enforces clean architecture and scalable design.
I enjoy working on apps from start to finish and this stack is ideal for full lifecycle and cloud-ready development.

### Frontend Stack

I’m not a frontend developer and have very little experience with it, but I want to expand my knowledge, so I chose what I felt complements my main stack (Java / Spring Boot) best.

### Database

I chose PostgreSQL for its reliability, advanced features, and ability to handle complex queries, with the bonus that I can also store JSON/NoSQL data if needed.

### Additional Tools

Spring Cloud, Apache Kafka, WebSockets, Redis, Jenkins, and Elasticsearch are all tools I haven’t used before. These technologies are widely used in the industry today, and I believe this project is the perfect opportunity to learn them and gain hands-on experience, expanding both my skills and knowledge.

## Architecture Choice

I chose a microservices architecture, not because it’s the most practical choice for this project (I know it’s overkill), but because I want to learn and gain hands-on experience with this common industry approach, which can be useful for building scalable apps.

## NOTE Nº2

Project is currently in development, more documentation will be added such as:

- Features
- Usage
- Lessons learned
- How I approached the development & how the app was deployed
- & more! (I’m still figuring out what else to include, hehe)