DROP TABLE IF EXISTS completed_challenges;

CREATE TABLE completed_challenges (
  id BIGSERIAL PRIMARY KEY,
  author_id BIGINT REFERENCES users(id) NOT NULL,
  challenge_id BIGINT REFERENCES challenges(id) NOT NULL,

  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL
);

