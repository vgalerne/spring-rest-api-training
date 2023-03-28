SET SCHEMA 'shopping-list';

CREATE TABLE "items" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar NOT NULL,
  "quantity" int
);
