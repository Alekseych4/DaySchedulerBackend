
create table users (
    id BINARY(16) not null,
    name TEXT,
    surname TEXT,
    id_in_service TEXT not null,
    email VARCHAR(255) not null,
    profile_image_url TEXT,
    primary key (id),
    unique (email)
);

create table tasks (
    id BINARY(16) not null,
    title TEXT not null,
    start_time LONG not null,
    end_time LONG,
    is_reminder BOOLEAN not null,
    is_anchor BOOLEAN not null,
    tag VARCHAR(500),
    description MEDIUMTEXT,
    user_id BINARY(16) not null,
    primary key (id),
    foreign key (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table users_tasks_mtm (
    task_id BINARY(16) not null,
    user_id BINARY(16) not null,
    primary key (task_id, user_id),
    foreign key (task_id) references tasks (id) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (user_id) references users (id) ON DELETE CASCADE ON UPDATE CASCADE
);