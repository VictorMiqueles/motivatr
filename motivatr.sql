--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4
-- Dumped by pg_dump version 14.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: authorities; Type: TABLE; Schema: public; Owner: dominicbrown
--

CREATE TABLE public.authorities (
    id bigint NOT NULL,
    username character varying(50),
    authority character varying(50) NOT NULL
);


ALTER TABLE public.authorities OWNER TO dominicbrown;

--
-- Name: authorities_id_seq; Type: SEQUENCE; Schema: public; Owner: dominicbrown
--

CREATE SEQUENCE public.authorities_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.authorities_id_seq OWNER TO dominicbrown;

--
-- Name: authorities_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dominicbrown
--

ALTER SEQUENCE public.authorities_id_seq OWNED BY public.authorities.id;


--
-- Name: challenges; Type: TABLE; Schema: public; Owner: dominicbrown
--

CREATE TABLE public.challenges (
    id bigint NOT NULL,
    author_id bigint NOT NULL,
    title text NOT NULL,
    description text NOT NULL,
    image_url text,
    video_url text,
    published_on timestamp without time zone,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    "1" integer,
    completed_count integer
);


ALTER TABLE public.challenges OWNER TO dominicbrown;

--
-- Name: challenges_completions; Type: TABLE; Schema: public; Owner: dominicbrown
--

CREATE TABLE public.challenges_completions (
    user_id bigint NOT NULL,
    challenge_id bigint NOT NULL,
    completed_at timestamp without time zone NOT NULL,
    id bigint NOT NULL
);


ALTER TABLE public.challenges_completions OWNER TO dominicbrown;

--
-- Name: challenges_completions_id_seq; Type: SEQUENCE; Schema: public; Owner: dominicbrown
--

CREATE SEQUENCE public.challenges_completions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.challenges_completions_id_seq OWNER TO dominicbrown;

--
-- Name: challenges_completions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dominicbrown
--

ALTER SEQUENCE public.challenges_completions_id_seq OWNED BY public.challenges_completions.id;


--
-- Name: challenges_id_seq; Type: SEQUENCE; Schema: public; Owner: dominicbrown
--

CREATE SEQUENCE public.challenges_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.challenges_id_seq OWNER TO dominicbrown;

--
-- Name: challenges_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dominicbrown
--

ALTER SEQUENCE public.challenges_id_seq OWNED BY public.challenges.id;


--
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: dominicbrown
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO dominicbrown;

--
-- Name: notification_settings; Type: TABLE; Schema: public; Owner: dominicbrown
--

CREATE TABLE public.notification_settings (
    id bigint NOT NULL,
    daily_notifications boolean NOT NULL,
    text_notifications boolean NOT NULL,
    email_notifications boolean NOT NULL
);


ALTER TABLE public.notification_settings OWNER TO dominicbrown;

--
-- Name: notification_settings_id_seq; Type: SEQUENCE; Schema: public; Owner: dominicbrown
--

CREATE SEQUENCE public.notification_settings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.notification_settings_id_seq OWNER TO dominicbrown;

--
-- Name: notification_settings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dominicbrown
--

ALTER SEQUENCE public.notification_settings_id_seq OWNED BY public.notification_settings.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: dominicbrown
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(60) NOT NULL,
    email text NOT NULL,
    mobile text,
    first_name character varying(50) NOT NULL,
    last_name text,
    image_url text,
    enabled boolean NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone,
    users_data_id bigint,
    notification_settings_id bigint
);


ALTER TABLE public.users OWNER TO dominicbrown;

--
-- Name: users_data; Type: TABLE; Schema: public; Owner: dominicbrown
--

