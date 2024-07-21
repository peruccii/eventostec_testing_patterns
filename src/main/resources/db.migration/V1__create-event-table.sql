CREATE TABLE event (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    img_url VARCHAR(255) NOT NULL,
    event_url VARCHAR(255) NOT NULL,
    date TIMESTAMP NOT NULL,
    remote BOOLEAN NOT NULL
);