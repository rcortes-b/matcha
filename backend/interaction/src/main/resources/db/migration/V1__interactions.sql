CREATE TABLE likes (
    user_id UUID NOT NULL,
    target_id UUID NOT NULL,
	created_at TIMESTAMPTZ DEFAULT NOW(),
	PRIMARY KEY(user_id, target_id)
);

CREATE TABLE blocks (
    user_id UUID NOT NULL,
    target_id UUID NOT NULL,
	created_at TIMESTAMPTZ DEFAULT NOW(),
	PRIMARY KEY(user_id, target_id)
);

CREATE TABLE reports (
    user_id UUID NOT NULL,
    target_id UUID NOT NULL,
	reason VARCHAR(255) NOT NULL,
	created_at TIMESTAMPTZ DEFAULT NOW(),
	PRIMARY KEY(user_id, target_id)
);

CREATE TABLE profile_viewed (
	user_id UUID NOT NULL,
    target_id UUID NOT NULL,
	created_at TIMESTAMPTZ DEFAULT NOW(),
	PRIMARY KEY(user_id, target_id)
);
