CREATE EXTENSION IF NOT EXISTS pgcrypto;

WITH users AS (
    SELECT
        i,
        ('00000000-0000-0000-0000-' || lpad(to_hex(i), 12, '0'))::uuid AS id,
        'user_' || i || '@example.com' AS email
    FROM generate_series(1, 50) i
)
INSERT INTO profile_user (
    id,
    email,
    first_name,
    last_name,
    birth_date,
    gender,
    sexual_preference_id,
    biography,
    latitude,
    longitude,
    country_code,
    state,
    city
)
SELECT
    id,
    email,
    'User' || i,
    'Test' || i,
    DATE '1985-01-01' + (i * 100),
    CASE WHEN i % 2 = 0 THEN 'MALE' ELSE 'FEMALE' END,
    (i % 3) + 1,
    'Generated test user #' || i,
    39.0 + (random() * 3),
    -0.3 + (random() * 3),
    'es',
    'Comunidad Valenciana',
    'Moncada'
FROM users;