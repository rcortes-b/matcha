# Matcha Project Dev Notes

## Exposed Ports

### Utils

| Name | Localhost port | Docker port |
|------|----------------|-------------|
| PostgreSQL | 5433 | 5432 |
| Kafka | 9098 | 9092 |
| Zookeper | 2182 | 2182 |
| Elasticsearch | 9200 | 9200 |
| Redis | 6380 | 6379 |

### Services

| Name | Localhost port | Docker port |
|------|----------------|-------------|
| Auth | 8081/9091(gRPC) | 8081/9091(gRPC) |
| Gateway | 9090 | 9090 |
| User | 8082/9093(gRPC) | 8082/9093(gRPC) |
| Browse | 9200 | 9200 |
| Notification | - | - |
| Message | - | - |

## Implementation List

This is a compilation of things that i've not implemented yet but will be implemented once the program is fully working

- Outbox Pattern for data resilience (Kafka Events)
- Password change in the Auth Service
- Profile Editing in the User Service
- Add configuration to Gateway Router
- Add filter to each service

- To add in the 'browse-service': If a user has already been liked/blocked, do not show
- Add '/unlike' and '/profile-view' endpoints and send events to the 'notification-service'

## Notes about things learnt

### General Notes

- Microservices Architecture == Configuration Hell. I've spent hours debugging a simple missmatch in the dependecies of the Maven project
- Java doesn't like the under score '_' (at least parsing the application.yml). I've spent hours debugging to find out that Java/Docker prefers a simple score '-'.

### Outbox Pattern

This will be implemented (at least) in the auth service. At the same time that the auth user is created, a new item will be added in the Outbox table.
General idea - Problem to solve:
- Create the auth_user and emit an event to be consumed in the UserService (Event UserCreated)
Solution to the problem:
- If Kafka is down, the user profile will never be created because the event won't be emitted
- If the time elapsed between the auth_user creation and the event to emit, the app crashes (this is done in ms, but could happen), the user profile will never be created because the event won't be emitted

## Development Notes

### 13-02-2026

#### This are the interactions that will be notified

Note: The key of the kafka event will be the targetId to always have the same user in the same partition

- LikeReceived by 'x'
- ProfileViewed by 'x'
- MessageReceived by 'x'
- MatchEvent with 'x'
• When a connected user “unlikes” them. (This is like a bonus - extra feature, because in a real app that won't be necessary)

#### This are the interactions that won't be notified
- UserBlock
- UserReport

