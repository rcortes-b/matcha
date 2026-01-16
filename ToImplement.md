# ToImplement

This is a collection of systems/patterns/features to implement once I've the app fully working

## Outbox Pattern

This will be implemented (at least) in the auth service. At the same time that the auth user is created, a new item will be added in the Outbox table.
General idea - Problem to solve:
- Create the auth_user and emit an event to be consumed in the UserService (Event UserCreated)
Solution to the problem:
- If Kafka is down, the user profile will never be created because the event won't be emitted
- If the time elapsed between the auth_user creation and the event to emit, the app crashes (this is done in ms, but could happen), the user profile will never be created because the event won't be emitted