CREATE TABLE public.users_data (
    id bigint NOT NULL,
    highest_streak integer,
    streak integer,
    total_completed integer,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.users_data OWNER TO dominicbrown;

--
-- Name: users_data_id_seq; Type: SEQUENCE; Schema: public; Owner: dominicbrown
--

CREATE SEQUENCE public.users_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_data_id_seq OWNER TO dominicbrown;

--
-- Name: users_data_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dominicbrown
--

ALTER SEQUENCE public.users_data_id_seq OWNED BY public.users_data.id;


--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: dominicbrown
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO dominicbrown;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dominicbrown
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: authorities id; Type: DEFAULT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.authorities ALTER COLUMN id SET DEFAULT nextval('public.authorities_id_seq'::regclass);


--
-- Name: challenges id; Type: DEFAULT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.challenges ALTER COLUMN id SET DEFAULT nextval('public.challenges_id_seq'::regclass);


--
-- Name: challenges_completions id; Type: DEFAULT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.challenges_completions ALTER COLUMN id SET DEFAULT nextval('public.challenges_completions_id_seq'::regclass);


--
-- Name: notification_settings id; Type: DEFAULT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.notification_settings ALTER COLUMN id SET DEFAULT nextval('public.notification_settings_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Name: users_data id; Type: DEFAULT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.users_data ALTER COLUMN id SET DEFAULT nextval('public.users_data_id_seq'::regclass);


--
-- Data for Name: authorities; Type: TABLE DATA; Schema: public; Owner: dominicbrown
--

COPY public.authorities (id, username, authority) FROM stdin;
1	yyy	ROLE_USER
2	ddd	ROLE_USER
3	fff	ROLE_USER
4	aaa	ROLE_USER
5	boooob	ROLE_USER
6	bigBazz	ROLE_USER
\.


--
-- Data for Name: challenges; Type: TABLE DATA; Schema: public; Owner: dominicbrown
--

COPY public.challenges (id, author_id, title, description, image_url, video_url, published_on, created_at, updated_at, "1", completed_count) FROM stdin;
1	1	5 bar riff	this one is an easy song to starty with. It will couse ont he coure bar courds A G E and C	/images/challenges/challenge1.png	https://www.youtube.com/embed/cUxRhesT8gY	2022-07-13 11:03:27.103	2022-07-07 09:42:40.995	2022-07-07 09:42:40.995	\N	\N
3	1	Bar courds	this one is an easy song to starty with. It will couse ont he coure bar courds A G E and C	/images/challenges/challenge1.png	https://www.youtube.com/embed/w4a2ge9N31E	2022-07-14 11:03:27.103	2022-07-07 09:42:40.995	2022-07-07 09:42:40.995	\N	\N
4	1	A note for begginers	this one is an easy song to starty with. It will couse ont he coure bar courds A G E and C	/images/challenges/challenge1.png	https://www.youtube.com/embed/2FTndcu891g	2022-07-15 11:03:27.103	2022-07-07 09:42:40.995	2022-07-07 09:42:40.995	\N	\N
5	1	Begginer accustic level one	this one is an easy song to starty with. It will couse ont he coure bar courds A G E and C	/images/challenges/challenge1.png	https://www.youtube.com/embed/HNSaXAe8tyg	2022-07-16 11:03:27.103	2022-07-07 09:42:40.995	2022-07-07 09:42:40.995	\N	\N
2	1	A begginer song for guitar	this one is an easy song to starty with. It will couse ont he coure bar courds A G E and C	/images/challenges/challenge1.png	https://www.youtube.com/embed/-X84GG06g-c	2022-07-12 11:03:27.103	2022-07-08 09:42:40.995	2022-07-08 09:42:40.995	\N	\N
\.


--
-- Data for Name: challenges_completions; Type: TABLE DATA; Schema: public; Owner: dominicbrown
--

COPY public.challenges_completions (user_id, challenge_id, completed_at, id) FROM stdin;
3	1	2022-07-08 10:21:27.042781	4
3	1	2022-07-08 10:40:06.810051	5
2	2	2022-07-12 14:40:42.444354	9
1	1	2022-07-12 14:40:42.444354	1
2	5	2022-07-12 21:38:50.804712	11
\.


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: dominicbrown
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	init	SQL	V1__init.sql	1204655984	dominicbrown	2022-07-07 09:42:08.770388	69	t
2	2	challenges table	SQL	V2__challenges_table.sql	-1643539814	dominicbrown	2022-07-07 09:42:08.852044	28	t
3	3	users data table	SQL	V3__users_data_table.sql	-149232883	dominicbrown	2022-07-07 09:42:08.886915	14	t
4	4	challenges completions table	SQL	V4__challenges_completions_table.sql	-1391562547	dominicbrown	2022-07-07 09:42:08.906349	6	t
5	5	drop column last challenge completed	SQL	V5__drop_column_last_challenge_completed.sql	-490627710	dominicbrown	2022-07-07 09:42:08.918538	17	t
6	6	add users data id	SQL	V6__add_users_data_id.sql	-234999282	dominicbrown	2022-07-07 09:42:08.940479	2	t
7	7	drop user id from users data	SQL	V7__drop_user_id_from_users_data.sql	474135116	dominicbrown	2022-07-07 09:42:08.947345	2	t
8	8	add completedchallenges data id	SQL	V8__add_completedchallenges_data_id.sql	-1259674559	dominicbrown	2022-07-07 16:47:16.574645	125	t
9	9	add challenge data completecount	SQL	V9__add_challenge_data_completecount.sql	2122485104	dominicbrown	2022-07-13 13:00:00.151157	36	t
10	18	add notification settings	SQL	V18__add_notification_settings.sql	915213352	dominicbrown	2022-07-14 10:14:19.087653	172	t
11	19	add notification settings id to users	SQL	V19__add_notification_settings_id_to_users.sql	134197129	dominicbrown	2022-07-14 10:14:19.275365	6	t
12	19.1	rename users user	SQL	V19_1__rename_users_user.sql	-1226618615	dominicbrown	2022-07-14 10:14:19.294579	4	t
13	19.2	drop user id from notification settings	SQL	V19_2__drop_user_id_from_notification_settings.sql	1839814944	dominicbrown	2022-07-14 10:14:19.304644	20	t
14	20.1	rename columns in users data	SQL	V20_1__rename_columns_in_users_data.sql	949281932	dominicbrown	2022-07-14 10:14:19.333226	30	t
\.


--
-- Data for Name: notification_settings; Type: TABLE DATA; Schema: public; Owner: dominicbrown
--

COPY public.notification_settings (id, daily_notifications, text_notifications, email_notifications) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: dominicbrown
--

COPY public.users (id, username, password, email, mobile, first_name, last_name, image_url, enabled, created_at, updated_at, users_data_id, notification_settings_id) FROM stdin;
1	yyy	$2a$10$IvBqJQc5OGlJZUBtDly3WudqdjqbVZBPrghq2JSjsQQvtF2LrN4sO	yyy	\N	Jack	Mish	/images/profile_picks/profile_pick_jack.png	t	2022-07-07 09:42:40.995931	2022-07-07 09:42:40.995944	1	\N
2	ddd	$2a$10$l2NDXQdBs7y2YRMNoEIFsO1NZr/7IE5Gmhe0Z618XwvjJMQ6luvo.	ddd	\N	Kat	Brown	/images/profile_picks/profile_pick_kat.png	t	2022-07-07 10:07:41.463136	2022-07-07 10:07:41.463159	2	\N
3	fff	$2a$10$a0KgYPc8q1lzmyBzbVw80uuhvUEPyygM7gyeHZs.MPmdSSWfF6i2W	fff	\N	Lilly	Wash	/images/profile_picks/profile_pick_lilly.png	t	2022-07-08 10:21:19.196011	2022-07-08 10:21:19.196025	3	\N
4	aaa	$2a$10$7Zt4E8xNnh1IY6Kqi4IaYebeHEjcy8MAEUOZJc4DCP26jMOvG42tO	aaa	\N	Ted	McTash	/images/profile_picks/profile_pick_ted.png	t	2022-07-12 12:43:44.628849	2022-07-12 12:43:44.628922	4	\N
5	boooob	$2a$10$RRxjBUgG.4yWRILDIoRGa.7fsOY5ARy06.Zap1hkNCdbs2nCypFee	bob@gmail.com	\N	Tess	Smith	/images/profile_picks/profile_pick_tess.png	t	2022-07-12 12:44:08.619343	2022-07-12 12:44:08.619448	5	\N
6	bigBazz	$2a$10$MwJOdl8Hgw9uR/oy950OgOo/LjS1Xx4Q8U9moC16bxZqIqSjVNq..	baz@gmail.com	\N	Tom	Witt	/images/profile_picks/profile_pick_tom.png	t	2022-07-12 12:44:36.500064	2022-07-12 12:44:36.500135	6	\N
\.


--
-- Data for Name: users_data; Type: TABLE DATA; Schema: public; Owner: dominicbrown
--

COPY public.users_data (id, highest_streak, streak, total_completed, created_at, updated_at) FROM stdin;
1	65	7	5	2022-07-07 09:42:41.089429	2022-07-07 09:42:41.089435
2	78	50	30	2022-07-07 10:07:41.580148	2022-07-07 10:07:41.580166
3	13	12	10	2022-07-08 10:21:19.302148	2022-07-08 10:21:19.302162
4	123	10	3	2022-07-12 12:43:44.812591	2022-07-12 12:43:44.812596
5	23	9	6	2022-07-12 12:44:08.773753	2022-07-12 12:44:08.773757
6	12	20	8	2022-07-12 12:44:36.654946	2022-07-12 12:44:36.654951
\.


--
-- Name: authorities_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dominicbrown
--

SELECT pg_catalog.setval('public.authorities_id_seq', 6, true);


--
-- Name: challenges_completions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dominicbrown
--

SELECT pg_catalog.setval('public.challenges_completions_id_seq', 11, true);


--
-- Name: challenges_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dominicbrown
--

SELECT pg_catalog.setval('public.challenges_id_seq', 1, true);


--
-- Name: notification_settings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dominicbrown
--

SELECT pg_catalog.setval('public.notification_settings_id_seq', 1, false);


--
-- Name: users_data_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dominicbrown
--

SELECT pg_catalog.setval('public.users_data_id_seq', 6, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dominicbrown
--

SELECT pg_catalog.setval('public.users_id_seq', 6, true);


--
-- Name: authorities authorities_pkey; Type: CONSTRAINT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.authorities
    ADD CONSTRAINT authorities_pkey PRIMARY KEY (id);


--
-- Name: challenges_completions challenges_completions_pkey; Type: CONSTRAINT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.challenges_completions
    ADD CONSTRAINT challenges_completions_pkey PRIMARY KEY (id);


--
-- Name: challenges challenges_pkey; Type: CONSTRAINT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.challenges
    ADD CONSTRAINT challenges_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: notification_settings notification_settings_pkey; Type: CONSTRAINT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.notification_settings
    ADD CONSTRAINT notification_settings_pkey PRIMARY KEY (id);


--
-- Name: users_data users_data_pkey; Type: CONSTRAINT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.users_data
    ADD CONSTRAINT users_data_pkey PRIMARY KEY (id);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: dominicbrown
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- Name: ix_auth_username; Type: INDEX; Schema: public; Owner: dominicbrown
--

CREATE UNIQUE INDEX ix_auth_username ON public.authorities USING btree (username, authority);


--
-- Name: authorities authorities_username_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.authorities
    ADD CONSTRAINT authorities_username_fkey FOREIGN KEY (username) REFERENCES public.users(username);


--
-- Name: challenges challenges_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.challenges
    ADD CONSTRAINT challenges_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.users(id);


--
-- Name: challenges_completions challenges_completions_challenge_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.challenges_completions
    ADD CONSTRAINT challenges_completions_challenge_id_fkey FOREIGN KEY (challenge_id) REFERENCES public.challenges(id);


--
-- Name: challenges_completions challenges_completions_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.challenges_completions
    ADD CONSTRAINT challenges_completions_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: users users_notification_settings_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_notification_settings_id_fkey FOREIGN KEY (notification_settings_id) REFERENCES public.notification_settings(id);


--
-- Name: users users_users_data_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dominicbrown
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_users_data_id_fkey FOREIGN KEY (users_data_id) REFERENCES public.users_data(id);


--
-- PostgreSQL database dump complete
--

