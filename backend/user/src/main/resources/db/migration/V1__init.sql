CREATE TABLE sexual_preference (
    id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    preference VARCHAR(20) UNIQUE NOT NULL
);

INSERT INTO sexual_preference(preference) VALUES ('HETEROSEXUAL'),
                                                 ('HOMOSEXUAL'),
                                                 ('BISEXUAL');

CREATE TABLE profile_user (
    id UUID UNIQUE NOT NULL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    birth_date DATE, --This cannot be null, but it will be in the register
    gender VARCHAR(6), -- This may be male or female
    sexual_preference_id SMALLINT references sexual_preference(id),
    biography VARCHAR(200),
    -- Pictures
    -- Location
    -- Tag
    created_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE user_pictures (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id UUID NOT NULL references profile_user(id),
    is_profile boolean DEFAULT false,
    url text NOT NULL,
    uploaded_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE location (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id UUID references profile_user(id),
    city VARCHAR(50),
    country VARCHAR(50),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    postal_code VARCHAR(10)
    --- state, provincia
    --- full address, ...
);

CREATE TABLE tags (
    id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    tag VARCHAR(20) UNIQUE NOT NULL
);

INSERT INTO tags (tag) VALUES ('fitness'),
                              ('technology');

CREATE TABLE user_tags (
    user_id UUID references profile_user(id),
    tag_id SMALLINT references tags(id),
    PRIMARY KEY (user_id, tag_id)
);

CREATE INDEX idx_user_pictures_user_id ON user_pictures(user_id);
CREATE INDEX idx_location_user_id ON location(user_id);