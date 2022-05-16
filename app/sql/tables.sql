create table users (
  id SERIAL PRIMARY KEY,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  address varchar(500) NOT NULL,
  email_id varchar(20) NOT NULL,
  password varchar(200) NOT NULL,
  created_at timestamp NOT NULL default current_timestamp,
  updated_at timestamp NOT NULL default current_timestamp,
  is_active boolean NOT NULL default true
);

create unique index users_email_index on users(id);

create table users_history (
  id SERIAL PRIMARY KEY,
  user_id integer not null references users(id) on delete cascade,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  address varchar(500) NOT NULL,
  email_id varchar(20) NOT NULL,
  user_activity varchar(2000) NOT NULL default 'user is created',
  updated_at timestamp NOT NULL default current_timestamp,
  superseeded_by integer
);

create table brands (
    id SERIAL PRIMARY KEY,
    name varchar(200) not null,
    defined_commission integer not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);

create table brands_history (
    id SERIAL PRIMARY KEY,
    brand_id integer not null references brands(id) on delete cascade,
    defined_commission integer not null,
    updated_at timestamp not null default current_timestamp
);

create table users_brand_link (
  id SERIAL PRIMARY KEY,
  user_id integer not null references users(id) on delete cascade,
  brand_id integer not null references brands(id) on delete cascade,
  unique_link varchar (100) NOT NULL,
  created_at timestamp NOT NULL default current_timestamp,
  updated_at timestamp NOT NULL default current_timestamp,
  validity timestamp NOT NULL
);

create unique index users_brand_link_unique_link_index on users_brand_link(unique_link);

create table users_brand_link_history (
  id SERIAL PRIMARY KEY,
  users_brand_link_id integer not null references users_brand_link(id) on delete cascade,
  validity timestamp NOT NULL,
  user_unique_link_click_count integer,
  updated_at timestamp not null default current_timestamp,
  superseeded_by integer
);

create table users_brand_unique_link_click_count (
    user_id integer not null references users(id) on delete cascade,
    users_brand_link_id integer not null references users_brand_link(id) on delete cascade,
    clicked_user_id integer not null references users(id) on delete cascade,
    PRIMARY KEY (user_id, users_brand_link_id, clicked_user_id)
);

create index users_brand_unique_link_click_count_user_id on users_brand_unique_link_click_count(user_id);

create index users_brand_unique_link_click_count_user_id_clicked_user_id_index on users_brand_unique_link_click_count(user_id, clicked_user_id) where user_id != clicked_user_id;

select 'drop table if exists "' || tablename || '" cascade;' as drop_queries from pg_tables where schemaname = 'public';