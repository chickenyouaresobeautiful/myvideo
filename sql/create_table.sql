CREATE TABLE advertisements (
                                id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                                title VARCHAR(255) NOT NULL,
                                description TEXT,
                                url VARCHAR(255) NOT NULL,
                                start_time TIMESTAMP NOT NULL,
                                end_time TIMESTAMP NOT NULL,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categories (
                            id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL UNIQUE,
                            description TEXT,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE users (
                       id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       phone_number VARCHAR(15),
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       profile_picture VARCHAR(255),
                       role ENUM('USER', 'ADMIN') DEFAULT 'USER'
);

CREATE TABLE videos (
                        id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT UNSIGNED NOT NULL,
                        title VARCHAR(255) NOT NULL,
                        description TEXT,
                        url VARCHAR(255) NOT NULL,
                        thumbnail_url VARCHAR(255),
                        duration INT UNSIGNED,
                        views BIGINT UNSIGNED DEFAULT 0,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        privacy ENUM('PUBLIC', 'PRIVATE') DEFAULT 'PUBLIC',
                        category_id BIGINT UNSIGNED,
                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                        FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL
);

CREATE TABLE comments (
                          id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                          video_id BIGINT UNSIGNED NOT NULL,
                          user_id BIGINT UNSIGNED NOT NULL,
                          content TEXT NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (video_id) REFERENCES videos(id) ON DELETE CASCADE,
                          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE favorites (
                           id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT UNSIGNED NOT NULL,
                           video_id BIGINT UNSIGNED NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                           FOREIGN KEY (video_id) REFERENCES videos(id) ON DELETE CASCADE,
                           UNIQUE (user_id, video_id) -- Ensure a user can only favorite a video once
);

CREATE TABLE likes_dislikes (
                                id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                                video_id BIGINT UNSIGNED NOT NULL,
                                user_id BIGINT UNSIGNED NOT NULL,
                                type TINYINT NOT NULL, -- 1 for like, -1 for dislike
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                FOREIGN KEY (video_id) REFERENCES videos(id) ON DELETE CASCADE,
                                FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                UNIQUE (video_id, user_id) -- Ensure a user can only like or dislike a video once
);

CREATE TABLE play_history (
                              id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                              user_id BIGINT UNSIGNED NOT NULL,
                              video_id BIGINT UNSIGNED NOT NULL,
                              last_watched_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              progress INT UNSIGNED DEFAULT 0,
                              FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                              FOREIGN KEY (video_id) REFERENCES videos(id) ON DELETE CASCADE,
                              UNIQUE (user_id, video_id) -- Ensure a unique play history per user per video
);

CREATE TABLE reports (
                         id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                         reported_by BIGINT UNSIGNED NOT NULL,
                         video_id BIGINT UNSIGNED,
                         comment_id BIGINT UNSIGNED,
                         reason TEXT NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (reported_by) REFERENCES users(id) ON DELETE CASCADE,
                         FOREIGN KEY (video_id) REFERENCES videos(id) ON DELETE CASCADE,
                         FOREIGN KEY (comment_id) REFERENCES comments(id) ON DELETE CASCADE
);

CREATE TABLE subscriptions (
                               id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                               subscriber_id BIGINT UNSIGNED NOT NULL,
                               subscribed_to_id BIGINT UNSIGNED NOT NULL,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (subscriber_id) REFERENCES users(id) ON DELETE CASCADE,
                               FOREIGN KEY (subscribed_to_id) REFERENCES users(id) ON DELETE CASCADE,
                               UNIQUE (subscriber_id, subscribed_to_id) -- Ensure a user can only subscribe once to another user
);

CREATE TABLE tags (
                      id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50) NOT NULL UNIQUE,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE video_advertisements (
                                      id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                                      video_id BIGINT UNSIGNED NOT NULL,
                                      advertisement_id BIGINT UNSIGNED NOT NULL,
                                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      FOREIGN KEY (video_id) REFERENCES videos(id) ON DELETE CASCADE,
                                      FOREIGN KEY (advertisement_id) REFERENCES advertisements(id) ON DELETE CASCADE
);

CREATE TABLE video_tags (
                            id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                            video_id BIGINT UNSIGNED NOT NULL,
                            tag_id BIGINT UNSIGNED NOT NULL,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (video_id) REFERENCES videos(id) ON DELETE CASCADE,
                            FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE,
                            UNIQUE (video_id, tag_id) -- Ensure a video can have a tag only once
);