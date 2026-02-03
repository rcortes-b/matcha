-- Ensure pgcrypto is available for UUID generation
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Insert 50 deterministic users
WITH users AS (
    SELECT
        i,
        -- Deterministic UUID based on row number, same as profile_user
        ('00000000-0000-0000-0000-' || lpad(to_hex(i), 12, '0'))::uuid AS id,
        'user_' || i || '@example.com' AS email
    FROM generate_series(1, 50) i
)
INSERT INTO auth_users (id, email, password, is_verified, role_id, created_at)
SELECT
    id,
    email,
    -- Replace this with a bcrypt hash if needed; here a dummy password hash
    '$2a$12$PcmSBrs42R2N0JQRqslt3.kKV12n9jPZda08GdNS01WgNY39q.VPG',
    true,      -- mark verified for testing
    1,         -- USER role
    NOW()
FROM users;