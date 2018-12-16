DROP TABLE IF EXISTS restaurant;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 1000;

CREATE TABLE restaurant (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        TEXT      NOT NULL
);
