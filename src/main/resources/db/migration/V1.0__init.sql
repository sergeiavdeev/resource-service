CREATE TABLE storage (

    id uuid NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    owner uuid NOT NULL,
    name varchar(50),
    address varchar(250),
    lat float8,
    lng float8,
    description varchar(250)
);

CREATE TABLE contact_type (

    id uuid NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    name varchar(50)
);

CREATE TABLE contacts (

    id uuid NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    owner uuid NOT NULL,
    contact_type uuid NOT NULL,
    contact_value varchar(50),
    contact_value_ext varchar(150)
);

CREATE TABLE resource (

    id uuid NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    name varchar(150) NOT NULL,
    description varchar(250),
    storage uuid NOT NULL
);

INSERT INTO contact_type (id, name)
VALUES
    ('e250c9b6-389c-4bc9-b696-31137a5daf6b', 'phone'),
    ('9be3ab6f-14ac-4307-80ad-13b05c035b11', 'e-mail');

INSERT INTO storage (id, owner, name, address, lat, lng, description)
VALUES (
    'd3541a1d-b22e-4203-aca0-330e7b1248ca',
    '7d32cc34-8179-4505-be5e-1ca198c10238',
    'ЦНТ Топспин',
    'Подольск, Клемента Готвальда, 6Б',
    55.423380,
    37.529300,
    'Второй этаж, прямо по корридору, дверь слева'
);

INSERT INTO contacts(owner, contact_type, contact_value, contact_value_ext)
VALUES
    ('d3541a1d-b22e-4203-aca0-330e7b1248ca', 'e250c9b6-389c-4bc9-b696-31137a5daf6b', '79055089785', 'Сергей'),
    ('d3541a1d-b22e-4203-aca0-330e7b1248ca', '9be3ab6f-14ac-4307-80ad-13b05c035b11', 'avdey@mail.ru', '');

INSERT INTO resource (id, name, description, storage)
VALUES
    ('32e76963-6cc5-47d9-98aa-246adf1e5ede', 'Стол 1', 'Стол 1', 'd3541a1d-b22e-4203-aca0-330e7b1248ca'),
    ('def45a47-7458-41ff-8b2c-acdab8821d84', 'Стол 2', 'Стол 2', 'd3541a1d-b22e-4203-aca0-330e7b1248ca');