CREATE TABLE coupon (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    code VARCHAR(100) NOT NULL,
    discount INTEGER NOT NULL,
    valid TIMESTAMP NOT NULL,
    event_id UUID,
    FOREIGN KEY (event_id) references event(id) ON DELETE CASCADE
)