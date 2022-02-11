

-- users
INSERT INTO user (id, username)
VALUES (1, user1);
INSERT INTO user (id, username)
VALUES (2, user2);
INSERT INTO user (id, username)
VALUES (3, user3);
INSERT INTO user (id, username)
VALUES (4, moderator);

-- follows
INSERT INTO user_follow (id, user_id, follow_user_id)
VALUES (1, 1, 2);

-- messages
INSERT INTO user_status_message (id, message, datetime, user_id, broadcast)
VALUES (1, 'Time to Grind!', CURRENT_TIME(), 4, 1);
INSERT INTO user_status_message (id, message, datetime, user_id, broadcast)
VALUES (2, 'Another day another dollar', CURRENT_TIME(), 2, 0);
INSERT INTO user_status_message (id, message, datetime, user_id, broadcast)
VALUES (3, 'Who ready to work!', CURRENT_TIME(), 1, 0);
INSERT INTO user_status_message (id, message, datetime, user_id, broadcast)
VALUES (4, 'Any good places to eat downtown?', CURRENT_TIME(), 3, 0);
