KAFKA_BROKER="matcha-kafka-dev:9092"

echo "Waiting for Kafka to be ready..."

while ! kafka-topics --bootstrap-server $KAFKA_BROKER --list >/dev/null 2>&1; do
  sleep 2
done

echo "Kafka is ready"

TOPICS=(
  "user.created:3",
  "profile.update:3"
)

for topic in "${TOPICS[@]}"; do
  IFS=":" read name partitions <<< "$topic"

  kafka-topics --bootstrap-server $KAFKA_BROKER \
    --create \
    --if-not-exists \
    --topic "$name" \
    --partitions "$partitions" \
    --replication-factor 1

done
