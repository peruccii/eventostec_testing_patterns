CREATE TABLE address (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    city VARCHAR(100) NOT NULL,
    uf VARCHAR(100) NOT NULL,
    event_id UUID,
    FOREIGN KEY (event_id) references event(id) ON DELETE CASCADE 
)