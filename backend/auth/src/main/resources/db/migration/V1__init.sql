CREATE TABLE roles (
    id SMALLSERIAL PRIMARY KEY,
    role VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO roles VALUES (1, 'USER'), (2, 'ADMIN');

CREATE TABLE auth_users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(60) NOT NULL,
    is_verified BOOLEAN DEFAULT false,
    role_id SMALLINT NOT NULL REFERENCES roles(id),
    created_at TIMESTAMPTZ DEFAULT NOW()
);